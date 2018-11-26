package com.forwork.com.forwork.net;

import android.util.Log;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2018/11/1.
 */

public class ApiClient {
    public static ApiClient apiClient;
    private Retrofit retrofit;
    private Retrofit mMusciRetrofit;
    private String TAG = "http";

    public static ApiClient getInstance(){
        if (apiClient == null){
           apiClient = new ApiClient();
        }

        return apiClient;
    }

    public Retrofit getClient(){
        if (retrofit == null){
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.addInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String message) {
                    Log.e(TAG, "log: "+message );
                }
            }).setLevel(HttpLoggingInterceptor.Level.BASIC));
            retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(builder.build())
                    .baseUrl("http://tao.lexiangquan.com/")
                    .build();
        }
        return retrofit;
    }
    public Retrofit getMusicClient(){
        if (mMusciRetrofit == null){
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.addInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String message) {
                    Log.e("Music", "log: "+message );
                }
            }).setLevel(HttpLoggingInterceptor.Level.BASIC));
            mMusciRetrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(builder.build())
                    .baseUrl("http://218.200.160.29/")
                    .build();
        }
        return mMusciRetrofit;
    }


}
