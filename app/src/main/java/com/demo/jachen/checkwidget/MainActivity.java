package com.demo.jachen.checkwidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;

import com.demo.jachen.checkwidget.adapter.BookAdapter;
import com.demo.jachen.checkwidget.bean.Book;
import com.demo.jachen.checkwidget.utils.FileUtil;
import com.demo.jachen.checkwidget.utils.SharedPreferencesUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    public static final String DEFAULT_BOOK = "源码解析";

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Book> books = makeDefaultBooks();
        if (!TextUtils.isEmpty(SharedPreferencesUtil.getBooksString())) {

            FileUtil.save(SharedPreferencesUtil.getBooksString());
            books = SharedPreferencesUtil.getBooks();
        }

        SharedPreferencesUtil.storeBook(books);

        recyclerView = findViewById(R.id.books);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new BookAdapter(books));

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

    }

    private List<Book> makeDefaultBooks() {
        List<Book> books = new ArrayList<>();
        Book book = new Book(DEFAULT_BOOK);
        books.add(book);
        return books;
    }

}
