package com.forwork.com.forwork.ui.fragment.department;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.forwork.com.forwork.R;
import com.forwork.com.forwork.bean.MusicList;
import com.forwork.com.forwork.bean.base.Music;
import com.forwork.com.forwork.net.ListApi;
import com.forwork.com.forwork.net.presenter.MusicPresenter;
import com.forwork.com.forwork.net.view.IMusicView;
import com.forwork.com.forwork.ui.activity.MusicActivity;
import com.forwork.com.forwork.ui.base.BaseActivity;
import com.forwork.com.forwork.ui.base.LazyFragment;
import com.forwork.com.forwork.ui.fragment.department.depart.adapter.MusicListAdapter;
import com.forwork.com.forwork.ui.service.MusicService;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class Index3Depart3Fragment extends LazyFragment implements IMusicView {
    private static final String TAG = "Index3Depart3";
    public static String music = "http://abv.cn/music/光辉岁月.mp3";
    public static String music_name = "光辉岁月.mp3";
    @BindView(R.id.index3Depart3List1)
    RecyclerView index3Depart3List1;
    boolean isCreated = false;
    MusicPresenter musicPresenter;
    MusicListAdapter musicListAdapter;
    private int page = 1;
    private int totalpage = 0;

    public Index3Depart3Fragment() {
        // Required empty public constructor
    }

    List<Music> musics = new ArrayList<>();
    BaseActivity appCompatActivity;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_index3_depart3, container, false);
        ButterKnife.bind(this, inflate);
        appCompatActivity = (BaseActivity) getActivity();
        isCreated = true;
        initView();
        return inflate;
    }

    @Override
    protected void lazyLoad() {
        if (isVisiable && isCreated) {
            init();

            isVisiable = false;
            isCreated = false;
        }
    }

    @Override
    public void refresh() {

    }

    public void initView() {
        musicListAdapter = new MusicListAdapter(musics, getActivity());

        index3Depart3List1.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        index3Depart3List1.setAdapter(musicListAdapter);
        musicListAdapter.setOnMusicItemClickLisenter(new MusicListAdapter.OnMusicItemClickLisenter() {
            @Override
            public void onItemClick(int position) {
//                Intent intent = new Intent(getActivity(), MusicActivity.class);
//                intent.putExtra("listenUrl",musics.get(position).getListenUrl());
//                startActivity(intent);
                Intent intent = new Intent(getActivity(), MusicService.class);
                intent.putExtra("listenUrl", music);
                intent.putExtra("listenName", music_name);
                getActivity().startService(intent);
            }
        });

        final int[] state = new int[1];
        final int[] lastVisibleItemPosition = new int[1];
        index3Depart3List1.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                state[0] = newState;
                int itemCount = recyclerView.getLayoutManager().getItemCount();
                int childCount = recyclerView.getLayoutManager().getChildCount();

                if (state[0] == RecyclerView.SCROLL_STATE_IDLE && itemCount > 0 && childCount > 0 && (lastVisibleItemPosition[0] + 1) >= itemCount) {
                    if (page < totalpage) {
                        page++;
                        musicPresenter.getMusicList(page);
                    }
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
    }

    public void init() {
        musicPresenter = new MusicPresenter();
        musicPresenter.attchView(this);
        musicPresenter.getMusicList(page);
    }

    @Override
    public void showDialog() {
        appCompatActivity.showLoading();
    }

    @Override
    public void dismissDialog() {
        appCompatActivity.dismissLoading();

    }

    @Override
    public void error(int code) {

    }

    @Override
    public void getMusicList(MusicList o) {
        if (o != null) {
            if (o.getList() != null && o.getList().size() != 0) {
                musics.addAll(o.getList());
                totalpage = o.getPagecount();
            }
        }

        musicListAdapter.notifyDataSetChanged();
    }
}
