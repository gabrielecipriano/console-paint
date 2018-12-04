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

    static void with(InputStream source) {
        new ConsolePaint(System.out::print,
                new CommandParser())
                .executeWith(new Scanner(source));
    }

    private void executeWith(Scanner inputSource) {
        loop(inputSource, new EmptyScreen());
    }

    private void loop(Scanner inputSource, Screen screen) {
        var input = console.getNextCommand(inputSource);
        var command = commandParser.interpret(input);
        
        screen = screen.execute(command);

        if (screen.isOn()) {
            console.print(System.lineSeparator() +
                    screen.render() +
                    System.lineSeparator());

            loop(inputSource, screen);
        }
    }
}
