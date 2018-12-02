package cpaint;

import java.util.Scanner;

public class ConsolePaint {
    private Screen screen;
    private CommandParser commandParser;

    public static void main(String[] args) {
        new ConsolePaint(System.out::print,
                new CommandParser())
                .executeWith(new Scanner(System.in));
    }

    public ConsolePaint(Screen screen, CommandParser commandParser) {
        this.screen = screen;
        this.commandParser = commandParser;
    }

    public void executeWith(Scanner inputSource) {
        screen.print("enter command: " + System.lineSeparator());
        var input = inputSource.nextLine();
        var command = commandParser.interpret(input);
        screen.print(command.representation());
    }

}
