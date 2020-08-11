package com.github.bakery.oven;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CalculatorTest {

    @Test
    void add() {
        // given
        final Calculator calculator = new Calculator();

        // when
        final long result = calculator.add(1, 1);

        // then
        assertThat(result).isEqualTo(2L);
    }

    @Test
    void sub() {
        // given
        final Calculator calculator = new Calculator();

        // when
        final long result = calculator.sub(5, 100);

        // then
        assertThat(result).isEqualTo(-95L);
    }

    @Test
    void mul() {
        // given
        final Calculator calculator = new Calculator();

        // when
        final long result = calculator.mul(5, 100);

        // then
        assertThat(result).isEqualTo(500);
    }

    @Test
    void div() {
        // given
        final Calculator calculator = new Calculator();

        // when
        final long result = calculator.div(100, 5);

        // then
        assertThat(result).isEqualTo(20);
    }

    @Test
    void divThrowException() {
        // given
        final Calculator calculator = new Calculator();

        // when , then
        Assertions.assertThrows(IllegalArgumentException.class, () -> calculator.div(1, 0));
    }
}