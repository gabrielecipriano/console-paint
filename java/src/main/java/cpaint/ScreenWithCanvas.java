package cpaint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class ScreenWithCanvas implements Screen {
    private Character[][] screenState;

    public ScreenWithCanvas(Canvas canvas) {
//        this.screenState = toBidimensionalCharArray(canvas);
    }

    @Override
    public String render() {
        return toList(this.screenState).stream().collect(Collectors.joining(System.lineSeparator()));
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


    private List<String> toList(Character[][] screenState) {
        ArrayList<String> lines = new ArrayList<>();

        for (int i = 0; i < screenState.length; i++) {
            Character[] characters = screenState[i];
            String line = Arrays.toString(characters);
            lines.add(line);
        }

        return lines;
    }

    private Character[][] toBidimensionalCharArray(Canvas canvas) {
        int w = canvas.w + 2;
        int h = canvas.h + 2;

        Character[][] characters = new Character[w][h];

        List<String> lines = lines(canvas.w, canvas.h);

        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                char c = lines.get(j).charAt(i);
                characters[j][i] = c;
            }
        }

        return characters;
    }

    public List<String> lines(int w, int h) {
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

    public ScreenWithCanvas drawLine(Line line) {
        return null;
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
