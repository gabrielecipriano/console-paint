package cpaint;

public interface Command {
    String representation();

    Screen executeWith(Screen screen);
}
