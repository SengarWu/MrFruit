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
import com.xpple.fruits.order.adapter.OrderSeedAdapter;
import com.xpple.fruits.order.model.bean.Order;
import com.xpple.fruits.utils.DensityUtil;

import java.text.DecimalFormat;

/**
 * Created by Administrator on 2016/11/13.
 */

public class OrderSeedsPopupWindow extends PopupWindow {

    private View parentView;
    private TextView tv_order_detail_seed_id;
    private TextView tv_order_detail_seed_state;
    private TextView tv_order_detail_seed_payment_method;
    private TextView tv_order_detail_seed_time;
    private TextView tv_order_detail_seed_total;
    private ImageButton ib_order_detail_seed_close;
    private Button btn_order_detail_seed1;
    private Button btn_order_detail_seed2;
    private RecyclerView rv_order_detail_seed;

    private OrderSeedAdapter orderSeedAdapter;
    private DecimalFormat df   = new DecimalFormat("######0.0");

    public OrderSeedsPopupWindow(Activity context, View.OnClickListener itemsOnClick, Order order)
    {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        parentView = inflater.inflate(R.layout.popup_window_seeds,null);
        tv_order_detail_seed_id = (TextView) parentView.findViewById(R.id.tv_order_detail_seed_id);
        tv_order_detail_seed_state = (TextView) parentView.findViewById(R.id.tv_order_detail_seed_state);
        tv_order_detail_seed_payment_method = (TextView) parentView.findViewById(R.id.tv_order_detail_seed_payment_method);
        tv_order_detail_seed_time = (TextView) parentView.findViewById(R.id.tv_order_detail_seed_time);
        tv_order_detail_seed_total = (TextView) parentView.findViewById(R.id.tv_order_detail_seed_total);
        ib_order_detail_seed_close = (ImageButton) parentView.findViewById(R.id.ib_order_detail_seed_close);
        ib_order_detail_seed_close.setOnClickListener(itemsOnClick);
        btn_order_detail_seed1 = (Button) parentView.findViewById(R.id.btn_order_detail_seed1);
        btn_order_detail_seed1.setOnClickListener(itemsOnClick);
        btn_order_detail_seed2 = (Button) parentView.findViewById(R.id.btn_order_detail_seed2);
        btn_order_detail_seed2.setOnClickListener(itemsOnClick);
        rv_order_detail_seed = (RecyclerView) parentView.findViewById(R.id.rv_order_detail_seed);
        rv_order_detail_seed.setLayoutManager(new LinearLayoutManager(CustomApplication.getmInstance()));

        tv_order_detail_seed_id.setText(order.getId());
        tv_order_detail_seed_state.setText(order.getState());
        tv_order_detail_seed_payment_method.setText(order.getPaymentMethod());
        tv_order_detail_seed_time.setText(order.getTime());
        tv_order_detail_seed_total.setText(df.format(order.getSum()));

        orderSeedAdapter = new OrderSeedAdapter(order.getSeeds());
        rv_order_detail_seed.setAdapter(orderSeedAdapter);

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
}
