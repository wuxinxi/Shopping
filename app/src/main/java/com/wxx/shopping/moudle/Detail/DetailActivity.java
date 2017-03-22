package com.wxx.shopping.moudle.Detail;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.wxx.shopping.R;
import com.wxx.shopping.base.BaseActivity;
import com.wxx.shopping.base.BasePresenter;

import butterknife.BindView;

/**
 * 作者：Tangren_ on 2017/3/19 18:19.
 * 邮箱：wu_tangren@163.com
 * TODO:一句话描述
 */

public class DetailActivity extends BaseActivity {
    @BindView(R.id.search)
    TextView search;
    @BindView(R.id.webView)
    WebView webView;
    @BindView(R.id.toolBar)
    Toolbar toolBar;

    private static final String MSG = "URL";
    @BindView(R.id.progress)
    ProgressBar progress;

    private String URL;

    public static void startActivity(Context context, String msg) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(MSG, msg);
        context.startActivity(intent);
    }

    @Override
    protected void initView() {
        URL = getIntent().getStringExtra(MSG);
        toolBar.setTitle("商品详情");
        search.setVisibility(View.GONE);
        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        webView.getSettings().setJavaScriptEnabled(true);
        WebSettings settings = webView.getSettings();
        if (Build.VERSION.SDK_INT == 17)
            settings.setDisplayZoomControls(false);
        settings.setLoadWithOverviewMode(true);

//        DisplayMetrics metrics = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(metrics);
//        int mDensity = metrics.densityDpi;
//        Logger.d("屏幕的密度为:" + mDensity);
//        switch (mDensity) {
//            case 120:
//                settings.setDefaultZoom(WebSettings.ZoomDensity.CLOSE);
//                break;
//            case 160:
//                settings.setDefaultZoom(WebSettings.ZoomDensity.MEDIUM);
//                break;
//            case 240:
//                settings.setDefaultZoom(WebSettings.ZoomDensity.FAR);
//                break;
//        }
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        webView.setWebChromeClient(new MyWebChromeClient());
        webView.setWebViewClient(new MyWebViewClient());
        webView.loadUrl(URL);
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_detail;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    class MyWebViewClient extends WebViewClient {

        public MyWebViewClient() {
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            view.loadUrl(URL);
            return true;
        }
    }


    class MyWebChromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);

            if (newProgress == 100)
                progress.setVisibility(View.GONE);
            else progress.setProgress(newProgress);
        }
    }
}
