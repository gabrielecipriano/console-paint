package cpaint;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class CommandParserShould {
    @Test
    void recognise_a_canvas() {
        assertThat(new CommandParser().interpret("C 11 1"), is(new Canvas(11, 1)));
        assertThat(new CommandParser().interpret("C 20003 12201"), is(new Canvas(20003, 12201)));
    }

    @Test
    void recognise_a_line() {
        assertThat(new CommandParser().interpret("L 1 3 7 3"), is(new Line(1, 3, 7, 3)));
    }

    @Test
    void recognise_a_rectangle() {
        assertThat(new CommandParser().interpret("R 2 5 6 7"), is(new Rectangle(2, 5, 6, 7)));
    }

    @Test
    void recognise_quit_command() {
        assertThat(new CommandParser().interpret("Q"), is(new QuitCommand()));
    }
}