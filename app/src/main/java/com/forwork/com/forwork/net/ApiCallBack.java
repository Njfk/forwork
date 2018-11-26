package com.forwork.com.forwork.net;

import org.reactivestreams.Subscription;

import io.reactivex.FlowableSubscriber;
import io.reactivex.annotations.NonNull;

/**
 * Created by Administrator on 2018/11/26.
 */

public abstract class ApiCallBack<T extends Object> implements FlowableSubscriber<T> {
    public abstract void onSuccess(T t);
    public abstract void error(Throwable t);
    public abstract void onFinish();

    @Override
    public void onComplete() {
        onFinish();
    }

    @Override
    public void onSubscribe(@NonNull Subscription s) {
        s.request(2);
    }

    @Override
    public void onNext(T t) {
        onSuccess(t);
    }

    @Override
    public void onError(Throwable t) {
        error(t);
    }
}
