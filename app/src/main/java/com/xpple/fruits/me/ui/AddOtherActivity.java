package com.xpple.fruits.me.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.xpple.fruits.R;
import com.xpple.fruits.base.BaseActivity;
import com.xpple.fruits.cart.ui.AccountActivity;

public class AddOtherActivity extends BaseActivity implements View.OnClickListener {
    private EditText et_add_other_content;
    private TextView tv_add_other_finish;
    private ImageButton ib_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_other);
        initView();
    }

    private void initView() {
        tv_add_other_finish=$(R.id.tv_add_other_finish);
        tv_add_other_finish.setOnClickListener(this);
        ib_back = $(R.id.ib_back);
        ib_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        et_add_other_content=$(R.id.et_add_other_content);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_add_other_finish://点击完成
                Intent intent = new Intent(AddOtherActivity.this,AccountActivity.class);
                intent.putExtra("remarks",et_add_other_content.getText().toString());
                setResult(1,intent);
                finish();
                break;
        }
    }
}
