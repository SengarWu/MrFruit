package com.xpple.fruits.order.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.jude.utils.JUtils;
import com.xpple.fruits.R;
import com.xpple.fruits.base.BaseActivity;
import com.xpple.fruits.order.adapter.OrderAdapter;
import com.xpple.fruits.order.model.bean.MyOrder;
import com.xpple.fruits.order.model.bean.Order;
import com.xpple.fruits.order.model.bean.OrderResult;
import com.xpple.fruits.order.presenter.OrderPresenter;
import com.xpple.fruits.order.presenter.OrderPresenterImpl;
import com.xpple.fruits.order.view.MyOrderView;
import com.xpple.fruits.utils.SharedPreferencesHelper;

import java.util.List;

import static com.xpple.fruits.R.id.btn_order_detail_fruit1;
import static com.xpple.fruits.R.id.btn_order_detail_fruit2;

public class MyOrderActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, MyOrderView, BaseQuickAdapter.RequestLoadMoreListener {

    private static final String TAG = "MyOrderActivity";

    private ImageButton ib_back;
    private TextView tv_title;
    private SwipeRefreshLayout srl_order;
    private RecyclerView rv_order;

    private List<OrderResult> orders;
    private Order itemOrder;
    private OrderAdapter adapter_order;

    private OrderFruitsPopupWindow orderFruitsPopupWindow;
    private OrderSeedsPopupWindow orderSeedsPopupWindow;
    private OrderGetsPopWindow orderGetsPopWindow;

    private ProgressBar progressBar;

    OrderPresenter presenter;

    private int userId = 0;
    private int index = 1;
    private final int SIZE = 20;
    private int dataCount = 0;

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
        presenter = new OrderPresenterImpl(this);
        initData();
    }

    private void setupView() {
        adapter_order = new OrderAdapter(orders);
        //设置动画效果
        adapter_order.openLoadAnimation();
        //加载更多
        adapter_order.openLoadMore(20);
        adapter_order.setOnLoadMoreListener(this);
        rv_order.setAdapter(adapter_order);
        rv_order.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                //点击列表项，查看订单详情
                OrderResult orderResult = (OrderResult) baseQuickAdapter.getData().get(i);
                presenter.orderDetail(orderResult.getId());
            }
        });

        //删除按钮
        rv_order.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void SimpleOnItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int position) {
                //获取点击的项
                final OrderResult order = (OrderResult) baseQuickAdapter.getData().get(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(MyOrderActivity.this);
                builder.setMessage("确认删除吗？删除后不可恢复呢");
                builder.setTitle("删除订单");
                builder.setPositiveButton("删除", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        //删除选中的订单
                        presenter.deleteOrder(order.getId());
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

    private void showPopWindow(final Order order)
    {
        //订单详情列表加载
        if (order.fruits != null)
        {
            //水果订单详情
            orderFruitsPopupWindow = new OrderFruitsPopupWindow(MyOrderActivity.this, new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    switch (view.getId())
                    {
                        case R.id.ib_order_detail_fruit_close:
                            //关闭订单详情弹窗
                            orderFruitsPopupWindow.dismiss();
                            break;
                        case btn_order_detail_fruit1:
                            //左边按钮
                            switch (order.getState())
                            {
                                case "等待付款":
                                case "支付失败":
                                case "等待配送":
                                    //取消订单
                                    presenter.cancelOrder(order.getId());
                                    break;
                                default:
                                    break;
                            }
                            break;
                        case btn_order_detail_fruit2:
                            //右边按钮
                            switch (order.getState())
                            {
                                case "等待付款":
                                    //确认付款
                                    if (order.getPaymentMethod() == "Get币支付")
                                    {
                                        presenter.getpayOrder(order.getId());
                                    }
                                    break;
                                case "支付失败":
                                    //继续支付
                                    if (order.getPaymentMethod().equals("Get币支付"))
                                    {
                                        presenter.getpayOrder(order.getId());
                                    }
                                    break;
                                case "待评价":
                                    //评价

                                    break;
                            }
                            break;
                    }
                }
            },order);
            // 设置popWindow的显示和消失动画
            orderFruitsPopupWindow.setAnimationStyle(R.style.mypopwindow_anim_style);
            //在底部显示
            orderFruitsPopupWindow.showAtLocation(MyOrderActivity.this.findViewById(R.id.ll_order), Gravity.BOTTOM, 0, 0);
        }
        else if (order.seeds != null)
        {
            //果种订单详情
            orderSeedsPopupWindow = new OrderSeedsPopupWindow(MyOrderActivity.this, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (v.getId())
                    {
                        case R.id.ib_order_detail_seed_close:
                            orderSeedsPopupWindow.dismiss();
                            break;
                        case R.id.btn_order_detail_seed1:

                            break;
                        case R.id.btn_order_detail_seed2:

                            break;
                    }
                }
            },order);
            //设置popWindow的显示和消失动画
            orderSeedsPopupWindow.setAnimationStyle(R.style.mypopwindow_anim_style);
            //在底部显示
            orderSeedsPopupWindow.showAtLocation(MyOrderActivity.this.findViewById(R.id.ll_order), Gravity.BOTTOM, 0, 0);
        }
        else if (order.gets != null)
        {
            orderGetsPopWindow = new OrderGetsPopWindow(MyOrderActivity.this, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (v.getId())
                    {
                        case R.id.ib_order_detail_get_close:
                            orderGetsPopWindow.dismiss();
                            break;
                        case R.id.btn_order_detail_get1:

                            break;
                        case R.id.btn_order_detail_get2:

                            break;
                    }
                }
            },order);
            //设置popWindow的显示和消失动画
            orderGetsPopWindow.setAnimationStyle(R.style.mypopwindow_anim_style);
            //在底部显示
            orderGetsPopWindow.showAtLocation(MyOrderActivity.this.findViewById(R.id.ll_order),Gravity.BOTTOM,0,0);
        }
        else
        {
            JUtils.Toast("数据加载失败");
            return;
        }
    }

    private void initData() {
        //获取本地数据
        //1.获取用户Id
        SharedPreferencesHelper helper = new SharedPreferencesHelper(this);
        userId = helper.getUserId();
        if (userId == 0)
        {
            JUtils.Toast("数据加载失败，请先登录！");
            return;
        }
        Intent intent = getIntent();
        if (intent != null)
        {
            //订单提交跳转
            int orderId = intent.getIntExtra("orderId",0);
            if (orderId != 0)
            {
                //显示订单详情
                presenter.orderDetail(orderId);
            }
        }
        else
        {
            //查看订单列表
            presenter.getList(userId,index,SIZE);
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
        progressBar = new ProgressBar(this);
    }

    @Override
    public void onRefresh() {
        handler.sendEmptyMessageDelayed(REFRESH_DELAY,500);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void getListFail(String message) {
        JUtils.Toast("加载失败！"+message);
    }

    @Override
    public void getListSuccess(MyOrder myOrder) {
        if (index == 0 || index == 1)
        {
            //首次请求
            orders = myOrder.getOrder();
            dataCount = myOrder.getDataCount();
            setupView();
        }
        else
        {
            adapter_order.addData(myOrder.getOrder());
        }
    }

    @Override
    public void getDetailFail(String message) {
        JUtils.Toast("数据加载失败"+message);
    }

    @Override
    public void getDetailSuccess(Order order) {
        itemOrder = order;
        showPopWindow(order);
    }

    @Override
    public void deleteOrderSuccess(String message) {
        JUtils.Toast("订单删除成功！");
        adapter_order.notifyDataSetChanged();
    }

    @Override
    public void deleteOrderFail(String message) {
        JUtils.Toast("订单删除失败！"+message);
    }

    @Override
    public void cancelOrderSuccess(String message) {
        JUtils.Toast(message);
    }

    @Override
    public void cancelOrderFail(String message) {
        JUtils.Toast(message);
    }

    @Override
    public void evaluateOrderSuccess(String message) {

    }

    @Override
    public void evaluateOrderFail(String message) {

    }

    @Override
    public void getpayOrderSuccess(String message) {
        JUtils.Toast(message);
        if (orderFruitsPopupWindow != null)
        {
            orderFruitsPopupWindow.dismiss();
            return;
        }
        if (orderSeedsPopupWindow != null)
        {
            orderSeedsPopupWindow.dismiss();
            return;
        }
        if (orderGetsPopWindow != null)
        {
            orderGetsPopWindow.dismiss();
            return;
        }
    }

    @Override
    public void getpayOrderFail(String message) {
        JUtils.Toast(message);
    }

    @Override
    public void onLoadMoreRequested() {
        rv_order.post(new Runnable() {
            @Override
            public void run() {
                if (adapter_order.getData().size() >= dataCount)
                {
                    //加载完毕
                    adapter_order.loadComplete();
                }
                else
                {
                    index++;
                    presenter.getList(userId,index,SIZE);
                }
            }
        });
    }
}
