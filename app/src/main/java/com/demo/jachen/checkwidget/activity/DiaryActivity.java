package com.demo.jachen.checkwidget.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

import com.demo.jachen.checkwidget.R;
import com.demo.jachen.checkwidget.bean.Diary;
import com.demo.jachen.checkwidget.repository.DiaryRepository;

import java.util.List;

public class DiaryActivity extends AppCompatActivity {

    TextView textView;

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
            textView.setText(diaries.toString());
            editText.setText(null);
        });


        textView = findViewById(R.id.diaries_text_view);

        editText = findViewById(R.id.editText);

        diaries = DiaryRepository.getAll();
        textView.setText(diaries.toString());

    }
}
