package com.forwork.com.forwork.net.presenter;

import com.forwork.com.forwork.bean.MusicList;
import com.forwork.com.forwork.net.ApiCallBack;
import com.forwork.com.forwork.net.ApiClient;
import com.forwork.com.forwork.net.ListApi;
import com.forwork.com.forwork.net.view.IMusicView;

import org.reactivestreams.Subscription;

import io.reactivex.FlowableSubscriber;
import io.reactivex.annotations.NonNull;

/**
 * Created by Administrator on 2018/11/26.
 */

public class MusicPresenter extends BasePresenter<IMusicView> {
    ListApi listApi;

    public MusicPresenter() {
        listApi = ApiClient.getInstance().getMusicClient().create(ListApi.class);
    }

    public void getMusicList(int page) {
        showDialog();
        addSubscrbtion(listApi.getMusicList(page), new ApiCallBack<MusicList>() {

            @Override
            public void onSuccess(MusicList o) {
                iBaseView.getMusicList(o);
            }

            @Override
            public void error(Throwable t) {
                iBaseView.error(0);
            }

            @Override
            public void onFinish() {
                iBaseView.dismissDialog();
            }
        });
    }
}
