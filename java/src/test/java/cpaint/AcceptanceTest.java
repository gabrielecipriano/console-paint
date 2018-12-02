package cpaint;

import org.junit.jupiter.api.Test;

import org.apache.tools.ant.filters.StringInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.function.Consumer;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class AcceptanceTest {
    @Test
    void spike_system_out() throws Exception {
        executeTrappingConsoleOutput(
                consoleOutput -> {

                    new ConsolePaint()
                            .executeWith(new Scanner(new StringInputStream("Gabriele")));

                    assertThat(consoleOutput.toString(), is("Hello Gabriele!"));
                });
    }

    private void executeTrappingConsoleOutput(Consumer<ByteArrayOutputStream> test) throws Exception {
        PrintStream originalOut = System.out;

        try (ByteArrayOutputStream consoleOutput = new ByteArrayOutputStream();
             PrintStream printStream = new PrintStream(consoleOutput))
        {
            System.setOut(printStream);

            test.accept(consoleOutput);

        } finally {
            System.setOut(originalOut);
        }
    }
}
