package cpaint;

import static java.lang.String.format;

public class EmptyScreen implements Screen {
    private static final String INITIAL_REPRESENTATION = "";
    private final String representation;
    private final boolean isOn;

    public EmptyScreen() {
        this.representation = INITIAL_REPRESENTATION;
        this.isOn = true;
    }

    private EmptyScreen(String representation) {
        this.representation = representation;
        this.isOn = true;
    }

    private EmptyScreen(String representation, boolean isOn) {
        this.representation = representation;
        this.isOn = isOn;
    }

    @Override
    public Screen execute(Command command) {
        return command.executeWith(this);
    }

    @Override
    public String render() {
        return representation;
    }

    @Override
    public Screen representText(String description) {
        return new EmptyScreen(description);
    }

    @Override
    public Screen drawLine(Line line) {
        return warnUnsupported("Line");
    }

    @Override
    public Screen drawRectangle(Rectangle rectangle) {
        return warnUnsupported("Rectangle");
    }

    @Override
    public Screen drawCanvas(Canvas canvas) {
        return new ScreenWithCanvas(canvas);
    }

    @Override
    public Screen switchOff() {
        return new EmptyScreen(this.representation, false);
    }

    @Override
    public boolean isOn() {
        return isOn;
    }

    private Screen warnUnsupported(String unsupportedCommand) {
        return representText(format("%s command is supported only within a canvas", unsupportedCommand));
    }
}
