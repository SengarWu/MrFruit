package com.xpple.fruits.main.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.xpple.fruits.R;
import com.xpple.fruits.base.BaseFragment;
import com.xpple.fruits.shop.ui.SeedDetailActivity;
import com.xpple.fruits.orchard.ui.OrchardActivity;
import com.xpple.fruits.shop.ui.ShoppingActivity;
import com.xpple.fruits.utils.ToastUtil;
import com.xpple.fruits.view.BannerLayout;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends BaseFragment implements View.OnClickListener {
    private TextView tv_main_area;
    private ImageButton ib_main_select_shop;
    private ImageButton ib_main_fruit_buy;
    private ImageButton ib_main_orchard;
    private ImageButton ib_main_find;
    private TextView tv_main_fruit_name;
    private TextView tv_main_bargain_price;
    private TextView tv_main_weight;
    private Button btn_main_buy_fruit;
    private ImageView iv_main_fruit_image;
    private TextView tv_main_get_number;
    private TextView tv_main_activity_seed;
    private ImageView iv_main_seed_image;
    private Button btn_main_buy_seed;
    private BannerLayout bl_main;
    private AreaPopupWindow areaPopupWindow;

    private View parentView;

    List<String> urls;
    List<String> links;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        parentView = inflater.inflate(R.layout.fragment_main, container, false);
        initView();
        initData();
        return parentView;
    }

    private void initData() {
        urls = new ArrayList<>();
        urls.add("http://mmbiz.qpic.cn/mmbiz/PY1lZ0Jrn5u0ibyOSHvJzkXJUNQrYZddeju0oOZ0LQpAxAPRJvJJDqbqyvPeuQA95VqlvMtp35df5oZCXict7uBQ/640?wx_fmt=jpeg&wxfrom=5&wx_lazy=1");
        urls.add("http://mmbiz.qpic.cn/mmbiz/PY1lZ0Jrn5vTAATVH4tgRTfhob579P4UspavECC5y4LoIl54jqpcRxTF4zx7gCPibOWjc2TC7ObmRZsIhicDGDpg/640?wx_fmt=jpeg&wxfrom=5&wx_lazy=1");
        urls.add("http://b.hiphotos.baidu.com/image/h%3D200/sign=8e8564a10c46f21fd6345953c6256b31/00e93901213fb80e22a34b8d30d12f2eb938947d.jpg");
        bl_main.setViewUrls(urls);
        links = new ArrayList<>();
        links.add("http://mp.weixin.qq.com/s?__biz=MzI5MjA3Mzc3Ng==&mid=2652588910&idx=2&sn=bf55c8bab682a6291b90fc85f15f4c7e&scene=1&srcid=0817S0cGFMvEcbSyTLgpQXjZ#rd");
        links.add("http://mp.weixin.qq.com/s?__biz=MzI5MjA3Mzc3Ng==&mid=2652588920&idx=1&sn=12648f9ce7c9137f5644e9bc480679d7&scene=1&srcid=0817Bwmd4buuNAboMo5542A4#rd");
        links.add("http://mp.weixin.qq.com/s?__biz=MzI5MjA3Mzc3Ng==&mid=2652588749&idx=1&sn=07bd5a59c22b10e698533960c0a38d1e&scene=1&srcid=0817W0BEYDwZ6taWCU1ml6U8#rd");
        bl_main.setOnBannerItemClickListener(new BannerLayout.OnBannerItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getActivity(),DiscoverDetailActivity.class);
                intent.putExtra("link",links.get(position));
                startActivity(intent);
            }
        });
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
        btn_main_buy_fruit= (Button) parentView.findViewById(R.id.btn_main_buy_fruit);
        btn_main_buy_fruit.setOnClickListener(this);
        btn_main_buy_seed= (Button) parentView.findViewById(R.id.btn_main_buy_seed);
        btn_main_buy_seed.setOnClickListener(this);
        iv_main_seed_image= (ImageView) parentView.findViewById(R.id.iv_main_seed_image);
        iv_main_seed_image.setOnClickListener(this);
        bl_main = (BannerLayout) parentView.findViewById(R.id.bl_main);
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
                        switch (view.getId())
                        {
                            case R.id.btn_area1:
                                tv_main_area.setText("天津工业大学");
                                break;
                            case R.id.btn_area2:
                            case R.id.btn_area3:
                            case R.id.btn_area4:
                                ToastUtil.showShort(getActivity(),"该区域正在接入中...");
                                break;
                        }
                        areaPopupWindow.dismiss();
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
            case R.id.btn_main_buy_fruit://点击特价一栏将特价水果加入购物车

                break;
            case R.id.btn_main_buy_seed://点击活动一栏将活动种子加入购物车

                break;
            case R.id.iv_main_seed_image://点击果种图片查看果种详情
                startActivity(new Intent(getActivity(), SeedDetailActivity.class));
                break;

        }
    }
}
