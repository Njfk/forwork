package com.forwork.com.forwork.ui.fragment.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.forwork.com.forwork.R;
import com.forwork.com.forwork.bean.base.Product;

import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2018/11/19.
 */

public class Index1StaggeredGridAdapter extends RecyclerView.Adapter<Index1StaggeredGridAdapter.ViewHolder> {

    List<Product> products;
    Context context;
    LayoutInflater inflater;

    public Index1StaggeredGridAdapter(List<Product> products, Context context) {
        this.products = products;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_staggered, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ViewGroup.LayoutParams layoutParams = holder.imageView.getLayoutParams();
        layoutParams.height = new Random().nextInt(200) + 100;
        holder.imageView.setLayoutParams(layoutParams);

        Glide.with(context).asBitmap().apply(new RequestOptions().centerCrop())
                .load(products.get(position).getImage())
                .into(holder.imageView);
    }


    @Override
    public int getItemCount() {
        return products.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.item_staggered_img);
        }
    }
}
