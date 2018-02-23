package com.demo.jachen.checkwidget.library.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.demo.jachen.checkwidget.library.BaseActivity;
import com.demo.jachen.checkwidget.library.R;
import com.demo.jachen.checkwidget.library.adapter.DiaryAdapter;
import com.demo.jachen.checkwidget.library.bean.Diary;
import com.demo.jachen.checkwidget.library.repository.DiaryRepository;

import java.util.List;

public class DiaryActivity extends BaseActivity {

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
            diaryAdapter.notifyDataSetChanged();
            recyclerView.smoothScrollToPosition(diaryAdapter.getItemCount() - 1);
//            handleSoftKeyboard(editText, false);
        });

        recyclerView = findViewById(R.id.diaries_recycler_view);

        editText = findViewById(R.id.editText);

        diaries = DiaryRepository.getAll();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setStackFromEnd(true);

        recyclerView.setLayoutManager(layoutManager);

        diaryAdapter = new DiaryAdapter(diaries);

        recyclerView.setAdapter(diaryAdapter);

//        recyclerView.post(() -> recyclerView.smoothScrollToPosition(diaryAdapter.getItemCount() - 1));

    }

    public void handleSoftKeyboard(View view, boolean show) {
        if (view.requestFocus()) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                if (show) {
                    imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
                } else {
                    imm.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_IMPLICIT_ONLY);
                }
            }
        }
    }
}
