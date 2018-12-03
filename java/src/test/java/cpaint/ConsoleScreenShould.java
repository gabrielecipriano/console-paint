package cpaint;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

class ConsoleScreenShould {

    private Screen screen;

    @BeforeEach
    void setUp() {
        screen = mock(Screen.class);
    }

    @Test
    void draw_a_canvas() {
        CanvasRepresentation canvasRepresentation = new CanvasRepresentation(new Canvas(3, 2));

        canvasRepresentation.representWith(screen);

        verify(screen)
                .print( "-----" + System.lineSeparator() +
                        "|   |" + System.lineSeparator() +
                        "|   |" + System.lineSeparator() +
                        "-----");
    }

    @Test
    void draw_a_line_on_a_canvas() {
        var canvasRepresentation =
                new CanvasRepresentation(new Canvas(3, 2))
                .drawLine(new Line(1, 2, 2, 2));

        canvasRepresentation.representWith(screen);

        verify(screen)
                .print( "-----" + System.lineSeparator() +
                        "|   |" + System.lineSeparator() +
                        "|xx |" + System.lineSeparator() +
                        "-----");
    }


    private class CanvasRepresentation {
        private Character[][] screenState;

        public CanvasRepresentation(Canvas canvas) {
            this.screenState = toBidimensionalCharArray(canvas);
        }

        public void representWith(Screen screen) {
            screen.print(toList(this.screenState).stream().collect(Collectors.joining(System.lineSeparator())));
        }

        private List<String> toList(Character[][] screenState) {
            ArrayList<String> lines = new ArrayList<>();

            for (int i = 0; i < screenState.length; i++) {
                Character[] characters = screenState[i];
                String line = Arrays.toString(characters);
                lines.add(line);
            }

            return lines;
        }

        private Character[][] toBidimensionalCharArray(Canvas canvas) {
            int w = canvas.w + 2;
            int h = canvas.h + 2;

            Character[][] characters = new Character[w][h];

            List<String> lines = lines(canvas.w, canvas.h);

            for (int i = 0; i < w; i++) {
                for (int j = 0; j < h; j++) {
                    char c = lines.get(j).charAt(i);
                    characters[j][i] = c;
                }
            }

            return characters;
        }

        public List<String> lines(int w, int h) {
            var horizontalSide = line("-", w + 2);
            var verticalSide = "|" + line(" ", w) + "|";

            return buildWithBorders(horizontalSide, verticalSide, h);
        }

        private List<String> buildWithBorders(String horizontalSide, String verticalSide, int h) {
            var lines = new ArrayList<String>();
            lines.add(horizontalSide);
            IntStream.range(0, h).forEach(i -> lines.add(verticalSide));
            lines.add(horizontalSide);
            return lines;
        }

        private String line(final String symbol, int count) {
            return symbol.repeat(count);
        }

        public CanvasRepresentation drawLine(Line line) {
            return null;
        }
    }
}