package com.forwork.com.forwork.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.CycleInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.forwork.com.forwork.R;
import com.forwork.com.forwork.bean.IndexBean1;
import com.forwork.com.forwork.bean.base.Product;
import com.forwork.com.forwork.net.presenter.ListApiPresenter;
import com.forwork.com.forwork.net.view.IListView;
import com.forwork.com.forwork.reader.MainActivity;
import com.forwork.com.forwork.ui.activity.IndexActivity;
import com.forwork.com.forwork.ui.activity.WebActivity;
import com.forwork.com.forwork.ui.adapter.IndexBunnerAdapter;
import com.forwork.com.forwork.ui.adapter.IndexList1Adapter;
import com.forwork.com.forwork.ui.adapter.OnRecycleViewItemClick;
import com.forwork.com.forwork.ui.base.LazyFragment;
import com.forwork.com.forwork.ui.dialog.ScanDialogFragment;
import com.forwork.com.forwork.view.MarqueeTextView;
import com.forwork.com.forwork.view.PriceText;
import com.forwork.com.forwork.view.refresh.MRefreshFooter;
import com.forwork.com.forwork.view.refresh.MRefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class Index1Fragment extends LazyFragment {
    @BindView(R.id.index_refresh_layout)
    RefreshLayout index_refresh_layout;
    @BindView(R.id.index_shar)
    ImageView index_shake;

    @BindView(R.id.index_toolbar)
    Toolbar index_toolbar;
    @BindView(R.id.index_bunner_viewpager)
    ViewPager index_bunner_viewpager;
    @BindView(R.id.index_list_1)
    RecyclerView index_list_1;
    @BindView(R.id.index_marquee_text)
    MarqueeTextView index_marquee_text;
    @BindView(R.id.index_seek_bar)
    SeekBar index_seek_bar;
    @BindView(R.id.home_limit_price)
    PriceText home_limit_price;
    private String TAG = "Index";

    List<Product> products = new ArrayList<>();
    ListApiPresenter listApiPresenter;

    IndexList1Adapter indexList1Adapter;
    AppCompatActivity appCompatActivity;

    int list_page1 = 1;
    int list_total1 = 1;

    IListView iListView;

    boolean isCreated = false;

    public static Index1Fragment newInstance() {

        Bundle args = new Bundle();

        Index1Fragment fragment = new Index1Fragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_index1, container, false);
        ButterKnife.bind(this, inflate);
        appCompatActivity = (AppCompatActivity) getActivity();
        iListView = (IListView) getActivity();
        isCreated = true;
        Log.e(TAG, "onCreateView: " + isCreated);
        initView();
        lazyLoad();
        return inflate;
    }

    @Override
    protected void lazyLoad() {
        if (isCreated && isVisiable) {
            initData();
            isCreated = false;
            isVisiable = false;
        }
    }

    @Override
    public void refresh() {
        list_page1 = 1;
        list_total1 = 1;
        products.clear();
        indexList1Adapter.notifyDataSetChanged();
        listApiPresenter.getList1(list_page1);
    }


    public void initView() {

        iListView.showDialog();

        setHasOptionsMenu(true);
        appCompatActivity.setSupportActionBar(index_toolbar);
        //刷新事件
        index_refresh_layout.setRefreshHeader(new MRefreshHeader(getActivity()));
//        index_refresh_layout.setRefreshFooter(new MRefreshFooter(getActivity()));
        index_refresh_layout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refresh();
            }
        });

        //晃动动画
        RotateAnimation rotateAnimation = new RotateAnimation(0, 10, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setRepeatMode(Animation.REVERSE);
        rotateAnimation.setDuration(300);
        rotateAnimation.setRepeatCount(Animation.INFINITE);
        rotateAnimation.setInterpolator(new CycleInterpolator(2));

        index_shake.startAnimation(rotateAnimation);
        //viewpager
        final List<ImageView> bunners = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            ImageView imageView = new ImageView(getActivity());
            Glide.with(this).load("http://image.51huihuahua.com/activity/1531301867266.png")
                    .apply(new RequestOptions().placeholder(R.drawable.pulltorefresh_anim)).
                    into(imageView);
            bunners.add(imageView);
        }
        index_bunner_viewpager.setAdapter(new IndexBunnerAdapter(bunners));
        index_bunner_viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                ViewGroup.LayoutParams layoutParams = index_bunner_viewpager.getLayoutParams();
                layoutParams.height = bunners.get(position).getHeight();
                index_bunner_viewpager.setLayoutParams(layoutParams);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        //list1
        indexList1Adapter = new IndexList1Adapter(products, getActivity());
        indexList1Adapter.setOnRecycleViewItemClick(new OnRecycleViewItemClick() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getActivity(), WebActivity.class);
                intent.putExtra("url", products.get(position).getClickUrl());
                startActivity(intent);
            }
        });
        index_list_1.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        index_list_1.setAdapter(indexList1Adapter);
        final int[] state_list1 = new int[1];
        final int[] lastVisibleItemPosition = {0};
        index_list_1.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                state_list1[0] = newState;

                int itemCount = index_list_1.getLayoutManager().getItemCount();
                int childCount = index_list_1.getLayoutManager().getChildCount();
                if (state_list1[0] == RecyclerView.SCROLL_STATE_IDLE && childCount > 0 && (lastVisibleItemPosition[0] + 1) >= itemCount) {
                    if (list_page1 < list_total1)
                        listApiPresenter.getList1(++list_page1);
                }


            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                if (layoutManager instanceof LinearLayoutManager) {
                    LinearLayoutManager lm = (LinearLayoutManager) (layoutManager);
                    lastVisibleItemPosition[0] = lm.findLastVisibleItemPosition();
                }
            }
        });

        //seekBar
        int progress = index_seek_bar.getProgress();
        if (progress / 10 != 0) {
            progress = progress / 10 * 10;
        } else {
            progress = 0;
        }


        int i = 4000 / 100 * progress + 4000;

        home_limit_price.setText("￥" + i);

        index_seek_bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress / 10 != 0) {
                    progress = progress / 10 * 10;
                } else {
                    progress = 0;
                }
                int i = 4000 / 100 * progress + 4000;


                home_limit_price.setText("￥" + i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void initData() {
        listApiPresenter = new ListApiPresenter();
        listApiPresenter.attchView(iListView);
        listApiPresenter.getList1(list_page1);

        //轮播公告
        String[] announcement = new String[]{
                "恭喜臭豆腐成功订购了一盒臭豆腐",
                "恭喜叉烧包成功订购了一盒三鹿奶粉",
                "恭喜张二狗成功充值100元",
                "恭喜王逗逗中了1000大奖"
        };
        index_marquee_text.initMarqueeTextView(announcement, null);

    }


    public void getListSuccess(IndexBean1 indexBean1) {
        if (indexBean1.getData() != null) {
            list_page1 = indexBean1.getData().getPage();
            list_total1 = indexBean1.getData().getPage_size();

            products.addAll(indexBean1.getData().getItems());
        }
        indexList1Adapter.notifyDataSetChanged();
    }


    public void onCompleteRefresh() {
        index_refresh_layout.finishRefresh(1000);
        index_refresh_layout.finishLoadMore(1000);
    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.index_menu, menu);
////        setMenubackgroudcolor();
//        return super.onCreateOptionsMenu(menu);
//    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.index_menu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.e("22", "onOptionsItemSelected: ");
        switch (item.getItemId()) {
            case R.id.item_index_scan:
                ScanDialogFragment scanDialogFragment = ScanDialogFragment.newInstance();
                scanDialogFragment.show(getActivity().getSupportFragmentManager(), "");
                break;
            case R.id.item_index_read:
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
