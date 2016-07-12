package com.xpple.fruits.me.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.xpple.fruits.R;
import com.xpple.fruits.base.BaseActivity;

public class InviteFriendActivity extends BaseActivity implements View.OnClickListener {
    private ImageButton ib_back;
    private TextView tv_title;
    private TextView tv_invite_friend_code;
    private Button bt_invite_friend_copy_code;
    private TextView tv_invite_friend_share;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite_friend);
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
        tv_title.setText("邀请好友");
        bt_invite_friend_copy_code=$(R.id.bt_invite_friend_copy_code);
        bt_invite_friend_copy_code.setOnClickListener(this);
        tv_invite_friend_share=$(R.id.tv_invite_friend_share);
        tv_invite_friend_share.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.bt_invite_friend_copy_code://点击复制邀请码

                break;
            case R.id.tv_invite_friend_share://分享至社交媒体

                break;
        }
    }
}
