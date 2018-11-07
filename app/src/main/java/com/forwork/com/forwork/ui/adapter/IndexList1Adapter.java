package com.forwork.com.forwork.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.forwork.com.forwork.R;
import com.forwork.com.forwork.bean.base.Product;
import com.forwork.com.forwork.net.ListApi;

import java.util.List;

/**
 * Created by Administrator on 2018/11/2.
 */

public class IndexList1Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Product> productList;
    Context context;
    LayoutInflater inflater;
    OnRecycleViewItemClick onRecycleViewItemClick;
    int width;
    int height;

    public IndexList1Adapter(List<Product> productList, Context context) {
        this.productList = productList;
        this.context = context;
        inflater = LayoutInflater.from(context);
        width = context.getResources().getDisplayMetrics().widthPixels / 4;
        height = context.getResources().getDisplayMetrics().widthPixels / 7;
    }

    @Override
    public Index1ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = inflater.inflate(R.layout.item_product, parent, false);

        return new Index1ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        Index1ViewHolder index1ViewHolder = (Index1ViewHolder) holder;
        Glide.with(context).applyDefaultRequestOptions(new RequestOptions().override(width, height)).asBitmap().load(productList.get(position).getImage()).into(index1ViewHolder.icon);
        index1ViewHolder.name.setText(productList.get(position).getName());
        ViewGroup.LayoutParams layoutParams = index1ViewHolder.name.getLayoutParams();
        layoutParams.width = width;
        index1ViewHolder.name.setLayoutParams(layoutParams);

        index1ViewHolder.icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRecycleViewItemClick.onItemClick(position);
            }
        });
    }


    @Override
    public int getItemCount() {
        return productList.size();
    }

    private static class Index1ViewHolder extends RecyclerView.ViewHolder {
        ImageView icon;
        TextView name;

        public Index1ViewHolder(View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.item_pro_icon);
            name = itemView.findViewById(R.id.item_pro_name);
        }
    }

    public void setOnRecycleViewItemClick(OnRecycleViewItemClick onRecycleViewItemClick) {
        this.onRecycleViewItemClick = onRecycleViewItemClick;
    }
}
