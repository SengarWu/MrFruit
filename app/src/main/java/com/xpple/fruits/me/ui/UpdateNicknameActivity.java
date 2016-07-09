package com.xpple.fruits.me.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.xpple.fruits.R;
import com.xpple.fruits.base.BaseActivity;

public class UpdateNicknameActivity extends BaseActivity implements View.OnClickListener {
    private ImageButton ib_back;
    private EditText et_update_nickname;
    private TextView tv_update_nickname_finish;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_nickname);
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
        et_update_nickname=$(R.id.et_update_nickname);
        et_update_nickname.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.et_update_nickname://修改昵称后点击完成

                break;
        }
    }
}
