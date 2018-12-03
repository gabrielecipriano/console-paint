package cpaint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class ScreenWithCanvas implements Screen {
    private char[][] screenState;

    public ScreenWithCanvas(Canvas canvas) {
        var lines = lines(3, 2);

        screenState = toCharArray(lines);
    }

    @Override
    public String render() {
        return toList(screenState).stream().collect(Collectors.joining(System.lineSeparator()));
    }

    @Override
    public Screen representText(String description) {
        return null;
    }

    @Override
    public Screen drawLine() {
        return null;
    }

    @Override
    public Screen drawRectangle() {
        return null;
    }

    @Override
    public Screen drawCanvas(Canvas canvas) {
        return null;
    }

    @Override
    public Screen execute(Command command) {
        return null;
    }


    public ScreenWithCanvas drawLine(Line line) {
        return null;
    }

    private List<String> toList(char[][] screenState) {
        ArrayList<String> lines = new ArrayList<>();

        for (int i = 0; i < screenState.length; i++) {
            char[] characters = screenState[i];
            String line = String.valueOf(characters);
            lines.add(line);
        }

        return lines;
    }

    private char[][] toCharArray(List<String> strings) {
        char[][] characters = new char[strings.size()][strings.get(0).length()];

        IntStream.range(0, strings.size())
                .forEach(i -> characters[i] = strings.get(i).toCharArray());

        return characters;
    }


    private List<String> lines(int w, int h) {
        var horizontalSide = line("-", w + 2);
        var verticalSide = "|" + line(" ", w) + "|";

        return buildWithBorders(horizontalSide, verticalSide, h);
    }


    private List<String> buildWithBorders(String horizontalSide, String verticalSide, int h) {
        var lines = new ArrayList<String>();
        lines.add(horizontalSide);
        IntStream.range(0, h).forEach(i -> lines.add(verticalSide));
        lines.add(horizontalSide);
        return lines;
    }


    private String line(final String symbol, int count) {
        return symbol.repeat(count);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScreenWithCanvas that = (ScreenWithCanvas) o;
        return Arrays.equals(screenState, that.screenState);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(screenState);
    }
}