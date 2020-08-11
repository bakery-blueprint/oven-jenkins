package com.github.bakery.oven;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationTest {

    @Test
    void load() {
        // no assert
    }

    @Test
    void main() {
        Application.main(new String[]{"hello"});
    }
}