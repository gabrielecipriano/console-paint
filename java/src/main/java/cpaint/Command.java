package cpaint;

public interface Command {
    Screen executeWith(Screen screen);
}
