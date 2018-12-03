package cpaint;

import java.util.ArrayList;
import java.util.List;

public class ScreenState {
    private ArrayList<Command> commands;

    public ScreenState(ArrayList<Command> commands) {
        this.commands = commands;
    }

    private ScreenState() {

    }

    public void representWith(Screen screen) {
        List<String> lines;

        var canvas = commands.get(0);

        lines = canvas.lines();

        var lineOnCanvas = commands.get(1);

        List<String> newLines = merge(lines, lineOnCanvas.lines());
    }

    private List<String> merge(List<String> lines, List<String> newLines) {
        return null;
    }

    public static ScreenState emptyScreen() {
        return new ScreenState(new ArrayList<>());
    }

    public ScreenState add(Command command) {
        this.commands.add(command);

        return new ScreenState(new ArrayList<>(commands));
    }
}
