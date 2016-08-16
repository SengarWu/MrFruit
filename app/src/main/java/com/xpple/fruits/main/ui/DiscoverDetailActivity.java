package com.xpple.fruits.main.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageButton;

import com.xpple.fruits.R;
import com.xpple.fruits.base.BaseActivity;
import com.xpple.fruits.utils.StringUtil;

public class DiscoverDetailActivity extends BaseActivity implements View.OnClickListener {

    private ImageButton ib_back;
    private ImageButton ib_discover_share;
    private WebView wv_discover;

    private String link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover_detail);
        initView();
        initData();
        setupView();
    }

    private void setupView() {
        //设置webview支持javascript
        wv_discover.getSettings().setJavaScriptEnabled(true);
        //支持自动加载图片
        wv_discover.getSettings().setLoadsImagesAutomatically(true);
        //设置webview推荐使用的窗口，使html界面自适应屏幕
        wv_discover.getSettings().setUseWideViewPort(true);
        //设置webview推荐使用的窗口，使html界面自适应屏幕
        wv_discover.getSettings().setUseWideViewPort(true);
        //支持缩放
        wv_discover.getSettings().setSupportZoom(true);

        wv_discover.loadUrl(link);
    }

    private void initData() {
        Intent intent = getIntent();
        if (intent == null)
        {
           //加载错误提示页
            return;
        }
        link = intent.getStringExtra("link");
        if (StringUtil.isEmpty(link))
        {
            //加载错误提示页

            return;
        }
    }

    private void initView() {
        ib_back = $(R.id.ib_back);
        ib_back.setOnClickListener(this);
        ib_discover_share = $(R.id.ib_discover_share);
        ib_discover_share.setOnClickListener(this);
        wv_discover = $(R.id.wv_discover);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.ib_back:
                finish();
                break;
            case R.id.ib_discover_share:
                //社会化分享
                break;
        }
    }
}
