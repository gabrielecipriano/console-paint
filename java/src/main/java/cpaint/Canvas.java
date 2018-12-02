package cpaint;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Arrays.asList;

public class Canvas implements Command {
    private final int w;
    private final int h;

    public Canvas(int w, int h) {
        this.w = w;
        this.h = h;
    }

    @Override
    public String representation() {
        var horizontalSide = line("-");
        var verticalSide = "|   |";

        var drawLines = asList(
                horizontalSide,
                verticalSide,
                verticalSide,
                horizontalSide);


        return join(drawLines);
    }

    public String line(final String symbol) {
        return symbol + "----";
    }

    public String join(List<String> draw) {
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
