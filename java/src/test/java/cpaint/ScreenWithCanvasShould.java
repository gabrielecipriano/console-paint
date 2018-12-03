package cpaint;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

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
    @Disabled
    void draw_a_line_on_a_canvas() {
        var screen = new ScreenWithCanvas(new Canvas(3, 2))
                .drawLine(new Line(1, 2, 2, 2));

        assertThat(screen.render(), is(
                        "-----" + System.lineSeparator() +
                        "|   |" + System.lineSeparator() +
                        "|xx |" + System.lineSeparator() +
                        "-----"));
    }
}