package cpaint;

public interface Screen {
    Screen execute(Command command);

    String representation();

    Screen representText(String description);
}
