package cpaint;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.IntStream;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toMap;

class CommandParser {
    private static final String WHITESPACE = " ";

    Command interpret(String input) {
        if (input.startsWith("C")) {
            return matchInputWith(input,
                    "C w h",
                    (map) -> new Canvas(toInt(map.get("w")), toInt(map.get("h"))));
        } else {
            return matchInputWith(input,
                    "L x1 y1 x2 y2",
                    (map) -> new Line(
                            toInt(map.get("x1")), toInt(map.get("y1")),
                            toInt(map.get("x2")), toInt(map.get("y2"))));
        }
    }

    private Command matchInputWith(String input, String canvasPattern, Function<Map<String, String>, Command> commandBuilder) {
        return commandBuilder.apply(compilePattern(input, canvasPattern));
    }

    private Map<String, String> compilePattern(String input, String canvasPattern) {
        var canvasComponents = asList(canvasPattern.split(WHITESPACE));
        var inputComponents = asList(input.split(WHITESPACE));

        return IntStream.range(0, inputComponents.size())
                .boxed()
                .collect(toMap(canvasComponents::get, inputComponents::get));
    }

    private Integer toInt(String w) {
        return Integer.valueOf(w);
    }
}
