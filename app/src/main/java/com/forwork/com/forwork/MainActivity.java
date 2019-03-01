package com.forwork.com.forwork;

import android.os.Handler;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.forwork.com.forwork.bean.base.Book;
import com.forwork.com.forwork.ui.base.BaseActivity;
import com.forwork.com.forwork.ui.dialog.LoginDialog;
import com.forwork.com.forwork.ui.dialog.MyDialog;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {

    LoginDialog loginDialog;

    Handler handler = new Handler();
    MyDialog myDialog;
    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void setToolbar() {

    }

    @Override
    public void setStatusBar() {

    }

    @Override
    public void initView() {
        if (loginDialog == null){
            loginDialog = new LoginDialog();
        }
    final MyDialog myDialog = new MyDialog(this,R.style.loading_style);
        myDialog.getWindow().setWindowAnimations(R.style.dialog_anim);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
//                loginDialog.show(getSupportFragmentManager(),"");
                myDialog.show();
            }
        },1000);
//        loginDialog.show(getSupportFragmentManager(),"");

//
    }

    @Override
    public void initData() {

    }
}
