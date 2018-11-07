package com.forwork.com.forwork.view.adapter;

import android.support.annotation.IntDef;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.forwork.com.forwork.R;


/**
 * Created by Administrator on 2018/10/23.
 */

public class LoadMoreAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    public static final int LOADING = 1;//加载中
    public static final int LOADING_COMPLETE = 2;//加载完成
    public static final int LOADING_END = 3;//加载结束

    private final int NORMAL_VIEW = 1;
    private final int FOOTER_VIEW = 2;

    private int loadingState = LOADING_COMPLETE;
    private RecyclerView.Adapter adapter;


    @IntDef({LOADING, LOADING_COMPLETE, LOADING_END})
    public @interface LoadingStatus {
    }



    public LoadMoreAdapter(RecyclerView.Adapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == FOOTER_VIEW) {
            View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_loadmore, parent,false);
            return new FootView(inflate);
        } else {
            return adapter.onCreateViewHolder(parent, viewType);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof FootView) {
            FootView footView = (FootView) holder;
            switch (loadingState) {
                case LOADING:
                    footView.loadmore_progress.setVisibility(View.VISIBLE);
                    footView.loading_content.setText("正在加载中..");
                    break;
                case LOADING_COMPLETE:
                    footView.loadmore_progress.setVisibility(View.GONE);
                    footView.loading_content.setText("加载完成..");
                    break;
                case LOADING_END:
                    footView.loadmore_progress.setVisibility(View.GONE);
                    footView.loading_content.setText("已到底部");
                    break;
            }
        } else {
            adapter.onBindViewHolder(holder, position);
        }
    }


    @Override
    public int getItemCount() {
        return adapter.getItemCount() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position + 1 == getItemCount()) {
            return FOOTER_VIEW;
        } else {
            return NORMAL_VIEW;
        }
    }

    private static class FootView extends RecyclerView.ViewHolder {

        ProgressBar loadmore_progress;
        TextView loading_content;

        public FootView(View itemView) {
            super(itemView);
            loading_content = itemView.findViewById(R.id.loading_content);
            loadmore_progress = itemView.findViewById(R.id.loadmore_progress);
        }


    }

    public void setLoadingState(@LoadingStatus int loadingState) {
        this.loadingState = loadingState;
        notifyDataSetChanged();
    }

    public int getLoadingState() {
        return loadingState;
    }
}
