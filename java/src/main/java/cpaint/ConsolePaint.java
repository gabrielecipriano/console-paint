package cpaint;

import java.io.InputStream;
import java.util.Scanner;

class ConsolePaint {
    private Console console;
    private CommandParser commandParser;

    private ConsolePaint(Console console, CommandParser commandParser) {
        this.console = console;
        this.commandParser = commandParser;
    }

    private void executeWith(Scanner inputSource) {
        Screen screen = new EmptyScreen();
        console.print("enter command: ");

        while (screen.isOn() && inputSource.hasNext()) {
            var input = inputSource.nextLine();
            var command = commandParser.interpret(input);
            screen = screen.execute(command);

            if (screen.isOn()) {
                console.print(System.lineSeparator() + screen.render() + System.lineSeparator());
                console.print("enter command: ");
            }
        }
    }

    static void compute(InputStream source) {
        new ConsolePaint(System.out::print,
                new CommandParser())
                .executeWith(new Scanner(source));
    }
}
