package com.github.bakery.oven;

public class Calculator {
    public long add(int a, int b) {
        return a + b;
    }

    public int sub(int a, int b) {
        return a - b;
    }

    public long mul(int a, int b) {
        return a * b;
    }

    public int div(int a, int b) {
        if (b == 0) {
            throw new IllegalArgumentException("a , b" + a + b);
        }
        return a / b;
    }
}
