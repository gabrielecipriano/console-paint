package cpaint;

import org.junit.jupiter.api.Test;

import static cpaint.DSLAcceptanceTests.*;

class GivenScenario {
    @Test
    void draw_a_canvas_a_line_a_rectangle_and_quit_the_program() throws Exception {
        check(withInput(
                "C 20 5",
                "L 1 3 7 3",
                "L 7 1 7 3",
                "R 15 2 20 5",
                "Q"),

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
                        "enter command: ",
                        "----------------------",
                        "|      x             |",
                        "|      x       xxxxxx|",
                        "|xxxxxxx       x    x|",
                        "|              x    x|",
                        "|              xxxxxx|",
                        "----------------------",
                        "enter command: "));
    }
}
