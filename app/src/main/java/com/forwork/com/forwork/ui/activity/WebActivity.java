package com.forwork.com.forwork.ui.activity;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.forwork.com.forwork.R;
import com.forwork.com.forwork.ui.base.BaseActivity;
import com.forwork.com.forwork.utils.StatusBarUtils;
import com.forwork.com.forwork.view.dialog.LoadingDialog;

import butterknife.BindView;

public class WebActivity extends BaseActivity {
    @BindView(R.id.web_webview)
    WebView web_webview;

    LoadingDialog loadingDialog;
    private String TAG = "web";

    @Override
    public int getLayoutId() {
        return R.layout.activity_web;
    }

    @Override
    public void setToolbar() {
//        View viewById = findViewById(R.id.inclued_toolbar);
//        if (viewById!=null){
            toolbar_back = (ImageView) findViewById(R.id.item_toolbar_back);
            toolbar_name = (TextView) findViewById(R.id.item_toolbar_name);
            mToolbar = (Toolbar) findViewById(R.id.item_toolbar);
//            setSupportActionBar(mToolbar);
//        }
        mToolbar.setTitle("");
        toolbar_name.setText("详情");
        toolbar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void setStatusBar() {
        StatusBarUtils.setColor(this,getResources().getColor(R.color.color_00c3e6));
    }

    @Override
    public void initView() {
        loadingDialog = new LoadingDialog(this,R.style.loading_style);
    }

    @Override
    public void initData() {
        web_webview.addJavascriptInterface(true,"android");

        WebSettings webSettings = web_webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setAllowContentAccess(true);
        webSettings.setAppCacheEnabled(false);
        webSettings.setBuiltInZoomControls(false);
//        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);

        WebViewClient webViewClient = new WebViewClient(){

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                loadingDialog.show();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                loadingDialog.dismiss();
            }

            @Override
            public void onLoadResource(WebView view, String url) {
                super.onLoadResource(view, url);
            }

            @Override
            public void onPageCommitVisible(WebView view, String url) {
                super.onPageCommitVisible(view, url);
            }
        };
        WebChromeClient webChromeClient = new WebChromeClient(){
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                toolbar_name.setText(title);
            }
        };

        web_webview.setWebViewClient(webViewClient);
        web_webview.setWebChromeClient(webChromeClient);

//        web_webview.loadUrl(getIntent().getStringExtra("url"));
        web_webview.loadUrl("http://www.baidu.com");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.e(TAG, "onKeyDown: "+web_webview.canGoBack() );
        if (web_webview.canGoBack()){
            web_webview.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);

    }
}
