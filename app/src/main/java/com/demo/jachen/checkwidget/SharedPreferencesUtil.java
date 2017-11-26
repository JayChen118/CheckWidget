package com.demo.jachen.checkwidget;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by jachen on 10/31/2017.
 */

public class SharedPreferencesUtil {

    public static final String RECORD = "record";

    public static void storeRecord(String records) {
        getSharedPreferences().edit().putString(RECORD, records).apply();
    }


    public static SharedPreferences getSharedPreferences() {
        return getSharedPreferences("check");
    }


    public static SharedPreferences getSharedPreferences(String name) {
        return getContext().getSharedPreferences(name, Context.MODE_PRIVATE);
    }

    public static String readRecord() {
        return getSharedPreferences().getString(RECORD, "");
    }

    private static Context getContext() {
        return CheckApplication.getInstance();
    }

    private static String getBookCheckName(String book) {
        return String.format("book_%s_%s", book, TimeUtil.getYearMonth());
    }

    private static SharedPreferences getBookSharedPreferences(String book) {
        return getSharedPreferences(getBookCheckName(book));
    }

    public static boolean isReadToday(String book) {
        return getBookSharedPreferences(book).getBoolean(TimeUtil.getDayOfMonth(), false);
    }

    public static void markReadToday(String book, boolean read) {
        getBookSharedPreferences(book).edit().putBoolean(TimeUtil.getDayOfMonth(), read).apply();
    }
}
