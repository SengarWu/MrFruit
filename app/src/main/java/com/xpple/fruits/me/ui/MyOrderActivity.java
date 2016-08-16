package com.xpple.fruits.me.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xpple.fruits.R;
import com.xpple.fruits.base.BaseActivity;
import com.xpple.fruits.bean.CartEntity;
import com.xpple.fruits.bean.OrderEntity;
import com.xpple.fruits.cart.adapter.AccountAdapter;
import com.xpple.fruits.me.adapter.OrderAdapter;

import java.util.ArrayList;
import java.util.List;

public class MyOrderActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = "MyOrderActivity";

    private ImageButton ib_back;
    private TextView tv_title;
    private SwipeRefreshLayout srl_order;
    private RecyclerView rv_order;

    private List<OrderEntity> orders;
    private OrderAdapter adapter_order;

    private List<CartEntity> carts;
    private AccountAdapter adapter_account;

    private OrderPopupWindow orderPopupWindow;

    private final int REFRESH_DELAY = 1001;
    private Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what)
            {
                case REFRESH_DELAY:
                    initData();
                    setupView();
                    srl_order.setRefreshing(false);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);
        initView();
        initData();
        setupView();
    }

    private void setupView() {
        adapter_order = new OrderAdapter(orders);
        //设置动画效果
        adapter_order.openLoadAnimation();
        //加载更多
        adapter_order.openLoadMore(20,true);

        rv_order.setAdapter(adapter_order);
        adapter_order.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                //订单详情列表加载
                adapter_account = new AccountAdapter(carts);
                //设置动画效果
                adapter_account.openLoadAnimation();
                //加载更多
                adapter_account.openLoadMore(20,true);

                orderPopupWindow = new OrderPopupWindow(MyOrderActivity.this, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        switch (view.getId())
                        {
                            case R.id.ib_order_detail_close:
                                orderPopupWindow.dismiss();
                                break;
                            case R.id.btn_order_detail1:

                                break;
                            case R.id.btn_order_detail2:

                                break;
                        }
                    }
                },adapter_account);

                // 设置popWindow的显示和消失动画
                orderPopupWindow.setAnimationStyle(R.style.mypopwindow_anim_style);
                //在底部显示
                orderPopupWindow.showAtLocation(MyOrderActivity.this.findViewById(R.id.ll_order),Gravity.BOTTOM, 0, 0);
            }
        });

        adapter_order.setOnRecyclerViewItemChildClickListener(new BaseQuickAdapter.OnRecyclerViewItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int position) {
                //获取点击的项
                final OrderEntity order = (OrderEntity) baseQuickAdapter.getItem(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(MyOrderActivity.this);
                builder.setMessage("确认删除吗？删除后不可恢复呢");
                builder.setTitle("删除订单");
                builder.setPositiveButton("删除", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        //删除选中的订单
                        orders.remove(order);
                        adapter_order.notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.create().show();
            }
        });

    }

    private void initData() {
        if (orders != null)
        {
            orders.clear();
        }
        orders = new ArrayList<OrderEntity>();
        if(carts != null)
        {
            carts.clear();
        }
        carts = new ArrayList<CartEntity>();
        for (int i = 0; i < 20; i++) {
            OrderEntity order = new OrderEntity();
            order.id = i + 1;
            order.state = "已完成";
            order.orderId = "100001";
            order.num = 5;
            order.total = 50;
            orders.add(order);

            CartEntity cart = new CartEntity();
            cart.fruit_name = "香蕉";
            cart.number = 5;
            cart.fruit_price = 10;
            cart.fruit_discount = 8;
            carts.add(cart);
        }
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
        tv_title.setText("我的订单");
        srl_order = $(R.id.srl_order);
        srl_order.setColorSchemeResources(R.color.base_color_yellow,R.color.base_color_brown);
        srl_order.setOnRefreshListener(this);
        rv_order = $(R.id.rv_order);
        rv_order.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onRefresh() {
        handler.sendEmptyMessageDelayed(REFRESH_DELAY,1000);
    }
}
