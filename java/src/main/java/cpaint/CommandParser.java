package cpaint;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.IntStream;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toMap;

public class CommandParser {

    public Command interpret(String input) {
        return matchInputWith(input,
                "C w h", (map) -> new Canvas(toInt(map.get("w")), toInt(map.get("h"))));
    }

    public Command matchInputWith(String input, String canvasPattern, Function<Map<String, String>, Command> commandBuilder) {
        return commandBuilder.apply(compilePattern(input, canvasPattern));
    }

    public Map<String, String> compilePattern(String input, String canvasPattern) {
        List<String> canvasComponents = asList(canvasPattern.split(" "));
        List<String> inputComponents = asList(input.split(" "));

        return IntStream.range(0, inputComponents.size())
                .boxed()
                .collect(toMap(canvasComponents::get, inputComponents::get));
    }

    public Integer toInt(String w) {
        return Integer.valueOf(w);
    }
}
