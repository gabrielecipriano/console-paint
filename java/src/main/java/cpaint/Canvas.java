package cpaint;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Canvas implements Command {
    public final int w;
    public final int h;

    Canvas(int w, int h) {
        this.w = w;
        this.h = h;
    }

    @Override
    public Screen executeWith(Screen screen) {
        return screen.drawCanvas(this);
    }

    private List<String> buildWithBorders(String horizontalSide, String verticalSide) {
        var lines = new ArrayList<String>();
        lines.add(horizontalSide);
        IntStream.range(0, h).forEach(i -> lines.add(verticalSide));
        lines.add(horizontalSide);
        return lines;
    }

    private String line(final String symbol, int count) {
        return symbol.repeat(count);
    }

    private String join(List<String> draw) {
        return draw.stream().collect(Collectors.joining(System.lineSeparator()));
    }

    @Override
    public String toString() {
        return "Canvas{" +
                "w=" + w +
                ", h=" + h +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Canvas canvas = (Canvas) o;
        return w == canvas.w &&
                h == canvas.h;
    }

    @Override
    public int hashCode() {
        return Objects.hash(w, h);
    }
}
