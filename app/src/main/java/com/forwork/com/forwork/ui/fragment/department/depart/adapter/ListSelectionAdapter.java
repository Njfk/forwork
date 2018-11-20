package com.forwork.com.forwork.ui.fragment.department.depart.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.forwork.com.forwork.R;
import com.forwork.com.forwork.ui.fragment.department.depart.bean.Selection;

import java.util.List;

/**
 * Created by Administrator on 2018/11/20.
 */

public class ListSelectionAdapter extends RecyclerView.Adapter<ListSelectionAdapter.ViewHolder>{
    List<Selection> selections;
    Context context;
    LayoutInflater inflater;

    public ListSelectionAdapter(List<Selection> selections, Context context) {
        this.selections = selections;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView title;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.item_selection_icon);
            title = itemView.findViewById(R.id.item_selection_title);
        }
    }
}
