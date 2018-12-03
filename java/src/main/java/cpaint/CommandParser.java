package cpaint;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.lang.String.format;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toMap;

class CommandParser {
    private static final String WHITESPACE = " ";

    Command interpret(String input) {
        return match(input, rules(
                new PatternRule("Q", map -> new QuitCommand()),

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

    private Command match(String input, Map<Character, PatternRule> rules) {
        char commandDescriptor = commandDescriptor(input);

        return Optional.ofNullable(rules.get(commandDescriptor))
                .map((rule) ->
                        buildCommandWith(input, rule.pattern, rule.commandBuilder))
                .orElse(new UnsupportedCommand(format("Command descriptor {%s} does not match any known", commandDescriptor)));
    }

    private Command buildCommandWith(String input, String pattern, Function<Map<String, String>, Command> commandBuilder) {
        List<String> inputComponents = asList(input.split(WHITESPACE));
        List<String> patternComponent = asList(pattern.split(WHITESPACE));

        if (inputComponents.size() != patternComponent.size()) {
            return new UnsupportedCommand(
                    format("{%s} command descriptor follows the following pattern: '%s'", commandDescriptor(input), pattern));
        }

        return commandBuilder.apply(compilePattern(patternComponent, inputComponents));
    }

    private char commandDescriptor(String input) {
        return input.charAt(0);
    }

    private Map<Character, PatternRule> rules(PatternRule... patternRules) {
        return Stream.of(patternRules)
                .collect(toMap(PatternRule::commandDescriptor, (pr) -> pr));
    }

    private Map<String, String> compilePattern(List<String> patternComponent, List<String> inputComponents) {
        return IntStream.range(0, inputComponents.size())
                .boxed()
                .collect(toMap(patternComponent::get, inputComponents::get));
    }

    private Integer toInt(String w) {
        return Integer.valueOf(w);
    }

    private class PatternRule {
        final String pattern;
        final Function<Map<String, String>, Command> commandBuilder;

        PatternRule(String pattern, Function<Map<String, String>, Command> commandBuilder) {
            this.pattern = pattern;
            this.commandBuilder = commandBuilder;
        }

        Character commandDescriptor() {
            return pattern.charAt(0);
        }
    }
}
