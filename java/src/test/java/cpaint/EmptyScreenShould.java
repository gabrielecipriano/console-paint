package cpaint;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EmptyScreenShould {

    @Test
    void draw_a_canvas() {
        var screenWithCanvas = new EmptyScreen()
                .execute(new Canvas(1, 2));

        assertThat(screenWithCanvas.render(), is(new ScreenWithCanvas(new Canvas(1, 2)).render()));
    }

    @Test
    void warn_user_of_an_unsupported_command() {
        var screen = new EmptyScreen()
                .execute(new UnsupportedCommand("this is the reason why I am unsupported"));

        assertThat(screen.render(), is("this is the reason why I am unsupported"));
    }

    @Test
    void warn_user_that_line_and_rectangle_is_only_represented_within_a_canvas() {
        assertThat(new EmptyScreen()
                        .execute(new Line(1, 2, 3, 4)).render(),
                is("Line command is supported only within a canvas"));

        assertThat(new EmptyScreen()
                        .execute(new Rectangle(1, 2, 3, 4)).render(),
                is("Rectangle command is supported only within a canvas"));
    }

    @Test
    void switch_off() {
        assertFalse(new EmptyScreen()
                .switchOff()
                .isOn());
    }

    @Test
    void should_be_on_after_a_non_quit_command() {
        assertTrue(new EmptyScreen()
                .execute(new Line(1, 2, 3, 4))
                .isOn());
        assertTrue(new EmptyScreen()
                .execute(new Rectangle(1, 2, 3, 4))
                .isOn());
        assertTrue(new EmptyScreen()
                .execute(new Canvas(1, 2))
                .isOn());
        assertTrue(new EmptyScreen()
                .execute(new UnsupportedCommand(""))
                .isOn());
    }
}