package cpaint;

public class WarningScreen implements Screen{
    private final Screen previousScreen;
    private final String warningMessage;

    WarningScreen(Screen previousScreen) {
        this.previousScreen = previousScreen;
        this.warningMessage = "";
    }

    private WarningScreen(Screen previousScreen, String warningMessage) {
        this.previousScreen = previousScreen;
        this.warningMessage = warningMessage;
    }

    @Override
    public Screen execute(Command command) {
        return previousScreen.execute(command);
    }

    @Override
    public String render() {
        return warningMessage;
    }

    @Override
    public Screen representText(String description) {
        return new WarningScreen(previousScreen, description);
    }

    @Override
    public Screen drawLine(Line line) {
        return previousScreen.drawLine(line);
    }

    @Override
    public Screen drawRectangle(Rectangle rectangle) {
        return previousScreen.drawRectangle(rectangle);
    }

    @Override
    public Screen drawCanvas(Canvas canvas) {
        return previousScreen.drawCanvas(canvas);
    }
}
