package com.demo.jachen.lib;

import java.util.HashSet;
import java.util.Set;

public class myClass {

    public static void main(String[] args) {
        Set<String> set = new HashSet<>();

        set.add("d");
        set.add("b");
        set.add(null);
        set.add("c");

        for (String s : set) {
            System.out.println(s);
        }


    }
}
