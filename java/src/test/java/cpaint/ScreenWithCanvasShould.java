package cpaint;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@Disabled
class ScreenWithCanvasShould {

    private Console console;

    @BeforeEach
    void setUp() {
        console = mock(Console.class);
    }

    @Test
    void draw_a_canvas() {
        ScreenWithCanvas screen = new ScreenWithCanvas(new Canvas(3, 2));

        screen.representWith(console);

        verify(console)
                .print( "-----" + System.lineSeparator() +
                        "|   |" + System.lineSeparator() +
                        "|   |" + System.lineSeparator() +
                        "-----");
    }

    @Test
    void draw_a_line_on_a_canvas() {
        var canvasRepresentation =
                new ScreenWithCanvas(new Canvas(3, 2))
                .drawLine(new Line(1, 2, 2, 2));

        canvasRepresentation.representWith(console);

        verify(console)
                .print( "-----" + System.lineSeparator() +
                        "|   |" + System.lineSeparator() +
                        "|xx |" + System.lineSeparator() +
                        "-----");
    }


}