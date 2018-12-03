package cpaint;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class EmptyScreenShould {

    @Test
    void execute_unsupported_command() {
        Screen screen = new EmptyScreen()
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
}