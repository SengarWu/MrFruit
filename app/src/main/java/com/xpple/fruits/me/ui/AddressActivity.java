package com.xpple.fruits.me.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.xpple.fruits.R;
import com.xpple.fruits.base.BaseActivity;

public class AddressActivity extends BaseActivity implements View.OnClickListener {
    private ImageButton ib_back;
    private TextView tv_title;
    private Button bt_address_out;
    private EditText et_address_name;
    private EditText et_address_tel;
    private EditText et_address_home;
    private EditText et_address_detail;
    private ImageView iv_address_more;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        initView();
    }

    private void initView() {
        ib_back = $(R.id.ib_back);
        ib_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tv_title = $(R.id.tv_title);
        tv_title.setText("收货信息");
        bt_address_out=$(R.id.bt_address_out);
        bt_address_out.setOnClickListener(this);
        iv_address_more=$(R.id.iv_address_more);
        iv_address_more.setOnClickListener(this);
        et_address_name=$(R.id.et_address_name);
        et_address_tel=$(R.id.et_address_tel);
        et_address_home=$(R.id.et_address_home);
        et_address_detail=$(R.id.et_address_detail);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_address_out://点击确认按钮
                startActivity(new Intent(AddressActivity.this,AccountActivity.class));
                break;
            case R.id.iv_address_more://点击地址栏下拉按钮

                break;
        }
    }
}
