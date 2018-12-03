package cpaint;

import org.apache.tools.ant.filters.StringInputStream;

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

class DSLAcceptanceTests {

    static void check(Stream<String> inputs, Stream<String> outputs) throws IOException {
        executeTrappingSystemOut(
                (consoleOutput) -> {
                    new ConsolePaint(System.out::print, new CommandParser())
                            .executeWith(new Scanner(new StringInputStream(join(inputs))));

                    assertThat(consoleOutput.toString(), is(join(outputs)));
                });
    }

    static Stream<String> outputIs(String... outputs) {
        return Stream.of(outputs);
    }

    static Stream<String> withInput(String... inputs) {
        return Stream.of(inputs);
    }

    private static void executeTrappingSystemOut(Consumer<ByteArrayOutputStream> test) throws IOException {
        PrintStream originalOut = System.out;

        try (ByteArrayOutputStream consoleOutput = new ByteArrayOutputStream();
             PrintStream printStream = new PrintStream(consoleOutput)) {
            System.setOut(printStream);

            test.accept(consoleOutput);

        } finally {
            System.setOut(originalOut);
        }
    }

    private static String join(Stream<String> inputStream) {
        return inputStream.collect(joining(lineSeparator()));
    }
}
