package com.itis.android.lessondb.ui.fragments.lists.book.main_book_list;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.itis.android.lessondb.R;
import com.itis.android.lessondb.general.Book;
import com.itis.android.lessondb.room.entity.RoomBook;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nail Shaykhraziev on 11.02.2018.
 */

public class BookAdapter extends RecyclerView.Adapter<BookItemHolder> {

    // need change RoomBook to RealmBook for work with Realm on this class
    private List<RoomBook> items = new ArrayList<>();
    private OnItemClickListener onItemClickListener;

    private final View.OnClickListener internalListener = (view) -> {
        if (onItemClickListener != null) {
            int position = (int) view.getTag();
            Book item = getItem(position);
            onItemClickListener.onItemClick(item);
        }
    };

    BookAdapter(List<RoomBook> items) {
        this.items.addAll(items);
    }

    @Override
    public BookItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BookItemHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_book, parent, false));
    }

    @Override
    public void onBindViewHolder(BookItemHolder holder, int position) {
        RoomBook book = getItem(position);
        holder.bind(book);
        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(internalListener);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    final void changeDataSet(@NonNull List<RoomBook> values) {
        items.clear();
        items.addAll(values);
        notifyDataSetChanged();
    }

    private RoomBook getItem(int pos) {
        return items.get(pos);
    }

    void setOnItemClickListener(@Nullable OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(@NonNull Book item);
    }
}
