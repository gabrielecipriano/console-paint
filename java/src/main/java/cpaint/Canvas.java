package cpaint;

import java.util.Objects;

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
