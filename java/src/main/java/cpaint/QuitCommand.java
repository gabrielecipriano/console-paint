package cpaint;

public class QuitCommand implements Command{

    @Override
    public Screen executeWith(Screen screen) {
        return screen.switchOff();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        return o != null && getClass() == o.getClass();
    }
}
