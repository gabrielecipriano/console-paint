package cpaint;

public class ConsoleScreen {
    public ScreenState draw(Command command, ScreenState screenState) {
        return screenState.add(command);
    }
}
