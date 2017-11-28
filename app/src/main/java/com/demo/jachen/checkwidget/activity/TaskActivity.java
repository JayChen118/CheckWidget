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

import static com.demo.jachen.checkwidget.MainActivity.DEFAULT_BOOK;

public class TaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);


        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        GridLayoutManager layoutManager = new GridLayoutManager(TaskActivity.this, 7);
        recyclerView.setLayoutManager(layoutManager);

        TaskAdapter adapter = new TaskAdapter(getDefaultBookTasks());
        recyclerView.setAdapter(adapter);

    }

    private List<Task> getDefaultBookTasks() {
        List<Task> list = new ArrayList<>();
        Book book = new Book(DEFAULT_BOOK);
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH);
        int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        for (int i = 1; i <= maxDay; i++) {
            Task task = new Task();
            task.setMonth(month);
            task.setDayOfMonth(i);
            task.setTarget(book);
            task.setFinished(SharedPreferencesUtil.isFinished(DEFAULT_BOOK, i));
            list.add(task);
        }
        return list;
    }
}
