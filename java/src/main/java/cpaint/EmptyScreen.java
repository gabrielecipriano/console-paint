package cpaint;

import static java.lang.String.format;

public class EmptyScreen implements Screen {
    private final String representation;

    public EmptyScreen() {
        this.representation = "";
    }

    private EmptyScreen(String representation) {
        this.representation = representation;
    }

    @Override
    public Screen execute(Command command) {
        return command.executeWith(this);
    }

    @Override
    public String representation() {
        return representation;
    }

    @Override
    public Screen representText(String description) {
        return new EmptyScreen(description);
    }

    @Override
    public Screen drawLine() {
        return warnUnsupported("Line");
    }

    @Override
    public Screen drawRectangle() {
        return warnUnsupported("Rectangle");
    }

    private Screen warnUnsupported(String unsupportedCommand) {
        return representText(format("%s command is supported only within a canvas", unsupportedCommand));
    }
}
