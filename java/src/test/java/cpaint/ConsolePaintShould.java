package cpaint;

import org.apache.tools.ant.filters.StringInputStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Scanner;

import static org.mockito.Mockito.*;

class ConsolePaintShould {

    private Screen screen;
    private CommandParser commandParser;

    @BeforeEach
    void setUp() {
        screen = Mockito.mock(Screen.class);
        commandParser = mock(CommandParser.class);
    }

    @Test
    void draw_a_canvas_with_C_command() {
        var canvasCommand = "C 3 2";

        when(commandParser.interpret(canvasCommand))
                .thenReturn(new Canvas(3, 2));

        new ConsolePaint(screen, commandParser)
                .executeWith(input(canvasCommand));

        verify(screen, times(2)).print("enter command: ");
        verify(screen).print(System.lineSeparator() +
                "-----" + System.lineSeparator() +
                "|   |" + System.lineSeparator() +
                "|   |" + System.lineSeparator() +
                "-----" + System.lineSeparator());
    }

    private Scanner input(String canvasCommand) {
        return new Scanner(new StringInputStream(canvasCommand));
    }
}