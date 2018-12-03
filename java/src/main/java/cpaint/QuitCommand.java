package cpaint;

import java.util.List;

public class QuitCommand implements Command{
    @Override
    public String representation() {
        return null;
    }

    @Override
    public List<String> lines() {
        return null;
    }

    @Override
    public String toString() {
        return "QuitCommand{}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        return o != null && getClass() == o.getClass();
    }
}
