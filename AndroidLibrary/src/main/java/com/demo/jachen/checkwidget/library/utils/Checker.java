package com.demo.jachen.checkwidget.library.utils;

import android.text.TextUtils;

import java.util.List;

/**
 * Created by JayChen on 2017/12/23.
 */

public class Checker {

    public static boolean isEmpty(CharSequence sequence) {
        return TextUtils.isEmpty(sequence);
    }


    public static boolean isEmpty(List list) {
        return list == null || list.isEmpty();
    }
}
