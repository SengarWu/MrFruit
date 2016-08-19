package com.xpple.fruits.me.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.xpple.fruits.R;
import com.xpple.fruits.base.BaseActivity;
import com.xpple.fruits.me.presenter.RegisterPresenter;
import com.xpple.fruits.me.presenter.RegisterPresenterImpl;
import com.xpple.fruits.me.view.RegisterView;
import com.xpple.fruits.view.DeletableEditText;

public class Register2Activity extends BaseActivity implements View.OnClickListener,RegisterView {
    private ImageButton ib_back;
    private TextView tv_title;
    private DeletableEditText et_register2_password;
    private DeletableEditText et_register2_confirm_password;
    private DeletableEditText et_register2_invite_code;
    private Button btn_register2_confirm;

    private RegisterPresenter presenter;

    private String message;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);
        initView();
        presenter = new RegisterPresenterImpl(this);
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
        btn_register2_confirm=$(R.id.btn_register2_confirm);
        btn_register2_confirm.setOnClickListener(this);
        et_register2_password=$(R.id.et_register2_password);
        et_register2_confirm_password=$(R.id.et_register2_confirm_password);
        et_register2_invite_code=$(R.id.et_register2_invite_code);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btn_register2_confirm://点击注册
                //message = presenter.onRegister()
                //startActivity(new Intent(Register2Activity.this,LoginActivity.class));
                break;
        }
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void registerSuccess() {

    }

    @Override
    public void registerFail() {

    }
}
