package com.demo.jachen.checkwidget.library.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.demo.jachen.checkwidget.library.R;
import com.demo.jachen.checkwidget.library.bean.Diary;

import java.util.List;

/**
 * Created by JayChen on 2017/12/27.
 */

public class DiaryAdapter extends RecyclerView.Adapter<DiaryAdapter.ViewHolder> {

    private List<Diary> diaries;

    public DiaryAdapter(List<Diary> diaries) {
        this.diaries = diaries;
    }

    public void setDiaries(List<Diary> diaries) {
        if (diaries != null) {
            this.diaries = diaries;
            notifyDataSetChanged();
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.diary_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Diary diary = diaries.get(position);
        holder.dateView.setText(diary.getDate());
        holder.dateView.setOnClickListener(view -> {
            TextView textView = (TextView) view;
            Toast.makeText(view.getContext(), textView.getText(), Toast.LENGTH_SHORT).show();
        });
        holder.contentView.setText(diary.getContent());
    }

    @Override
    public int getItemCount() {
        return diaries.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView dateView;
        TextView contentView;


        public ViewHolder(View itemView) {
            super(itemView);
            dateView = itemView.findViewById(R.id.date_text_view);
            contentView = itemView.findViewById(R.id.contentTextView);
        }
    }

}
