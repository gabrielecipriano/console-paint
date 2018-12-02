package cpaint;

import org.apache.tools.ant.filters.StringInputStream;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class AcceptanceTest {
    @Test
    void spike_system_out() throws Exception {
        PrintStream originalOut = System.out;

        try (ByteArrayOutputStream consoleOutput = new ByteArrayOutputStream();
             PrintStream printStream = new PrintStream(consoleOutput)) {
            System.setOut(printStream);

            new ConsolePaint()
                    .executeWith(new Scanner(
                            new StringInputStream(
                                    "Gabriele" + System.lineSeparator() +
                                            "Giovanni")));

            assertThat(consoleOutput.toString(), is(
                    "What's your name?: " + System.lineSeparator() +
                    "Hello Gabriele!" + System.lineSeparator() +
                    "What's your name again?: " + System.lineSeparator() +
                    "Oh ok Giovanni, nice to meet you"));

        } finally {
            System.setOut(originalOut);
        }
    }
}
