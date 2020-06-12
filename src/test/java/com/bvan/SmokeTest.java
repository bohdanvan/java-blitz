package com.bvan;

import static org.assertj.core.api.Assertions.assertThat;

import com.bvan.smoke.Smoke;
import org.junit.jupiter.api.Test;

class SmokeTest {

    @Test
    void smokeTest() {
        assertThat(new Smoke().foo())
                .isEqualTo("foo");
    }
}
