package cpaint;

import org.junit.jupiter.api.Test;

import static cpaint.DSLAcceptanceTests.*;

class EdgeCases {
    @Test
    void fail_to_parse_unexpected_command_but_resume() throws Exception {
        check(withInput(
                "C 20 5",
                "Z 2 1 3",
                "L 1 1 2 1"),

                outputIs("enter command: ",
                        "----------------------",
                        "|                    |",
                        "|                    |",
                        "|                    |",
                        "|                    |",
                        "|                    |",
                        "----------------------",
                        "enter command: ",
                        "Command descriptor {Z} does not match any known",
                        "enter command: ",
                        "----------------------",
                        "|xx                  |",
                        "|                    |",
                        "|                    |",
                        "|                    |",
                        "|                    |",
                        "----------------------",
                        "enter command: "));
    }

    @Test
    void resize_and_clear_canvas() throws Exception {
        check(withInput(
                "C 20 5",
                "L 1 3 7 3",
                "C 12 5"),

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
                        "--------------",
                        "|            |",
                        "|            |",
                        "|            |",
                        "|            |",
                        "|            |",
                        "--------------",
                        "enter command: "));
    }
}
