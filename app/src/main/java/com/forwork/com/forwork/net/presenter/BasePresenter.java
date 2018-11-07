package com.forwork.com.forwork.net.presenter;

import com.forwork.com.forwork.net.view.IBaseView;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/11/1.
 */

public class BasePresenter<T extends IBaseView> {
    T iBaseView;

    public void addSubscrbtion(Flowable flowable, FlowableSubscriber flowableSubscriber) {
        flowable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(flowableSubscriber);
    }

    public void attchView(T iBaseView) {
        this.iBaseView = iBaseView;
    }

    public void showDialog() {
        iBaseView.showDialog();
    }

    public void dismissDialog() {
        iBaseView.dismissDialog();
    }
}
