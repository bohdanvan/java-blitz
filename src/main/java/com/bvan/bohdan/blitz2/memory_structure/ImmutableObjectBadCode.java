package com.bvan.bohdan.blitz2.memory_structure;

public class ImmutableObjectBadCode {

    public static void main(String[] args) {
        Integer x = 10;
        add(x);
        System.out.println(x); // 10
    }

    private static void add(Integer x) {
        x++;
    }
}
