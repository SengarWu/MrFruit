package com.xpple.fruits.order.ui;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.xpple.fruits.R;
import com.xpple.fruits.base.CustomApplication;
import com.xpple.fruits.order.adapter.OrderFruitAdapter;
import com.xpple.fruits.order.model.bean.Order;
import com.xpple.fruits.utils.DensityUtil;

import java.text.DecimalFormat;

/**
 * Created by Administrator on 2016/8/15.
 */
public class OrderFruitsPopupWindow extends PopupWindow {
    private View parentView;
    private TextView tv_order_detail_fruit_id;
    private TextView tv_order_detail_fruit_state;
    private TextView tv_order_detail_fruit_payment_method;
    private TextView tv_order_detail_fruit_time;
    private TextView tv_order_detail_fruit_total;
    private TextView tv_order_detail_fruit_receiver;
    private TextView tv_order_detail_fruit_phone;
    private TextView tv_order_detail_fruit_area;
    private TextView tv_order_detail_fruit_address;
    private ImageButton ib_order_detail_fruit_close;
    private Button btn_order_detail_fruit1;
    private Button btn_order_detail_fruit2;
    private RecyclerView rv_order_detail_fruit;

    private OrderFruitAdapter orderFruitAdapter;

    private DecimalFormat df   = new DecimalFormat("######0.0");

    public OrderFruitsPopupWindow(Activity context, View.OnClickListener itemsOnClick, Order order)
    {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        parentView = inflater.inflate(R.layout.popup_window_fruits,null);
        tv_order_detail_fruit_id = (TextView) parentView.findViewById(R.id.tv_order_detail_fruit_id);
        tv_order_detail_fruit_state = (TextView) parentView.findViewById(R.id.tv_order_detail_fruit_state);
        tv_order_detail_fruit_payment_method = (TextView) parentView.findViewById(R.id.tv_order_detail_fruit_payment_method);
        tv_order_detail_fruit_time = (TextView) parentView.findViewById(R.id.tv_order_detail_fruit_time);
        tv_order_detail_fruit_total = (TextView) parentView.findViewById(R.id.tv_order_detail_fruit_total);
        tv_order_detail_fruit_receiver = (TextView) parentView.findViewById(R.id.tv_order_detail_fruit_receiver);
        tv_order_detail_fruit_phone = (TextView) parentView.findViewById(R.id.tv_order_detail_fruit_phone);
        tv_order_detail_fruit_area = (TextView) parentView.findViewById(R.id.tv_order_detail_fruit_area);
        tv_order_detail_fruit_address = (TextView) parentView.findViewById(R.id.tv_order_detail_fruit_address);
        ib_order_detail_fruit_close = (ImageButton) parentView.findViewById(R.id.ib_order_detail_fruit_close);
        ib_order_detail_fruit_close.setOnClickListener(itemsOnClick);
        btn_order_detail_fruit1 = (Button) parentView.findViewById(R.id.btn_order_detail_fruit1);
        btn_order_detail_fruit1.setOnClickListener(itemsOnClick);
        btn_order_detail_fruit2 = (Button) parentView.findViewById(R.id.btn_order_detail_fruit2);
        btn_order_detail_fruit2.setOnClickListener(itemsOnClick);
        rv_order_detail_fruit = (RecyclerView) parentView.findViewById(R.id.rv_order_detail_fruit);
        rv_order_detail_fruit.setLayoutManager(new LinearLayoutManager(CustomApplication.getmInstance()));
        //显示数据
        tv_order_detail_fruit_id.setText(order.getId());
        tv_order_detail_fruit_state.setText(order.getState());
        tv_order_detail_fruit_payment_method.setText(order.getPaymentMethod());
        tv_order_detail_fruit_time.setText(order.getTime());
        tv_order_detail_fruit_total.setText(df.format(order.getSum()));
        tv_order_detail_fruit_receiver.setText(order.getorderExtro().getReceiver());
        tv_order_detail_fruit_phone.setText(order.getorderExtro().getPhone());
        tv_order_detail_fruit_area.setText(order.getorderExtro().getArea());
        tv_order_detail_fruit_address.setText(order.getorderExtro().getAddress());
        //客户端订单状态 等待付款，支付失败，等待配送，配送中，等待退款，已退款，已取消，待评价，完成
        switch (order.getState())
        {
            case "等待付款":
                if (!order.getPaymentMethod().equals("货到付款"))
                {
                    btn_order_detail_fruit2.setText("确认支付");
                }
                btn_order_detail_fruit1.setText("取消订单");
                break;
            case "支付失败":
                btn_order_detail_fruit1.setText("取消订单");
                btn_order_detail_fruit2.setText("继续支付");
                break;
            case "等待配送":
                btn_order_detail_fruit1.setText("取消订单");
                btn_order_detail_fruit2.setVisibility(View.GONE);
                break;
            case "配送中":
            case "等待退款":
            case "已退款":
            case "已取消":
            case "完成":
                btn_order_detail_fruit1.setVisibility(View.GONE);
                btn_order_detail_fruit2.setVisibility(View.GONE);
                break;
            case "待评价":
                btn_order_detail_fruit1.setVisibility(View.GONE);
                btn_order_detail_fruit2.setText("评价");
                break;
            default:
                break;
        }

        orderFruitAdapter = new OrderFruitAdapter(order.getFruits());
        rv_order_detail_fruit.setAdapter(orderFruitAdapter);

        //设置AreaPopupWindow的View
        this.setContentView(parentView);
        //设置AreaPopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置AreaPopupWindow弹出窗体的高
        this.setHeight(DensityUtil.dip2px(context,420));
        //设置AreaPopupWindow弹出窗体可点击
        this.setFocusable(true);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(Color.WHITE);
        //设置AreaPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        //parentView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        parentView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int height = parentView.findViewById(R.id.pop_layout_order).getTop();
                int y=(int) motionEvent.getY();
                if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                    if(y<height){
                        dismiss();
                    }
                }
                return true;
            }
        });
    }

    public void updateButton1Text(String message)
    {
        btn_order_detail_fruit1.setText(message);
    }

    public void updateButton1Visable(int visibility)
    {
        btn_order_detail_fruit1.setVisibility(visibility);
    }

    public void updateButton2Text(String message)
    {
        btn_order_detail_fruit2.setText(message);
    }

    public void updateButton2Visable(int visibility)
    {
        btn_order_detail_fruit2.setVisibility(visibility);
    }
}
