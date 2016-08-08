package com.xpple.fruits.shop.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.xpple.fruits.R;
import com.xpple.fruits.base.BaseActivity;

public class ShoppingActivity extends BaseActivity implements View.OnClickListener {

    private ImageButton ib_back;
    private TextView tv_title;
    private RadioGroup rg_shop;
    private FrameLayout fl_shop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);
        initView();
        tv_title.setText("水果专区");
    }

    private void initView() {
        ib_back = $(R.id.ib_back);
        ib_back.setOnClickListener(this);
        tv_title = $(R.id.tv_title);
        rg_shop = $(R.id.rg_shop);
        fl_shop = $(R.id.fl_shop);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.ib_back:
                finish();
                break;
        }
    }
}
