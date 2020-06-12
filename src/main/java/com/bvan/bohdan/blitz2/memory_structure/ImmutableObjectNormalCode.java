package com.bvan.bohdan.blitz2.memory_structure;

public class ImmutableObjectNormalCode {

    public static void main(String[] args) {
        Integer x = 10;
        x = add(x);
        System.out.println(x); // 11
    }

    private static Integer add(Integer x) {
        return x + 1;
    }
}
