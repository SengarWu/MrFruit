package com.xpple.fruits.me.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xpple.fruits.R;
import com.xpple.fruits.base.BaseActivity;

public class UpdatePersonalActivity extends BaseActivity implements View.OnClickListener {
    private ImageButton ib_back;
    private TextView tv_title;
    private ImageView iv_update_person_head;
    private TextView tv_update_person_account;
    private TextView tv_update_person_nickname;
    private TextView tv_update_person_address;
    private RelativeLayout rl_update_person_head;
    private RelativeLayout rl_update_person_nickname;
    private RelativeLayout rl_update_person_address;
    private RelativeLayout rl_update_person_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_personal);

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
        tv_title.setText("修改信息");
        rl_update_person_head=$(R.id.rl_update_person_head);
        rl_update_person_head.setOnClickListener(this);
        rl_update_person_nickname=$(R.id.rl_update_person_nickname);
        rl_update_person_nickname.setOnClickListener(this);
        rl_update_person_address=$(R.id.rl_update_person_address);
        rl_update_person_address.setOnClickListener(this);
        rl_update_person_password=$(R.id.rl_update_person_password);
        rl_update_person_password.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.rl_update_person_head://点击修改头像一栏
                break;
            case R.id.rl_update_person_nickname://点击昵称一栏
                startActivity(new Intent(UpdatePersonalActivity.this,UpdateNicknameActivity.class));
                break;
            case R.id.rl_update_person_address://点击收货信息一栏
                break;
            case R.id.rl_update_person_password://点击修改密码一栏
                break;
        }
    }
}
