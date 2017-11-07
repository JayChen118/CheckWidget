package com.demo.jachen.checkwidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = MainActivity.this;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    AppWidgetManager manager = context.getSystemService(AppWidgetManager.class);
                    Log.d(TAG, "onClick: " + manager);
                    ComponentName componentName = new ComponentName(context, CheckAppWidgetProvider.class);
                    if (manager != null && manager.isRequestPinAppWidgetSupported()) {
                        Intent pinedWidgetCallbackIntent = new Intent(context, MainActivity.class);

                        PendingIntent intent = PendingIntent.getActivity(context, 0, pinedWidgetCallbackIntent, 0);
                        manager.requestPinAppWidget(componentName, null, intent);
                    }
                }
            }
        });

        TextView textView = findViewById(R.id.records);
        textView.setText(SharedPreferencesUtil.readRecord(this));
    }
}
