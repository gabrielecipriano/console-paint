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

        while (inputSource.hasNext()) {
            var input = inputSource.nextLine();
            var command = commandParser.interpret(input);
            screen = screen.execute(command);
            console.print(System.lineSeparator() + command.representation() + System.lineSeparator());

            console.print(screen.representation());
            console.print("enter command: ");
        }
    }

}
