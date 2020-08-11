package com.github.bakery.oven;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


class CaculatorTest {
    Caculator calculator = new Caculator();

    @Test
    void add() {
        long result = calculator.add(1, 1);
        assertEquals(result,2);
    }

    @Test
    void sub() {
        long result = calculator.sub(5, 100);
        assertEquals(result,-95L);
    }

    @Test
    void mul() {
        long result = calculator.mul(5, 100);
        assertEquals(result,500);

    }

    @Test
    void div() {
        long result = calculator.div(100, 5);
        assertEquals(result,20);

    }


    @Test
    void 영으로_나누면_에러(){
        try{
            calculator.div(1, 0);
            fail("Expected ArithmeticException");
        } catch(IllegalArgumentException e) {
        }
    }


}