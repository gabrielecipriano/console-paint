package cpaint;

import java.io.InputStream;
import java.util.Scanner;
import java.util.function.BiFunction;

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
        whileScreenIsOn(inputSource, (input, screen) -> {
            var command = commandParser.interpret(input);
            return screen.execute(command);
        });
    }

    private void whileScreenIsOn(Scanner inputSource, BiFunction<String, Screen, Screen> updateScreenForCommand) {
        Screen screen = new EmptyScreen();
        console.print("enter command: ");

        loop(inputSource, updateScreenForCommand, screen);
    }

    private void loop(Scanner inputSource, BiFunction<String, Screen, Screen> updateScreenForCommand, Screen screen) {
        var input = inputSource.nextLine();
        screen = updateScreenForCommand.apply(input, screen);

        if (screen.isOn()) {
            console.print(System.lineSeparator() + screen.render() + System.lineSeparator());
            console.print("enter command: ");
            loop(inputSource, updateScreenForCommand, screen);
        }
    }
}
