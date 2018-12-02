package cpaint;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toMap;

class CommandParser {
    private static final String WHITESPACE = " ";

    Command interpret(String input) {
        return buildCommandWith(input, rules(
                new PatternRule("C w h", (map)
                        -> new Canvas(toInt(map.get("w")), toInt(map.get("h")))),

                new PatternRule("L x1 y1 x2 y2", (map)
                        -> new Line(
                        toInt(map.get("x1")), toInt(map.get("y1")),
                        toInt(map.get("x2")), toInt(map.get("y2")))),

                new PatternRule("R x1 y1 x2 y2", (map)
                        -> new Rectangle(
                        toInt(map.get("x1")), toInt(map.get("y1")),
                        toInt(map.get("x2")), toInt(map.get("y2"))))
        ));
    }

    private Command buildCommandWith(String input, Map<Character, PatternRule> stringPatternRuleMap) {
        char commandDescriptor = input.charAt(0);

        return matchInputWith(input,
                stringPatternRuleMap.get(commandDescriptor).pattern,
                stringPatternRuleMap.get(commandDescriptor).commandBuilder);
    }

    private Map<Character, PatternRule> rules(PatternRule ... patternRules) {
        return Stream.of(patternRules)
                .collect(toMap(PatternRule::commandDescriptor, (pr) -> pr));
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

    private class PatternRule {
        final String pattern;
        final Function<Map<String, String>, Command> commandBuilder;

        public PatternRule(String pattern, Function<Map<String, String>, Command> commandBuilder) {
            this.pattern = pattern;
            this.commandBuilder = commandBuilder;
        }

        Character commandDescriptor() {
            return pattern.charAt(0);
        }
    }
}
