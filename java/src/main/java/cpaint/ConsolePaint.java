package cpaint;

import java.util.Scanner;

public class ConsolePaint {
    private Console console;
    private CommandParser commandParser;

    public static void main(String[] args) {
        new ConsolePaint(System.out::print,
                new CommandParser())
                .executeWith(new Scanner(System.in));
    }

    ConsolePaint(Console console, CommandParser commandParser) {
        this.console = console;
        this.commandParser = commandParser;
    }

    void executeWith(Scanner inputSource) {
        Screen screen = new EmptyScreen();
        console.print("enter command: ");

        while (inputSource.hasNext() && screen.isOn()) {
            var input = inputSource.nextLine();
            var command = commandParser.interpret(input);
            screen = screen.execute(command);

            if (screen.isOn()) {
                console.print(System.lineSeparator() + screen.render() + System.lineSeparator());
                console.print("enter command: ");
            }
        }
    }

}
