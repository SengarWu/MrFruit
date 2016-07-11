package com.xpple.fruits.me.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.xpple.fruits.R;
import com.xpple.fruits.base.BaseActivity;

public class Register1Activity extends BaseActivity implements View.OnClickListener {
    private ImageButton ib_back;
    private TextView tv_title;
    private EditText et_register1_name;
    private EditText et_register1_tel;
    private EditText et_register1_password;
    private Button bt_register1_getcode;
    private Button bt_register1_confirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register1);
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
        tv_title.setText("注册");
        bt_register1_getcode=$(R.id.bt_register1_getcode);
        bt_register1_getcode.setOnClickListener(this);
        bt_register1_confirm=$(R.id.bt_register1_confirm);
        bt_register1_confirm.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
       switch (v.getId()){
           case R.id.bt_register1_getcode://点击发送验证码

               break;
           case R.id.bt_register1_confirm://点击下一步
               startActivity(new Intent(Register1Activity.this,Register2Activity.class));
               break;
       }
    }
}
