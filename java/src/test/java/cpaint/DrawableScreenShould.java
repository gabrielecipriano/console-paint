package cpaint;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DrawableScreenShould {

    @Test
    void draw_a_canvas() {
        var screen = new DrawableScreen(new Canvas(3, 2));

        assertThat(screen.render(), is(
                        "-----" + System.lineSeparator() +
                        "|   |" + System.lineSeparator() +
                        "|   |" + System.lineSeparator() +
                        "-----"));
    }

    @Test
    void draw_an_horizontal_line_on_a_canvas() {
        var screen = new DrawableScreen(new Canvas(3, 2))
                .execute(new Line(1, 2, 2, 2));

        assertThat(screen.render(), is(
                        "-----" + System.lineSeparator() +
                        "|   |" + System.lineSeparator() +
                        "|xx |" + System.lineSeparator() +
                        "-----"));
    }

    @Test
    void draw_a_vertical_line_on_a_canvas() {
        var screen = new DrawableScreen(new Canvas(3, 2))
                .execute(new Line(1, 1, 1, 2));

        assertThat(screen.render(), is(
                        "-----" + System.lineSeparator() +
                        "|x  |" + System.lineSeparator() +
                        "|x  |" + System.lineSeparator() +
                        "-----"));
    }

    @Test
    void draw_a_rectangle_on_a_canvas() {
        var screen = new DrawableScreen(new Canvas(3, 3))
                .execute(new Rectangle(1, 1, 3, 3));

        assertThat(screen.render(), is(
                        "-----" + System.lineSeparator() +
                        "|xxx|" + System.lineSeparator() +
                        "|x x|" + System.lineSeparator() +
                        "|xxx|" + System.lineSeparator() +
                        "-----"));
    }

    @Test
    void draw_a_new_canvas() {
        var screen = new DrawableScreen(new Canvas(3, 3))
                .execute(new Line(1, 1, 1, 2))
                .execute(new Canvas(2, 1));

        assertThat(screen.render(), is(
                        "----" + System.lineSeparator() +
                        "|  |" + System.lineSeparator() +
                        "----"));
    }

    @Test
    void warn_user_with_unsupported_command() {
        var screen = new DrawableScreen(new Canvas(1, 1))
                .execute(new UnsupportedCommand("this is the reason why I am unsupported"));

        assertThat(screen.render(), is("this is the reason why I am unsupported"));
    }

    @Test
    void resume_with_previous_state_after_a_warning() {
        var screen = new DrawableScreen(new Canvas(3, 2))
                .execute(new UnsupportedCommand("warning message"))
                .execute(new Line(1, 1, 1, 2));

        assertThat(screen.render(), is(
                        "-----" + System.lineSeparator() +
                        "|x  |" + System.lineSeparator() +
                        "|x  |" + System.lineSeparator() +
                        "-----"));
    }

    @Test
    void switch_off() {
        assertFalse(new DrawableScreen(new Canvas(1, 1))
                .execute(new QuitCommand())
                .isOn());
    }

    @Test
    void should_be_on_after_a_non_quit_command() {
        assertTrue(new DrawableScreen(new Canvas(8, 8))
                .execute(new Line(1,2,3,4))
                .isOn());
        assertTrue(new DrawableScreen(new Canvas(8, 8))
                .execute(new Rectangle(1,2,3,4))
                .isOn());
        assertTrue(new DrawableScreen(new Canvas(8, 8))
                .execute(new Canvas(1,2))
                .isOn());
        assertTrue(new DrawableScreen(new Canvas(8, 8))
                .execute(new UnsupportedCommand(""))
                .isOn());
    }
}