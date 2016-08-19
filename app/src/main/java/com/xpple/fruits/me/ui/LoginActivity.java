package com.xpple.fruits.me.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.xpple.fruits.R;
import com.xpple.fruits.base.BaseActivity;
import com.xpple.fruits.me.presenter.LoginPresenter;
import com.xpple.fruits.me.presenter.LoginPresenterImpl;
import com.xpple.fruits.me.view.LoginView;
import com.xpple.fruits.utils.ToastUtil;
import com.xpple.fruits.view.DeletableEditText;

public class LoginActivity extends BaseActivity implements View.OnClickListener,LoginView {
    private DeletableEditText et_login_tel;
    private DeletableEditText et_login_password;
    private TextView tv_login_register;
    private Button btn_login_account;
    private TextView tv_login_update_password;

    private ProgressBar progressBar;

    private LoginPresenter presenter;

    private String message;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        presenter = new LoginPresenterImpl(this);
    }

    private void initView() {
        tv_login_register=$(R.id.tv_login_register);
        tv_login_register.setOnClickListener(this);
        btn_login_account=$(R.id.bt_login_account);
        btn_login_account.setOnClickListener(this);
        tv_login_update_password=$(R.id.tv_login_update_password);
        tv_login_update_password.setOnClickListener(this);
        et_login_tel=$(R.id.et_login_tel);
        et_login_password=$(R.id.et_login_password);
        progressBar = new ProgressBar(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_login_register://点击注册
                startActivity(new Intent(LoginActivity.this,Register1Activity.class));
                break;
            case R.id.btn_login_account://点击登录
                String username = et_login_tel.getText().toString();
                String password = et_login_password.getText().toString();
                if (!isNetConnected())
                {
                    ToastUtil.showShort(this,"当前网络不可用，请检查网络");
                    return;
                }
                if (TextUtils.isEmpty(username))
                {
                    ToastUtil.showShort(this,"请输入用户名/手机号");
                    return;
                }
                //调用presenter执行业务逻辑。返回执行结果信息；只接收信息，与操作无关
                message = presenter.validateCredentials(username,password);
                break;
            case R.id.tv_login_update_password://点击忘记密码
                startActivity(new Intent(LoginActivity.this,UpdatePasswordActivity.class));
                break;
        }
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void loginFail() {
        ToastUtil.showShort(this,message);
    }

    @Override
    public void loginSuccess() {
        ToastUtil.showShort(this,message);

    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }
}
