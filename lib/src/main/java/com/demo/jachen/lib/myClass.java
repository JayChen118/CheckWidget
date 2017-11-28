package com.demo.jachen.lib;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class myClass {
    public static final DateFormat HOUR_MINUTE = new SimpleDateFormat("HH:mm", Locale.CHINA);
    public static final DateFormat DATE_TIME = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);


    public static void main(String[] args) {
        Set<String> set = new HashSet<>();

        set.add("d");
        set.add("b");
        set.add(null);
        set.add("c");

        for (String s : set) {
            System.out.println(s);
        }
        try {
            String date = DATE_TIME.format(HOUR_MINUTE.parse("17:00"));
            System.out.println(date);

        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
