package com.demo.jachen.checkwidget;

import android.app.Application;
import android.content.Context;

/**
 * Created by JayChen on 2017/11/26.
 */

public class CheckApplication extends Application {
    private static CheckApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static Context getInstance() {
        return instance;
    }
}
