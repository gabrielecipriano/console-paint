package cpaint;

import org.apache.tools.ant.filters.StringInputStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Scanner;

import static org.mockito.Mockito.*;

class ConsolePaintShould {

    private Console console;
    private CommandParser commandParser;

    @BeforeEach
    void setUp() {
        console = Mockito.mock(Console.class);
        commandParser = mock(CommandParser.class);
    }

    @Test
    void draw_a_canvas_with_C_command() {
        var canvasCommand = "C 3 2";

        when(commandParser.interpret(canvasCommand))
                .thenReturn(new Canvas(3, 2));

        new ConsolePaint(console, commandParser)
                .executeWith(input(canvasCommand));

        verify(console, times(2)).print("enter command: ");
        verify(console).print(System.lineSeparator() +
                "-----" + System.lineSeparator() +
                "|   |" + System.lineSeparator() +
                "|   |" + System.lineSeparator() +
                "-----" + System.lineSeparator());
    }

    private Scanner input(String canvasCommand) {
        return new Scanner(new StringInputStream(canvasCommand));
    }
}