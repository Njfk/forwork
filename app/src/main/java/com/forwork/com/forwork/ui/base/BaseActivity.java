package com.forwork.com.forwork.ui.base;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.forwork.com.forwork.R;
import com.forwork.com.forwork.utils.ActivityStack;
import com.forwork.com.forwork.view.dialog.LoadingFragment;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/10/23.
 */

public abstract class BaseActivity extends AppCompatActivity {

    public Handler handler = new Handler();
    LoadingFragment loadingFragment;
    public  Toolbar mToolbar;
    public TextView toolbar_name;
    public  ImageView toolbar_back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ActivityStack.getInstance().addActivity(this);
        ButterKnife.bind(this);
        setToolbar();
        setStatusBar();


        init();
        initView();
        initData();
    }

    public void init(){

        loadingFragment = new LoadingFragment();


    }

    public abstract int getLayoutId();

    public abstract void setToolbar();

    public abstract void setStatusBar();

    public abstract void initView();

    public abstract void initData();

    public void showLoading(){
        if (loadingFragment!=null&&!loadingFragment.isAdded()){
            loadingFragment.show(getSupportFragmentManager(),"");
        }

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                dismissLoading();
            }
        },3000);
    }

    public void dismissLoading(){
        if (loadingFragment!=null&&loadingFragment.isAdded()){
            loadingFragment.dismiss();
        }
    }

}
