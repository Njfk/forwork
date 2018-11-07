package com.forwork.com.forwork.view.adapter;

import android.support.annotation.IntDef;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

/**
 * Created by Administrator on 2018/10/23.
 */

public class LoadMoreScrollLisentner extends RecyclerView.OnScrollListener{
    LoadMoreAdapter loadMoreAdapter;
    RecyclerViewStatus recyclerViewStatus;

    private int currentScrollState;
    private int lastVisibleItemPosition;

    public static final int LINEAR = 1;
    public static final int GRID = 2;
    public static final int STAGGERED_GRID = 3;
    private int layoutManagerType = 0;
    private int[] lastPositions;

    @IntDef({LINEAR,GRID,STAGGERED_GRID})
    public @interface LAYOUT_MANAGER_TYPE {
    }

    public LoadMoreScrollLisentner(LoadMoreAdapter loadMoreAdapter) {
        this.loadMoreAdapter = loadMoreAdapter;
    }


    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        currentScrollState = newState;
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        int visibleItemCount = layoutManager.getChildCount();
        int totalItemCount = layoutManager.getItemCount();
//        lastVisibleItemPosition = layoutManager.get
        if ((visibleItemCount > 0 && currentScrollState == RecyclerView.SCROLL_STATE_IDLE &&
                (lastVisibleItemPosition) >= totalItemCount - 1)) {
            if (loadMoreAdapter .getLoadingState() != LoadMoreAdapter.LOADING){
                recyclerViewStatus.onBottom();
            }
        }

    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        //  int lastVisibleItemPosition = -1;
        if (layoutManagerType == 0) {
            if (layoutManager instanceof LinearLayoutManager) {
                layoutManagerType = LINEAR;
            } else if (layoutManager instanceof GridLayoutManager) {
                layoutManagerType = GRID;
            } else if (layoutManager instanceof StaggeredGridLayoutManager) {
                layoutManagerType = STAGGERED_GRID;
            } else {
                throw new RuntimeException(
                        "Unsupported LayoutManager used. Valid ones are LinearLayoutManager, GridLayoutManager and StaggeredGridLayoutManager");
            }
        }

        switch (layoutManagerType) {
            case LINEAR:
                lastVisibleItemPosition = ((LinearLayoutManager) layoutManager)
                        .findLastVisibleItemPosition();
                break;
            case GRID:
                lastVisibleItemPosition = ((GridLayoutManager) layoutManager)
                        .findLastVisibleItemPosition();
                break;
            case STAGGERED_GRID:
                StaggeredGridLayoutManager staggeredGridLayoutManager
                        = (StaggeredGridLayoutManager) layoutManager;
                if (lastPositions == null) {
                    lastPositions = new int[staggeredGridLayoutManager.getSpanCount()];
                }
                staggeredGridLayoutManager.findLastVisibleItemPositions(lastPositions);
                lastVisibleItemPosition = findMax(lastPositions);
                break;
        }
    }
    public interface RecyclerViewStatus{
        void onBottom();
    }

    public void setRecyclerViewStatus(RecyclerViewStatus recyclerViewStatus) {
        this.recyclerViewStatus = recyclerViewStatus;
    }

    private int findMax(int[] lastPositions) {
        int max = lastPositions[0];
        for (int value : lastPositions) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }
}
