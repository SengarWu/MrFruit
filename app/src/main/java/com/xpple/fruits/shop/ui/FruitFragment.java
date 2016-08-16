package com.xpple.fruits.shop.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xpple.fruits.R;
import com.xpple.fruits.base.BaseFragment;
import com.xpple.fruits.bean.FruitEntity;
import com.xpple.fruits.shop.adapter.FruitAdapter;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class FruitFragment extends BaseFragment implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {

    private View parentView;

    private DecimalFormat df   = new DecimalFormat("######0.0");
    private static double total = 0;

    private FruitAdapter adapter;
    private List<FruitEntity> fruits;

    private RecyclerView rv_shop;
    private ImageButton ib_shop_cart;
    private TextView tv_shop_message;
    private TextView tv_shop_total;
    private Button btn_shop_settle;
    private SwipeRefreshLayout srl_fruit;

    private final int REFRESH_DELAY = 1001;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what)
            {
                case REFRESH_DELAY:
                    initData();
                    setupView();
                    srl_fruit.setRefreshing(false);
                    break;
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        parentView = inflater.inflate(R.layout.fragment_fruit, container, false);
        initView();
        initData();
        setupView();
        return parentView;

    }

    private void initView() {
        rv_shop = (RecyclerView) parentView.findViewById(R.id.rv_shop);
        rv_shop.setLayoutManager(new LinearLayoutManager(getActivity()));
        ib_shop_cart = (ImageButton) parentView.findViewById(R.id.ib_shop_cart);
        ib_shop_cart.setOnClickListener(this);
        tv_shop_message = (TextView) parentView.findViewById(R.id.tv_shop_message);
        tv_shop_total = (TextView) parentView.findViewById(R.id.tv_shop_total);
        btn_shop_settle = (Button) parentView.findViewById(R.id.btn_shop_settle);
        btn_shop_settle.setOnClickListener(this);
        srl_fruit = (SwipeRefreshLayout) parentView.findViewById(R.id.srl_fruit);
        srl_fruit.setColorSchemeResources(R.color.base_color_yellow,R.color.base_color_brown);
        srl_fruit.setOnRefreshListener(this);
    }

    private void setupView() {
        adapter = new FruitAdapter(fruits);
        //设置动画效果
        adapter.openLoadAnimation();
        //加载更多
        adapter.openLoadMore(20,true);
        rv_shop.setAdapter(adapter);
        //回调子控件点击事件
        adapter.setOnRecyclerViewItemChildClickListener(new BaseQuickAdapter.OnRecyclerViewItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int position) {
                FruitEntity fruit = (FruitEntity) baseQuickAdapter.getItem(position);
                switch (view.getId())
                {
                    case R.id.ib_fruit_sub:
                        if (fruit.fruit_num < 2)
                        {
                            fruit.visible = false;
                        }
                        else
                        {
                            fruit.fruit_num--;
                        }
                        break;
                    case R.id.ib_fruit_add:
                        if (fruit.fruit_num >= 0)
                        {
                            fruit.visible = true;
                            fruit.fruit_num++;
                        }
                        break;
                    default:
                        break;
                }
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void initData() {
        if (fruits != null)
        {
            fruits.clear();
        }
        fruits = new ArrayList<FruitEntity>();
        for (int i = 0; i < 20; i++) {
            FruitEntity fruit = new FruitEntity();
            fruit.fruit_name = "柠檬";
            fruit.fruit_price = 10.0;
            fruit.fruit_discount = 8.0;
            fruit.fruit_unit = "个";
            fruit.fruit_num = 0;
            fruit.visible = false;
            fruits.add(fruit);
        }
    }


    @Override
    public void onClick(View view) {

    }

    @Override
    public void onRefresh() {
        handler.sendEmptyMessageDelayed(REFRESH_DELAY,1000);
    }
}
