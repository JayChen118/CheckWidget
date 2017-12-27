package com.demo.jachen.checkwidget.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;

import com.demo.jachen.checkwidget.R;
import com.demo.jachen.checkwidget.adapter.DiaryAdapter;
import com.demo.jachen.checkwidget.bean.Diary;
import com.demo.jachen.checkwidget.repository.DiaryRepository;

import java.util.List;

public class DiaryActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    DiaryAdapter diaryAdapter;

    EditText editText;

    List<Diary> diaries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Diary");
        }

        findViewById(R.id.save).setOnClickListener(view -> {
            Diary diary = new Diary(System.currentTimeMillis(), editText.getText().toString());
            diaries.add(diary);
            DiaryRepository.store(diaries);
            editText.setText(null);
        });

        recyclerView = findViewById(R.id.diaries_recycler_view);

        editText = findViewById(R.id.editText);

        diaries = DiaryRepository.getAll();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        diaryAdapter = new DiaryAdapter(diaries);

        recyclerView.setAdapter(diaryAdapter);

    }
}
