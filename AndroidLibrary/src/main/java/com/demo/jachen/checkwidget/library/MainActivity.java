package com.demo.jachen.checkwidget.library;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;

import com.demo.jachen.checkwidget.library.activity.DiaryActivity;
import com.demo.jachen.checkwidget.library.adapter.BookAdapter;
import com.demo.jachen.checkwidget.library.bean.Book;
import com.demo.jachen.checkwidget.library.utils.FileUtil;
import com.demo.jachen.checkwidget.library.utils.SharedPreferencesUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    private static final String TAG = "MainActivity";
    public static final String DEFAULT_BOOK = "源码解析";
    public static final String SECOND_BOOK = "A Study In Scarlet";
    public static final String THIRD_BOOK = "你不努力，谁也给不了你想要的生活";


    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.demo.jachen.checkwidget.library.R.layout.activity_main);

        List<Book> books = makeDefaultBooks();
        if (!TextUtils.isEmpty(SharedPreferencesUtil.getBooksString())) {

            FileUtil.save(SharedPreferencesUtil.getBooksString());
            books = SharedPreferencesUtil.getBooks();
            Log.d(TAG, "onCreate: size: " + books.size());
            addNotContain(books, makeDefaultBooks());
            Log.d(TAG, "onCreate: size: " + books.size());

        }

        SharedPreferencesUtil.storeBook(books);

        recyclerView = findViewById(com.demo.jachen.checkwidget.library.R.id.books);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new BookAdapter(books));


        findViewById(com.demo.jachen.checkwidget.library.R.id.button2).setOnClickListener(v -> {

            Intent intent = new Intent(this, DiaryActivity.class);
            startActivity(intent);
//            addWidget();
        });

        requestPermissions();
    }


    private void addWidget() {
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

    private void addNotContain(List<Book> current, List<Book> adding) {
        for (Book addBook : adding) {
            boolean isContain = false;
            for (Book book : current) {
                if (TextUtils.equals(addBook.getName(), book.getName())) {
                    isContain = true;
                    break;
                }
            }
            if (!isContain) {
                current.add(addBook);
            }
        }
    }


    private List<Book> makeDefaultBooks() {
        List<Book> books = new ArrayList<>();
        Book book = new Book(DEFAULT_BOOK);
        books.add(book);
        book = new Book(SECOND_BOOK);
        books.add(book);
        book = new Book(THIRD_BOOK);
        books.add(book);
        return books;
    }

}
