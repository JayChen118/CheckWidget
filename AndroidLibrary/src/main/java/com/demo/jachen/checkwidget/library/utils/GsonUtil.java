package com.demo.jachen.checkwidget.library.utils;

import com.demo.jachen.checkwidget.library.bean.Book;
import com.demo.jachen.checkwidget.library.bean.Diary;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jachen on 11/28/2017.
 */

public class GsonUtil {

    public static List<Book> parse(String json) {
        Gson gson = new Gson();
        Type type = new TypeToken<List<Book>>() {
        }.getType();
        try {
            return gson.fromJson(json, type);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }


    public static List<Diary> parseDiaries(String json) {
        Gson gson = new Gson();
        Type type = new TypeToken<List<Diary>>() {
        }.getType();
        try {
            return gson.fromJson(json, type);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public static <T> String format(List<T> list) {
        return new Gson().toJson(list);
    }
}
