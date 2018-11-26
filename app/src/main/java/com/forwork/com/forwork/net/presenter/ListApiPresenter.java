package com.forwork.com.forwork.net.presenter;

import com.forwork.com.forwork.bean.IndexBean1;
import com.forwork.com.forwork.net.ApiClient;
import com.forwork.com.forwork.net.ListApi;
import com.forwork.com.forwork.net.view.IBaseView;
import com.forwork.com.forwork.net.view.IListView;

import org.reactivestreams.Subscription;

import io.reactivex.FlowableSubscriber;
import io.reactivex.annotations.NonNull;

/**
 * Created by Administrator on 2018/11/1.
 */

public class ListApiPresenter extends BasePresenter<IListView>{
    ListApi listApi;
    public ListApiPresenter() {
        listApi = ApiClient.getInstance().getClient().create(ListApi.class);
    }

    public void getList1(int page){
//        iBaseView.showDialog();
        addSubscrbtion(listApi.getList1(page), new FlowableSubscriber<IndexBean1>() {
            @Override
            public void onSubscribe(@NonNull Subscription s) {
                s.request(2);
            }

            @Override
            public void onNext(IndexBean1 o) {
                iBaseView.getListSuccess(o);
            }

            @Override
            public void onError(Throwable t) {
                iBaseView.error(0);
                onComplete();
            }

            @Override
            public void onComplete() {
                iBaseView.dismissDialog();
            }
        });
    }

}
