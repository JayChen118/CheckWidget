package com.demo.jachen.checkwidget;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by jachen on 10/31/2017.
 */

public class SharedPreferencesUtil {

    public static void storeRecord(Context context, String records) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("check", Context.MODE_PRIVATE);
        sharedPreferences.edit().putString("record", records);
    }
}
