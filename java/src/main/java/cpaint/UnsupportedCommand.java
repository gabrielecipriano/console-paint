package cpaint;

import java.util.List;
import java.util.Objects;

public class UnsupportedCommand implements Command{
    private String description;

    public UnsupportedCommand(String description) {
        this.description = description;
    }

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
        return "UnsupportedCommand{" +
                "description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UnsupportedCommand that = (UnsupportedCommand) o;
        return Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description);
    }
}
