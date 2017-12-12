package com.demo.jachen.checkwidget.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.demo.jachen.checkwidget.R;
import com.demo.jachen.checkwidget.adapter.TaskAdapter;
import com.demo.jachen.checkwidget.bean.Book;
import com.demo.jachen.checkwidget.bean.Task;
import com.demo.jachen.checkwidget.utils.SharedPreferencesUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TaskActivity extends AppCompatActivity {

    public static final String KEY_BOOK = "BOOK";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        Book book = getIntent().getParcelableExtra(KEY_BOOK);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        GridLayoutManager layoutManager = new GridLayoutManager(TaskActivity.this, 7);
        recyclerView.setLayoutManager(layoutManager);

        TaskAdapter adapter = new TaskAdapter(getDefaultBookTasks(book));
        recyclerView.setAdapter(adapter);

    }

    private List<Task> getDefaultBookTasks(Book book) {
        List<Task> list = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH);
        int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        for (int i = 1; i <= maxDay; i++) {
            Task task = new Task();
            task.setMonth(month);
            task.setDayOfMonth(i);
            task.setTarget(book);
            task.setFinished(SharedPreferencesUtil.isFinished(book.getName(), i));
            list.add(task);
        }
        return list;
    }
}
