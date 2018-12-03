package cpaint;

import org.junit.jupiter.api.Test;

import static cpaint.DSLAcceptanceTests.*;

class GivenScenarios {
    @Test
    void draw_a_canvas() throws Exception {
        check(withInput("C 20 5"),

                outputIs("enter command: ",
                        "----------------------",
                        "|                    |",
                        "|                    |",
                        "|                    |",
                        "|                    |",
                        "|                    |",
                        "----------------------",
                        "enter command: "));
    }

    @Test
    void draw_a_line_within_a_canvas() throws Exception {
        check(withInput(
                "C 20 5",
                "L 1 3 7 3",
                "L 7 1 7 3"),

                outputIs("enter command: ",
                        "----------------------",
                        "|                    |",
                        "|                    |",
                        "|                    |",
                        "|                    |",
                        "|                    |",
                        "----------------------",
                        "enter command: ",
                        "----------------------",
                        "|                    |",
                        "|                    |",
                        "|xxxxxxx             |",
                        "|                    |",
                        "|                    |",
                        "----------------------",
                        "enter command: ",
                        "----------------------",
                        "|      x             |",
                        "|      x             |",
                        "|xxxxxxx             |",
                        "|                    |",
                        "|                    |",
                        "----------------------",
                        "enter command: "));
    }

    @Test
    void draw_a_rectangle_within_a_canvas() throws Exception {
        check(withInput(
                "C 20 5",
                "R 15 2 20 5"),

                outputIs("enter command: ",
                        "----------------------",
                        "|                    |",
                        "|                    |",
                        "|                    |",
                        "|                    |",
                        "|                    |",
                        "----------------------",
                        "enter command: ",
                        "----------------------",
                        "|                    |",
                        "|              xxxxxx|",
                        "|              x    x|",
                        "|              x    x|",
                        "|              xxxxxx|",
                        "----------------------",
                        "enter command: "));
    }
}
