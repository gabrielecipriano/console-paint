package cpaint;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;

@Disabled
class ScreenWithCanvasShould {

    @Test
    void draw_a_canvas() {
        var screen = new ScreenWithCanvas(new Canvas(3, 2));

        assertThat(screen.render(), is(
                        "-----" + System.lineSeparator() +
                        "|   |" + System.lineSeparator() +
                        "|   |" + System.lineSeparator() +
                        "-----"));
    }

    @Test
    void draw_a_line_on_a_canvas() {
        var screen = new ScreenWithCanvas(new Canvas(3, 2))
                .drawLine(new Line(1, 2, 2, 2));

        assertThat(screen.render(), is(
                        "-----" + System.lineSeparator() +
                        "|   |" + System.lineSeparator() +
                        "|xx |" + System.lineSeparator() +
                        "-----"));
    }

    @Test
    void lines() {
        List<String> lines = lines(3, 2);

        assertThat(lines, is(contains("-----" ,
                "|   |" ,
                "|   |" ,
                "-----")));
    }

    @Test
    void spike_to_char() {
        List<String> strings = asList("123", "456");

        char[][] expected = new char[2][3];

        expected[0][0] = '1';
        expected[0][1] = '2';
        expected[0][2] = '3';
        expected[1][0] = '4';
        expected[1][1] = '5';
        expected[1][2] = '6';

        assertThat(toCharArray(strings), is(expected));
    }

    @Test
    void spike_back_to_strings() {
        List<String> strings = asList("123", "456");

        char[][] chars = toCharArray(strings);

        List<String> actual = toList(chars);

        assertThat(actual, is(strings));
    }

    @Test
    void spike_everything_togheter() {
        List<String> lines = lines(3, 2);

        char[][] chars = toCharArray(lines);

        List<String> strings = toList(chars);

        String canvas = strings.stream().collect(Collectors.joining(System.lineSeparator()));

        assertThat(canvas, is("-----" + System.lineSeparator() +
                "|   |" + System.lineSeparator() +
                "|   |" + System.lineSeparator() +
                "-----"));
    }

    private List<String> toList(char[][] screenState) {
        ArrayList<String> lines = new ArrayList<>();

        for (int i = 0; i < screenState.length; i++) {
            char[] characters = screenState[i];
            String line = String.valueOf(characters);
            lines.add(line);
        }

        return lines;
    }
    private char[][] toCharArray(List<String> strings) {
        char[][] characters = new char[strings.size()][strings.get(0).length()];

        IntStream.range(0, strings.size())
                .forEach(i -> characters[i] = strings.get(i).toCharArray());

        return characters;
    }


    private List<String> lines(int w, int h) {
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

}