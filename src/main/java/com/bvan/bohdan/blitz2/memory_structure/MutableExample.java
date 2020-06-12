package com.bvan.bohdan.blitz2.memory_structure;

import java.util.ArrayList;
import java.util.List;

public class MutableExample {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        add10(list);
        Integer x = list.get(0);
        x = x + 1;
        list.set(0, x);
        list.add(20);
        System.out.println(list); // [11, 20]
    }

    private static void add10(List<Integer> list) {
        list.add(10);
    }
}
