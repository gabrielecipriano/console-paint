package cpaint;

public class UndoCommand implements Command{
    @Override
    public Screen executeWith(Screen screen) {
        return screen.representText("Undo command not yet implemented");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        return o != null && getClass() == o.getClass();
    }

    @Override
    public String toString() {
        return "UndoCommand{}";
    }
}
