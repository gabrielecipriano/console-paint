package cpaint;

import java.util.Objects;

public class Rectangle implements Command{
    final int x1;
    final int y1;
    final int x2;
    final int y2;

    Rectangle(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    @Override
    public Screen executeWith(Screen screen) {
        return screen.drawRectangle(this);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x1, y1, x2, y2);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rectangle rectangle = (Rectangle) o;
        return x1 == rectangle.x1 &&
                y1 == rectangle.y1 &&
                x2 == rectangle.x2 &&
                y2 == rectangle.y2;
    }
}
