package com.xpple.fruits.me.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.xpple.fruits.R;
import com.xpple.fruits.base.BaseActivity;

public class UpdatePasswordActivity extends BaseActivity implements View.OnClickListener {
    private ImageButton ib_back;
    private TextView tv_title;
    private EditText et_update_password_name;
    private EditText et_update_password_invite;
    private EditText et_update_password_newpassword;
    private EditText et_update_password_confirm;
    private Button bt_update_password_getcode;
    private Button bt_update_password_confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_password);
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
        tv_title.setText("修改密码");
        bt_update_password_getcode=$(R.id.bt_update_password_getcode);
        bt_update_password_getcode.setOnClickListener(this);
        bt_update_password_confirm=$(R.id.bt_update_password_confirm);
        bt_update_password_confirm.setOnClickListener(this);
        et_update_password_name=$(R.id.et_update_password_name);
        et_update_password_invite=$(R.id.et_update_password_invite);
        et_update_password_newpassword=$(R.id.et_update_password_newpassword);
        et_update_password_confirm=$(R.id.et_update_password_confirm);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_update_password_getcode://点击获取验证码按钮

                break;
            case R.id.bt_update_password_confirm://点击确认按钮

                break;

        }
    }
}
