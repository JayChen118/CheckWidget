package com.demo.jachen.checkwidget.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.demo.jachen.checkwidget.R;
import com.demo.jachen.checkwidget.bean.Task;

import java.util.List;

/**
 * Created by jachen on 11/28/2017.
 */

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    private List<Task> tasks;

    public TaskAdapter(List<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.dateView.setText(String.valueOf(position + 1));
        holder.stateView.setText(tasks.get(position).isFinished() ? "Y" : "N");
        holder.stateView.setTextColor(tasks.get(position).isFinished() ? Color.GREEN : Color.BLACK);
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView dateView;
        public TextView stateView;

        public ViewHolder(View itemView) {
            super(itemView);
            dateView = itemView.findViewById(R.id.date);
            stateView = itemView.findViewById(R.id.state);
        }
    }

}
