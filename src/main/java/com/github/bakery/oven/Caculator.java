package com.github.bakery.oven;

public class Caculator{
    public int add(int a, int b){
        return a+b;
    }
    public int sub(int a,int b){
        return a-b;
    }
    public int mul(int a, int b){
        return a*b;
    }
    public int div(int a, int b){
        if(b==0){
            throw new IllegalArgumentException("0오면 안됨!!");
        }
        return a/b;
    }
}
