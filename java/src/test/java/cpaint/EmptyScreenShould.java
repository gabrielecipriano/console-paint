package cpaint;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

class EmptyScreenShould {

    @Test
    void execute_unsupported_command() {
        Screen screen = new EmptyScreen()
                .execute(new UnsupportedCommand("this is the reason why I am unsupported"));

        assertThat(screen.representation(), is("this is the reason why I am unsupported"));
    }
}