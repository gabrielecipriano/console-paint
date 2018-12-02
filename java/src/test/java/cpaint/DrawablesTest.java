package cpaint;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class DrawablesTest {
    @Test
    void canvas() {
        Canvas canvas = new Canvas(2, 1);

        assertThat(canvas.representation(),
                is("----" + System.lineSeparator() +
                        "|  |" + System.lineSeparator() +
                        "----"
                ));

        Canvas biggerCanvas = new Canvas(3, 2);

        assertThat(biggerCanvas.representation(),
                is("-----" + System.lineSeparator() +
                                "|   |" + System.lineSeparator() +
                                "|   |" + System.lineSeparator() +
                                "-----"
                ));
    }
}