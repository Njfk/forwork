package com.forwork.com.forwork.net;

import com.forwork.com.forwork.bean.IndexBean1;
import com.forwork.com.forwork.bean.MusicList;

import java.util.Observer;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2018/11/1.
 */
//音乐列表
//http://218.200.160.29/rdp2/l10n/content/songList.do?ua=Android_bayge&version=2.1.1&lang=0&groupcode=20818736%2F21313936%2F21327854&pageno=1

public interface ListApi {

    @GET("?act=discover&type=0&gc=0&isTmall=0&sort=sort&discount=&commissionRate=&appVersion=3.2.0")
    Flowable<IndexBean1> getList1(@Query("page") int page);

    @GET("/rdp2/l10n/content/songList.do?ua=Android_bayge&version=2.1.1&lang=0&groupcode=20818736%2F21313936%2F21327854")
    Flowable<MusicList> getMusicList(@Query("pageno")int pageno);
}
