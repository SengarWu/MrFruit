package com.xpple.fruits.main.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.xpple.fruits.R;
import com.xpple.fruits.base.BaseFragment;
import com.xpple.fruits.me.ui.SeedDetailActivity;
import com.xpple.fruits.orchard.ui.OrchardActivity;
import com.xpple.fruits.shop.ui.ShoppingActivity;

public class MainFragment extends BaseFragment implements View.OnClickListener {
    private TextView tv_main_area;
    private ImageButton ib_main_select_shop;
    private ImageButton ib_main_fruit_buy;
    private ImageButton ib_main_orchard;
    private ImageButton ib_main_find;
    private TextView tv_main_fruit_name;
    private TextView tv_main_bargain_price;
    private TextView tv_main_weight;
    private ImageButton ib_main_buy_fruit;
    private ImageView iv_main_fruit_image;
    private TextView tv_main_get_number;
    private TextView tv_main_activity_seed;
    private ImageView iv_main_seed_image;
    private ImageButton ib_main_buy_seed;
    private View parentView;

    private AreaPopupWindow areaPopupWindow;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        parentView = inflater.inflate(R.layout.fragment_main, container, false);
        initView();
        return parentView;
    }

    private void initView() {
        tv_main_area = (TextView) parentView.findViewById(R.id.tv_main_area);
        ib_main_select_shop= (ImageButton) parentView.findViewById(R.id.ib_main_select_shop);
        ib_main_select_shop.setOnClickListener(this);
        ib_main_fruit_buy= (ImageButton) parentView.findViewById(R.id.ib_main_fruit_buy);
        ib_main_fruit_buy.setOnClickListener(this);
        ib_main_orchard= (ImageButton) parentView.findViewById(R.id.ib_main_orchard);
        ib_main_orchard.setOnClickListener(this);
        ib_main_find= (ImageButton) parentView.findViewById(R.id.ib_main_find);
        ib_main_find.setOnClickListener(this);
        ib_main_buy_fruit= (ImageButton) parentView.findViewById(R.id.ib_main_buy_fruit);
        ib_main_buy_fruit.setOnClickListener(this);
        ib_main_buy_seed= (ImageButton) parentView.findViewById(R.id.ib_main_buy_seed);
        ib_main_buy_seed.setOnClickListener(this);
        iv_main_seed_image= (ImageView) parentView.findViewById(R.id.iv_main_seed_image);
        iv_main_seed_image.setOnClickListener(this);
    }

    public static MainFragment newInstance() {
        
        Bundle args = new Bundle();
        
        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ib_main_select_shop://点击标题栏的商铺地点按钮进行水果商店选择
                areaPopupWindow = new AreaPopupWindow(getActivity(), new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //点击某个学校，目前只是关闭弹窗
                        areaPopupWindow.dismiss();
                        //tv_main_area.setText("天津工业大学");
                    }
                });
                // 以下拉方式显示
                areaPopupWindow.showAsDropDown(parentView.findViewById(R.id.rl_main_top));

                break;
            case R.id.ib_main_fruit_buy://点击水果购买跳转到购买水果的页面
                startActivity(new Intent(getActivity(), ShoppingActivity.class));
                break;
            case R.id.ib_main_orchard://点击果园物语跳转到自己的果园页面
                startActivity(new Intent(getActivity(), OrchardActivity.class));
                break;
            case R.id.ib_main_find://点击发现跳转到推文界面
                startActivity(new Intent(getActivity(),DiscoverActivity.class));
                break;
            case R.id.ib_main_buy_fruit://点击特价一栏将特价水果加入购物车

                break;
            case R.id.ib_main_buy_seed://点击活动一栏将活动种子加入购物车

                break;
            case R.id.iv_main_seed_image://点击果种图片查看果种详情
                startActivity(new Intent(getActivity(), SeedDetailActivity.class));
                break;

        }
    }
}
