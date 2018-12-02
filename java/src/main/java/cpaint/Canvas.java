package cpaint;

import java.util.Objects;

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
