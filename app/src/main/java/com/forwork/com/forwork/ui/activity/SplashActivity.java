package com.forwork.com.forwork.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.media.Image;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
import pub.devrel.easypermissions.EasyPermissions;

public class SplashActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks {
    Handler handler = new Handler();
    @BindView(R.id.splash_viewpager)
    ViewPager splash_viewpager;

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void setToolbar() {

    }

    @Override
    public void setStatusBar() {
        StatusBarUtils.setColor(this, getResources().getColor(R.color.color_00c3e5));
    }

    @Override
    public void initView() {
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

        splash_viewpager.setAdapter(new IndexBunnerAdapter(imageViews));
    }

    @Override
    public void initData() {
        String[] perms = {Manifest.permission.CAMERA};
        if (EasyPermissions.hasPermissions(this, perms)) {
            toMain();
        } else {
            EasyPermissions.requestPermissions(this, "camera", 0x11, perms);
        }
    }

    boolean isStart = false;

    public void toMain() {
        isStart = true;
        if (isStart) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(SplashActivity.this, IndexActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, 3000);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
        toMain();
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {

    }

}
