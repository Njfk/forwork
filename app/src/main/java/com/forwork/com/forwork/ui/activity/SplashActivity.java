package com.forwork.com.forwork.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.media.Image;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.forwork.com.forwork.R;
import com.forwork.com.forwork.ui.adapter.IndexBunnerAdapter;
import com.forwork.com.forwork.ui.base.BaseActivity;
import com.forwork.com.forwork.utils.StatusBarUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

public class SplashActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks {
    Handler handler = new Handler();
    @BindView(R.id.splash_viewpager)
    ViewPager splash_viewpager;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    public void initView() {
        StatusBarUtils.setColor(this, getResources().getColor(R.color.white));
        int[] imgs = new int[]{
                R.drawable.navigation_1,
                R.drawable.navigation_2,
                R.drawable.navigation_3
        };
        List<ImageView> imageViews = new ArrayList<>();
        for (int i = 0;i<imgs.length;i++){

            ImageView imageView = new ImageView(this);
            imageView.setImageResource(imgs[i]);

            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);


            imageViews.add(imageView);
        }
        imageViews.get(2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toMain(0);
            }
        });

        splash_viewpager.setAdapter(new IndexBunnerAdapter(imageViews));
    }

    public void initData() {
        String[] perms = {Manifest.permission.CAMERA};
        if (EasyPermissions.hasPermissions(this, perms)) {
            toMain(3000);
        } else {
            EasyPermissions.requestPermissions(this, "请打开相机权限", 101, perms);
        }
    }

    boolean isStart = false;

    public void toMain(long time) {
        isStart = true;
        if (isStart) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(SplashActivity.this, IndexActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, time);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
        toMain(0);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        Log.e("22", "onPermissionsGranted: " );
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        Log.e("22", "onPermissionsDenied: " );
        if (EasyPermissions.somePermissionDenied(this)){
            new AppSettingsDialog.Builder(this).build().show();
        }
    }

}
