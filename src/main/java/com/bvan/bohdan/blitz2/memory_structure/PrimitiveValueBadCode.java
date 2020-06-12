package com.bvan.bohdan.blitz2.memory_structure;

public class PrimitiveValueBadCode {

    public static void main(String[] args) {
        int x = 10;
        add(x);
        System.out.println(x); // 10
    }

    private static void add(int y) {
        y++;
    }
}
