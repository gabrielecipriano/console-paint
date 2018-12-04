package cpaint;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class CommandParserShould {

    private final CommandParser commandParser = new CommandParser();

    @Test
    void recognise_expected_commands() {
        assertThat(commandParser.interpret("C 11 1"), is(new Canvas(11, 1)));
        assertThat(commandParser.interpret("L 1 3 7 3"), is(new Line(1, 3, 7, 3)));
        assertThat(commandParser.interpret("R 2 5 6 7"), is(new Rectangle(2, 5, 6, 7)));
        assertThat(commandParser.interpret("Q"), is(new QuitCommand()));
    }

    @Test
    void fail_to_parse_expected_command_with_wrong_patterns() {
        assertThat(commandParser.interpret("C 11 1 2"), is(
                new UnsupportedCommand("Command descriptor {C} follows the following pattern: 'C w h'")));

        assertThat(commandParser.interpret("L 1 3 7"), is(
                new UnsupportedCommand("Command descriptor {L} follows the following pattern: 'L x1 y1 x2 y2'")));

        assertThat(commandParser.interpret("R 1 2 3"), is(
                new UnsupportedCommand("Command descriptor {R} follows the following pattern: 'R x1 y1 x2 y2'")));

        assertThat(commandParser.interpret("Q 23"), is(
                new UnsupportedCommand("Command descriptor {Q} follows the following pattern: 'Q'")));
    }

    @Test
    void fail_to_parse_unexpected_command() {
        assertThat(commandParser.interpret("Z 2 1 3"), is(
                new UnsupportedCommand("Command descriptor {Z} does not match any known")));
        assertThat(commandParser.interpret("A"), is(
                new UnsupportedCommand("Command descriptor {A} does not match any known")));
    }
}