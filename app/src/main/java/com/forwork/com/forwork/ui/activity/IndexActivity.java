package com.forwork.com.forwork.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.CycleInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

//import com.bumptech.glide.Glide;
import com.forwork.com.forwork.R;
import com.forwork.com.forwork.bean.IndexBean1;
import com.forwork.com.forwork.bean.base.Book;
import com.forwork.com.forwork.bean.base.Product;
import com.forwork.com.forwork.net.ListApi;
import com.forwork.com.forwork.net.presenter.ListApiPresenter;
import com.forwork.com.forwork.net.view.IListView;
import com.forwork.com.forwork.reader.MainActivity;
import com.forwork.com.forwork.ui.adapter.IndexBunnerAdapter;
import com.forwork.com.forwork.ui.adapter.IndexList1Adapter;
import com.forwork.com.forwork.ui.adapter.IndexPageAdapter;
import com.forwork.com.forwork.ui.adapter.OnRecycleViewItemClick;
import com.forwork.com.forwork.ui.base.BaseActivity;
import com.forwork.com.forwork.ui.dialog.ScanDialogFragment;
import com.forwork.com.forwork.ui.fragment.Index1Fragment;
import com.forwork.com.forwork.ui.fragment.Index2Fragment;
import com.forwork.com.forwork.ui.fragment.Index3Fragment;
import com.forwork.com.forwork.ui.fragment.Index4Fragment;
import com.forwork.com.forwork.ui.fragment.Index5Fragment;
import com.forwork.com.forwork.ui.fragment.department.Index3Depart3Fragment;
import com.forwork.com.forwork.ui.service.MusicService;
import com.forwork.com.forwork.ui.service.Player;
import com.forwork.com.forwork.utils.StatusBarUtils;
import com.forwork.com.forwork.view.MarqueeTextView;
import com.forwork.com.forwork.view.dialog.LoadingFragment;
import com.forwork.com.forwork.view.refresh.MRefreshHeader;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class IndexActivity extends BaseActivity implements IListView, View.OnClickListener {

    @BindView(R.id.index_viewpager)
    ViewPager index_viewpager;
    @BindView(R.id.index_mall0)
    RadioButton index_mall0;
    @BindView(R.id.index_mall1)
    RadioButton index_mall1;
    @BindView(R.id.index_mall2)
    RadioButton index_mall2;
    @BindView(R.id.index_mall3)
    ImageView index_mall3;
    @BindView(R.id.index_mall4)
    RadioButton index_mall4;

    List<Fragment> fragments = new ArrayList<>();
    List<RadioButton> btns = new ArrayList<>();
    IndexPageAdapter indexPageAdapter;
    Index1Fragment index1Fragment;
    Index2Fragment index2Fragment;
    Index3Fragment index3Fragment;
    Index4Fragment index4Fragment;
    Index5Fragment index5Fragment;
    private String TAG = "index";

    @Override
    public int getLayoutId() {
        return R.layout.activity_index;
    }

    @Override
    public void setToolbar() {
//        setSupportActionBar(index_toolbar);
    }

    @Override
    public void setStatusBar() {
        StatusBarUtils.setColor(this, getResources().getColor(R.color.color_00c3e6));
    }

    @Override
    public void initView() {
//        showLoading();
//
//
//        //晃动动画
//        RotateAnimation rotateAnimation = new RotateAnimation(0, 10, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
//        rotateAnimation.setRepeatMode(Animation.REVERSE);
//        rotateAnimation.setDuration(300);
//        rotateAnimation.setRepeatCount(Animation.INFINITE);
//        rotateAnimation.setInterpolator(new CycleInterpolator(2));
//
//        index_shake.startAnimation(rotateAnimation);
//        index_mall3.setAnimation(rotateAnimation);
//        //viewpager
//        final List<ImageView> bunners = new ArrayList<>();
//        for (int i = 0; i < 3; i++) {
//            ImageView imageView = new ImageView(this);
//            Glide.with(this).load("http://image.51huihuahua.com/activity/1531301867266.png")
//                    .apply(new RequestOptions().placeholder(R.drawable.pulltorefresh_anim)).
//                    into(imageView);
//            bunners.add(imageView);
//        }
//        index_bunner_viewpager.setAdapter(new IndexBunnerAdapter(bunners));
//        index_bunner_viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                ViewGroup.LayoutParams layoutParams = index_bunner_viewpager.getLayoutParams();
//                layoutParams.height = bunners.get(position).getHeight();
//                index_bunner_viewpager.setLayoutParams(layoutParams);
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });
//
//        //刷新事件
//        index_refresh_layout.setRefreshHeader(new MRefreshHeader(this));
//        index_refresh_layout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
//            @Override
//            public void onLoadMore(final RefreshLayout refreshLayout) {
////                Toast.makeText(IndexActivity.this,"1",Toast.LENGTH_LONG).show();
//                refreshLayout.finishLoadMore(2000);
//            }
//
//            @Override
//            public void onRefresh(RefreshLayout refreshLayout) {
////                Toast.makeText(IndexActivity.this,"2",Toast.LENGTH_LONG).show();
//                refreshLayout.finishRefresh(2000);
//
//            }
//        });
//
//        //list1
//        indexList1Adapter = new IndexList1Adapter(products, this);
//        indexList1Adapter.setOnRecycleViewItemClick(new OnRecycleViewItemClick() {
//            @Override
//            public void onItemClick(int position) {
//                Intent intent = new Intent(IndexActivity.this, WebActivity.class);
//                intent.putExtra("url", products.get(position).getClickUrl());
//                startActivity(intent);
//            }
//        });
//        index_list_1.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
//        index_list_1.setAdapter(indexList1Adapter);
//        final int[] state_list1 = new int[1];
//        final int[] lastVisibleItemPosition = {0};
//        index_list_1.setOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//                state_list1[0] = newState;
//
//                int itemCount = index_list_1.getLayoutManager().getItemCount();
//                int childCount = index_list_1.getLayoutManager().getChildCount();
//                Log.e(TAG, "onScrolled: " + (state_list1[0] == RecyclerView.SCROLL_STATE_IDLE));
//                Log.e(TAG, "onScrolled: " + (childCount > 0));
//                Log.e(TAG, "onScrolled: " + ((lastVisibleItemPosition[0] + 1) >= itemCount));
//                Log.e(TAG, "onScrolled: " + lastVisibleItemPosition[0]);
//                if (state_list1[0] == RecyclerView.SCROLL_STATE_IDLE && childCount > 0 && (lastVisibleItemPosition[0] + 1) >= itemCount) {
//                    if (list_page1 < list_total1)
//                        listApiPresenter.getList1(++list_page1);
//                }
//
//
//            }
//
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
//                if (layoutManager instanceof LinearLayoutManager) {
//                    LinearLayoutManager lm = (LinearLayoutManager) (layoutManager);
//                    lastVisibleItemPosition[0] = lm.findLastVisibleItemPosition();
//                }
//            }
//        });
        RotateAnimation rotateAnimation = new RotateAnimation(0, 10, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setRepeatMode(Animation.REVERSE);
        rotateAnimation.setDuration(500);
        rotateAnimation.setRepeatCount(Animation.INFINITE);
        rotateAnimation.setInterpolator(new CycleInterpolator(2));

        index_mall3.setAnimation(rotateAnimation);

        index_mall0.setOnClickListener(this);
        index_mall1.setOnClickListener(this);
        index_mall2.setOnClickListener(this);
        index_mall3.setOnClickListener(this);
        index_mall4.setOnClickListener(this);

        btns.add(index_mall0);
        btns.add(index_mall1);
        btns.add(index_mall2);
        btns.add(null);
        btns.add(index_mall4);

        index1Fragment = new Index1Fragment();
        index2Fragment = new Index2Fragment();
        index3Fragment = new Index3Fragment();
        index4Fragment = new Index4Fragment();
        index5Fragment = new Index5Fragment();
        fragments.add(index1Fragment);
        fragments.add(index2Fragment);
        fragments.add(index3Fragment);
        fragments.add(index4Fragment);
        fragments.add(index5Fragment);

        indexPageAdapter = new IndexPageAdapter(getSupportFragmentManager(), fragments);
        index_viewpager.setAdapter(indexPageAdapter);
        index_viewpager.setOffscreenPageLimit(5);

        index_viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                index2Fragment.dismissPop();
            }

            @Override
            public void onPageSelected(int position) {
                if (position == 3) {
                    return;
                }
                btns.get(position).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    public void initData() {

    }


    @Override
    public void showDialog() {
        super.showLoading();
    }

    @Override
    public void dismissDialog() {
        super.dismissLoading();
        index1Fragment.onCompleteRefresh();

    }

    @Override
    public void error(int code) {

    }

    @Override
    public void getListSuccess(IndexBean1 indexBean1) {
        index1Fragment.getListSuccess(indexBean1);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        index_marquee_text.releaseResources();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.index_mall0:
                index_mall0.setChecked(true);
                index_viewpager.setCurrentItem(0);
                break;
            case R.id.index_mall1:
                index_mall1.setChecked(true);
                index_viewpager.setCurrentItem(1);
                break;
            case R.id.index_mall2:
                index_mall2.setChecked(true);
                index_viewpager.setCurrentItem(2);
                break;
            case R.id.index_mall3:
                index_viewpager.setCurrentItem(3);
                break;
            case R.id.index_mall4:
                index_mall4.setChecked(true);
                index_viewpager.setCurrentItem(4);
                break;
        }
    }

    @Override
    protected boolean onPrepareOptionsPanel(View view, Menu menu) {
        if (menu != null) {
            if (menu.getClass() == MenuBuilder.class) {
                try {
                    Method m = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu, true);
                } catch (Exception e) {
                }
            }
        }
        return super.onPrepareOptionsPanel(view, menu);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    }
}
