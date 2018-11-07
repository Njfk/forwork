package com.forwork.com.forwork.net;

import com.forwork.com.forwork.bean.IndexBean1;

import java.util.Observer;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2018/11/1.
 */

public interface ListApi {

    @GET("?act=discover&type=0&gc=0&isTmall=0&sort=sort&discount=&commissionRate=&appVersion=3.2.0")
    Flowable<IndexBean1> getList1(@Query("page")int page);
}
