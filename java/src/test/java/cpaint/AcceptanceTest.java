package cpaint;

import org.apache.tools.ant.filters.StringInputStream;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.stream.Stream;

import static java.lang.System.lineSeparator;
import static java.util.stream.Collectors.joining;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class AcceptanceTest {
    @Test
    void draw_a_canvas() throws Exception {
        check(withInput("C 20 5"),

                outputIs("enter command: ",
                        "----------------------",
                        "|                    |",
                        "|                    |",
                        "|                    |",
                        "|                    |",
                        "|                    |",
                        "----------------------",
                        "enter command: "));
    }

    @Test
    void draw_a_line_within_a_canvas() throws Exception {
        check(withInput(
                "C 20 5",
                "L 1 3 7 3"),

                outputIs("enter command: ",
                        "----------------------",
                        "|                    |",
                        "|                    |",
                        "|                    |",
                        "|                    |",
                        "|                    |",
                        "----------------------",
                        "enter command: ",
                        "----------------------",
                        "|                    |",
                        "|                    |",
                        "|xxxxxxx             |",
                        "|                    |",
                        "|                    |",
                        "----------------------",
                        "enter command: "
                        ));
    }

    private void check(Stream<String> inputs, Stream<String> outputs) throws IOException {
        executeTrappingSystemOut(
                (consoleOutput) -> {
                    new ConsolePaint(System.out::print, new CommandParser())
                            .executeWith(new Scanner(new StringInputStream(join(inputs))));


                    assertThat(consoleOutput.toString(), is(join(outputs)));
                });
    }

    private void executeTrappingSystemOut(Consumer<ByteArrayOutputStream> test) throws IOException {
        PrintStream originalOut = System.out;

        try (ByteArrayOutputStream consoleOutput = new ByteArrayOutputStream();
             PrintStream printStream = new PrintStream(consoleOutput)) {
            System.setOut(printStream);

            test.accept(consoleOutput);

        } finally {
            System.setOut(originalOut);
        }
    }

    private Stream<String> outputIs(String... outputs) {
        return Stream.of(outputs);
    }

    private Stream<String> withInput(String... inputs) {
        return Stream.of(inputs);
    }

    private String join(Stream<String> inputStream) {
        return inputStream.collect(joining(lineSeparator()));
    }
}
