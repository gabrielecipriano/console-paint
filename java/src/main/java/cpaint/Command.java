package cpaint;

import java.util.List;

public interface Command {
    String representation();

    List<String> lines();
}
