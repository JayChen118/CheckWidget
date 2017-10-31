package com.demo.jachen.checkwidget;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by jachen on 10/31/2017.
 */

public class SharedPreferencesUtil {

    public static final String RECORD = "record";

    public static void storeRecord(Context context, String records) {
        getSharedPreferences(context).edit().putString(RECORD, records).apply();
    }


    public static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences("check", Context.MODE_PRIVATE);
    }

    public static String readRecord(Context context){
        return getSharedPreferences(context).getString(RECORD, "");
    }
}
