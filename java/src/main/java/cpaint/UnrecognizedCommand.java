package cpaint;

public class UnrecognizedCommand implements Command{
    @Override
    public String representation() {
        return null;
    }

    @Override
    public String toString() {
        return "UnrecognizedCommand{}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        return o != null && getClass() == o.getClass();
    }
}
