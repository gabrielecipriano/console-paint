package cpaint;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class AcceptanceTest {
    @Test
    void should_pass() {
        assertThat(true, is(true));
    }
}
