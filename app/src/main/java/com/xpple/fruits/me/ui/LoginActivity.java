package com.xpple.fruits.me.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.xpple.fruits.R;
import com.xpple.fruits.base.BaseActivity;

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private EditText et_login_tel;
    private EditText et_login_password;
    private ImageView iv_login_delete;
    private TextView tv_login_register;
    private Button bt_login_account;
    private TextView tv_login_update_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        iv_login_delete=$(R.id.iv_login_delete);
        iv_login_delete.setOnClickListener(this);
        tv_login_register=$(R.id.tv_login_register);
        tv_login_register.setOnClickListener(this);
        bt_login_account=$(R.id.bt_login_account);
        bt_login_account.setOnClickListener(this);
        tv_login_update_password=$(R.id.tv_login_update_password);
        tv_login_update_password.setOnClickListener(this);
        et_login_tel=$(R.id.et_login_tel);
        et_login_password=$(R.id.et_login_password);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_login_delete://点击删除

                break;
            case R.id.tv_login_register://点击注册
                startActivity(new Intent(LoginActivity.this,Register1Activity.class));
                break;
            case R.id.bt_login_account://点击登录

                break;
            case R.id.tv_login_update_password://点击忘记密码
                startActivity(new Intent(LoginActivity.this,UpdatePasswordActivity.class));
                break;
        }
    }
}
