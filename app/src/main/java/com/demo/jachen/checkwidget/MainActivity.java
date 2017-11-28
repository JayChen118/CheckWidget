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
import android.widget.CheckBox;
import android.widget.TextView;

import com.demo.jachen.checkwidget.activity.TaskActivity;
import com.demo.jachen.checkwidget.bean.Book;
import com.demo.jachen.checkwidget.utils.SharedPreferencesUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    public static final String DEFAULT_BOOK = "源码解析";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        if (TextUtils.isEmpty(SharedPreferencesUtil.getBooksString())) {
        List<Book> books = makeDefaultBooks();
        SharedPreferencesUtil.storeBook(books);
//        }

        findViewById(R.id.button2).setOnClickListener(v -> {
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
        });

        TextView textView = findViewById(R.id.records);
        textView.setText(SharedPreferencesUtil.readRecord());

        CheckBox checkBox = findViewById(R.id.checkBox);
        checkBox.setText(DEFAULT_BOOK);
        checkBox.setChecked(SharedPreferencesUtil.isFinishedToday(DEFAULT_BOOK));
        checkBox.setOnCheckedChangeListener((buttonView, isChecked) ->
                SharedPreferencesUtil.markFinishedToday(DEFAULT_BOOK, isChecked));

        findViewById(R.id.book_button).setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, TaskActivity.class)));
    }

    private List<Book> makeDefaultBooks() {
        List<Book> books = new ArrayList<>();
        Book book = new Book(DEFAULT_BOOK);
        books.add(book);
        return books;
    }

}
