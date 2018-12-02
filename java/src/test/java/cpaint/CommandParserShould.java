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
    void fail_to_parse_unexpected_command() {
        assertThat(commandParser.interpret("Z 2 1 3"), is(new UnrecognizedCommand()));
    }
}