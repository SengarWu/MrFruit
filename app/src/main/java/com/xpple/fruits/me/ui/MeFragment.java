package com.xpple.fruits.me.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xpple.fruits.R;
import com.xpple.fruits.base.BaseFragment;

public class MeFragment extends BaseFragment implements View.OnClickListener {

    private View parentView;
    private ImageView iv_mine_head;
    private TextView tv_mine_nickname;
    private ImageButton ib_mine_list;
    private ImageButton ib_mine_update;
    private ImageButton ib_mine_delete;
    private ImageButton ib_mine_add;
    private ImageButton ib_mine_fruit;
    private TextView tv_mine_growth;
    private TextView tv_get_coin;
    private LinearLayout ll_mine_orchard;
    private LinearLayout ll_mine_getcoin;
    private LinearLayout ll_mine_address;
    private LinearLayout ll_mine_about;
    private LinearLayout ll_mine_back;
    private Button bt_mine_out;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        parentView = inflater.inflate(R.layout.fragment_me, container, false);
        initView();
        return parentView;
    }

    private void initView() {
        ib_mine_list = (ImageButton) parentView.findViewById(R.id.ib_mine_list);
        ib_mine_list.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {

            case R.id.ib_mine_list://我的订单

                break;
            case R.id.ib_mine_update://版本更新

                break;

            case R.id.ib_mine_delete://清除缓存

                break;

            case R.id.ib_mine_add://邀请好友

                break;

            case R.id.ib_mine_fruit://收获水果

                break;
            case R.id.ll_mine_orchard://前往果园

                break;
            case R.id.ll_mine_getcoin://充值

                break;
            case R.id.ll_mine_personal://修改信息

                break;
            case R.id.ll_mine_about://关于我们

                break;
            case R.id.ll_mine_back://反馈

                break;
            case R.id.bt_mine_out://退出帐号

                break;
        }
    }
}
