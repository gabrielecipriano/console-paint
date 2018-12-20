package cpaint;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class DrawableScreen implements Screen {
    private final char[][] screenState;
    private final boolean isOn;
    private Screen previousScreen;

    DrawableScreen(Canvas canvas) {
        this.previousScreen = new EmptyScreen();
        this.isOn = true;
        var characters = charArrayFor(canvas);
        this.screenState = this.initialiseState(characters);
        drawInitialCanvas(canvas);
    }

    private DrawableScreen(char[][] newScreenState, Screen previousScreen) {
        this.screenState = newScreenState;
        this.previousScreen = previousScreen;
        this.isOn = true;
    }

    private DrawableScreen(char[][] screenState, boolean isOn, Screen previousScreen) {
        this.screenState = screenState;
        this.isOn = isOn;
        this.previousScreen = previousScreen;
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
        return drawLineWith(line, 'x');
    }

    @Override
    public Screen drawRectangle(Rectangle rectangle) {
        return drawRectangleWith(rectangle, 'x', 'x');
    }

    @Override
    public Screen drawCanvas(Canvas canvas) {
        return new DrawableScreen(canvas);
    }

    @Override
    public Screen switchOff() {
        return new DrawableScreen(this.screenState, false, this);
    }

    @Override
    public boolean isOn() {
        return isOn;
    }

    @Override
    public Screen undo() {
        return previousScreen;
    }

    @Override
    public Screen execute(Command command) {
        return command.executeWith(this);
    }

    private char[][] charArrayFor(Canvas canvas) {
        return new char[canvas.h + 2][canvas.w + 2];
    }

    private DrawableScreen drawLineWith(Line line, char symbol) {
        char[][] clone = Arrays.copyOf(screenState, screenState.length);

        char[][] newScreenState = drawLineOn(line, symbol, clone);

        return new DrawableScreen(newScreenState, this);
    }

    private void drawInitialCanvas(Canvas canvas) {
        this.drawRectangleWith(new Rectangle(0, 0, canvas.w + 1, canvas.h + 1), '|', '-');
    }

    private DrawableScreen drawRectangleWith(Rectangle rectangle, char verticalSymbol, char horizontalSymbol) {
        return this
                .drawLineWith(new Line(rectangle.x1, rectangle.y1, rectangle.x1, rectangle.y2), verticalSymbol)
                .drawLineWith(new Line(rectangle.x2, rectangle.y1, rectangle.x2, rectangle.y2), verticalSymbol)
                .drawLineWith(new Line(rectangle.x1, rectangle.y2, rectangle.x2, rectangle.y2), horizontalSymbol)
                .drawLineWith(new Line(rectangle.x1, rectangle.y1, rectangle.x2, rectangle.y1), horizontalSymbol);
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

    private char[][] initialiseState(char[][] emptyCharArray) {
        IntStream.range(0, emptyCharArray[0].length).forEach(y ->
                IntStream.range(0, emptyCharArray.length)
                        .forEach(x -> emptyCharArray[x][y] = ' ')
        );

        return emptyCharArray;
    }

    private List<String> toList(char[][] screenState) {
        return Arrays.stream(screenState)
                .map(String::valueOf)
                .collect(Collectors.toList());
    }
}
