package cpaint;

public class Canvas implements Command {
    private final int w;
    private final int h;

    public Canvas(int w, int h) {
        this.w = w;
        this.h = h;
    }

    @Override
    public String representation() {
        return "-----" + System.lineSeparator() +
                "|   |" + System.lineSeparator() +
                "|   |" + System.lineSeparator() +
                "-----";
    }
}
