package com.demo.jachen.checkwidget.library.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.demo.jachen.checkwidget.library.R;
import com.demo.jachen.checkwidget.library.activity.TaskActivity;
import com.demo.jachen.checkwidget.library.bean.Book;
import com.demo.jachen.checkwidget.library.utils.Checker;
import com.demo.jachen.checkwidget.library.utils.SharedPreferencesUtil;

import java.util.List;

import static com.demo.jachen.checkwidget.library.activity.TaskActivity.KEY_BOOK;

/**
 * Created by jachen on 12/7/2017.
 */

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {

    private List<Book> books;

    public BookAdapter(List<Book> books) {
        this.books = books;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.book_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Book book = books.get(position);
        holder.bookName.setText(String.format("(%d)%s", book.getCurrentPage(), book.getName()));

        holder.bookName.setChecked(SharedPreferencesUtil.isFinishedToday(book.getName()));
        holder.bookName.setOnCheckedChangeListener((buttonView, isChecked) ->
                SharedPreferencesUtil.markFinishedToday(book.getName(), isChecked));

        holder.viewMonthly.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), TaskActivity.class);
            intent.putExtra(KEY_BOOK, book);
            v.getContext().startActivity(intent);
        });
        holder.editPage.setOnClickListener(view -> showPageDialog(view.getContext(), book));
    }

    private boolean showPageDialog(Context context, Book book) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(R.layout.dialog_edit_page);

        builder.setPositiveButton("确定", (dialogInterface, i) -> {
            if (dialogInterface instanceof AlertDialog) {
                EditText editText = ((AlertDialog) dialogInterface).findViewById(R.id.page_edit_text);
                if (editText != null && !Checker.isEmpty(editText.getText())) {

                    try {
                        int page = Integer.parseInt(editText.getText().toString());
                        book.setCurrentPage(page);
                        SharedPreferencesUtil.storeBook(books);
                        notifyDataSetChanged();
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }
            }

        });
        builder.create().show();


        return true;
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public CheckBox bookName;
        public Button viewMonthly;
        public Button editPage;

        public ViewHolder(View itemView) {
            super(itemView);
            bookName = itemView.findViewById(R.id.book_name);
            viewMonthly = itemView.findViewById(R.id.view_monthly_button);
            editPage = itemView.findViewById(R.id.edit_page_button);
        }
    }
}
