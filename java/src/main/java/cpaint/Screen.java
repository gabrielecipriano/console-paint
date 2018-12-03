package cpaint;

public interface Screen {
    Screen execute(Command command);

    String render();

    Screen representText(String description);

    Screen drawLine(Line line);

    Screen drawRectangle(Rectangle rectangle);

    Screen drawCanvas(Canvas canvas);
}
