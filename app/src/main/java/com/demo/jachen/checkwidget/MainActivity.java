package com.demo.jachen.checkwidget;

import android.Manifest;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.demo.jachen.checkwidget.activity.DiaryActivity;
import com.demo.jachen.checkwidget.adapter.BookAdapter;
import com.demo.jachen.checkwidget.bean.Book;
import com.demo.jachen.checkwidget.utils.FileUtil;
import com.demo.jachen.checkwidget.utils.SharedPreferencesUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    public static final String DEFAULT_BOOK = "源码解析";
    public static final String SECOND_BOOK = "A Study In Scarlet";
    public static final String THIRD_BOOK = "你不努力，谁也给不了你想要的生活";

    private static final int REQUEST_CODE_STORAGE = 260;

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Book> books = makeDefaultBooks();
        if (!TextUtils.isEmpty(SharedPreferencesUtil.getBooksString())) {

            FileUtil.save(SharedPreferencesUtil.getBooksString());
            books = SharedPreferencesUtil.getBooks();
            Log.d(TAG, "onCreate: size: " + books.size());
            addNotContain(books, makeDefaultBooks());
            Log.d(TAG, "onCreate: size: " + books.size());

        }

        SharedPreferencesUtil.storeBook(books);

        recyclerView = findViewById(R.id.books);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new BookAdapter(books));

        findViewById(R.id.button2).setOnClickListener(v -> {

            Intent intent = new Intent(this, DiaryActivity.class);
            startActivity(intent);
//            addWidget();
        });

        requestPermissions();
    }

    private void requestPermissions() {
        int permissionCheck = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE_STORAGE);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_CODE_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "授权成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "授权失败，将无法存储数据", Toast.LENGTH_LONG).show();
                }
        }
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
