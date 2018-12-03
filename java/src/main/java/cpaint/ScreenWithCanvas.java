package cpaint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class ScreenWithCanvas implements Screen {
    private final char[][] screenState;
    private final boolean isOn;

    public ScreenWithCanvas(Canvas canvas) {
        var lines = canvasRepresentedAsList(canvas.w, canvas.h);

        this.screenState = toCharArray(lines);
        this.isOn = true;
    }

    private ScreenWithCanvas(char[][] newScreenState) {
        this.screenState = newScreenState;
        this.isOn = true;
    }

    private ScreenWithCanvas(char[][] screenState, boolean isOn) {
        this.screenState = screenState;
        this.isOn = isOn;
    }

    @Override
    public String render() {
        return render(this.screenState);
    }

    @Override
    public Screen representText(String description) {
        return new WarningScreen(this).representText(description);
    }

    @Override
    public Screen drawLine(Line line) {
        var newScreenState = drawLineOn(line, 'x', screenState.clone());

        return new ScreenWithCanvas(newScreenState);
    }

    @Override
    public Screen drawRectangle(Rectangle rectangle) {
        return this
                .drawLine(new Line(rectangle.x1, rectangle.y1, rectangle.x1, rectangle.y2))
                .drawLine(new Line(rectangle.x1, rectangle.y2, rectangle.x2, rectangle.y2))
                .drawLine(new Line(rectangle.x2, rectangle.y1, rectangle.x2, rectangle.y2))
                .drawLine(new Line(rectangle.x1, rectangle.y1, rectangle.x2, rectangle.y1));
    }

    @Override
    public Screen drawCanvas(Canvas canvas) {
        return new ScreenWithCanvas(canvas);
    }

    @Override
    public Screen switchOff() {
        return new ScreenWithCanvas(this.screenState, false);
    }

    @Override
    public boolean isOn() {
        return isOn;
    }

    @Override
    public Screen execute(Command command) {
        return command.executeWith(this);
    }

    private String render(char[][] screenState) {
        return toList(screenState).stream()
                .collect(Collectors.joining(System.lineSeparator()));
    }

    private char[][] drawLineOn(Line line, char symbol, char[][] screenState) {
        IntStream.rangeClosed(line.y1, line.y2).forEach(y ->
                IntStream.rangeClosed(line.x1, line.x2)
                        .forEach(x -> screenState[y][x] = symbol)
        );

        return screenState;
    }

    private List<String> toList(char[][] screenState) {
        return Arrays.stream(screenState)
                .map(String::valueOf)
                .collect(Collectors.toList());
    }

    private char[][] toCharArray(List<String> strings) {
        char[][] characters = new char[strings.size()][strings.get(0).length()];

        IntStream.range(0, strings.size())
                .forEach(i -> characters[i] = strings.get(i).toCharArray());

        return characters;
    }


    private List<String> canvasRepresentedAsList(int w, int h) {
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

    @Override
    public String toString() {
        return "ScreenWithCanvas{" +
                "screenState=" + Arrays.toString(screenState) +
                '}';
    }
}
