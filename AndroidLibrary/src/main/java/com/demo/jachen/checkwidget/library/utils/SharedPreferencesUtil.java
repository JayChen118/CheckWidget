package com.demo.jachen.checkwidget.library.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.demo.jachen.checkwidget.library.CheckApplication;
import com.demo.jachen.checkwidget.library.bean.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jachen on 10/31/2017.
 */

public class SharedPreferencesUtil {

    public static final String RECORD = "record";
    public static final String TIME = "Time";
    private static final String KEY_BOOKS = "BOOKS";

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

    private static String getTargetCheckName(String target) {
        return String.format("%s_%s", target, TimeUtil.getYearMonth());
    }

    private static SharedPreferences getTargetSharedPreferences(String target) {
        return getSharedPreferences(getTargetCheckName(target));
    }

    public static boolean isFinishedToday(String target) {
        return isFinished(target, TimeUtil.getDayOfMonth());
    }

    public static void markFinishedToday(String target, boolean finished) {
        markFinished(target, TimeUtil.getDayOfMonth(), finished);
    }


    public static boolean isFinished(String target, int dayOfMonth) {
        return getTargetSharedPreferences(target).getBoolean(String.valueOf(dayOfMonth), false);
    }

    public static void markFinished(String target, int dayOfMonth, boolean finished) {
        getTargetSharedPreferences(target).edit().putBoolean(String.valueOf(dayOfMonth), finished).apply();
    }

    public static void logLastAlertTime(String target, long time) {
        getTargetSharedPreferences(target).edit().putLong(TIME, time).apply();
    }

    public static long getLastAlertTime(String target) {
        return getTargetSharedPreferences(target).getLong(TIME, 0);
    }

    public static void storeBook(List<Book> books) {
        getSharedPreferences().edit().putString(KEY_BOOKS, GsonUtil.format(books)).apply();
    }

    public static String getBooksString() {
        return getSharedPreferences().getString(KEY_BOOKS, "");
    }

    public static List<Book> getBooks() {
        String string = getBooksString();
        if (!TextUtils.isEmpty(string)) {
            return GsonUtil.parse(string);
        } else {
            return new ArrayList<>();
        }
    }

}