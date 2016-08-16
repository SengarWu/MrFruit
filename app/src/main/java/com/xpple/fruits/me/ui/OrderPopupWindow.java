package com.xpple.fruits.me.ui;

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

import com.xpple.fruits.R;
import com.xpple.fruits.base.CustomApplication;
import com.xpple.fruits.cart.adapter.AccountAdapter;
import com.xpple.fruits.utils.DensityUtil;

/**
 * Created by Administrator on 2016/8/15.
 */
public class OrderPopupWindow extends PopupWindow {
    private View parentView;
    private ImageButton ib_order_detail_close;
    private Button btn_order_detail1;
    private Button btn_order_detail2;
    private RecyclerView rv_order_detail;


    public OrderPopupWindow(Activity context, View.OnClickListener itemsOnClick,AccountAdapter adapter)
    {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        parentView = inflater.inflate(R.layout.popup_window_fruits,null);
        ib_order_detail_close = (ImageButton) parentView.findViewById(R.id.ib_order_detail_close);
        ib_order_detail_close.setOnClickListener(itemsOnClick);
        btn_order_detail1 = (Button) parentView.findViewById(R.id.btn_order_detail1);
        btn_order_detail1.setOnClickListener(itemsOnClick);
        btn_order_detail2 = (Button) parentView.findViewById(R.id.btn_order_detail2);
        btn_order_detail2.setOnClickListener(itemsOnClick);
        rv_order_detail = (RecyclerView) parentView.findViewById(R.id.rv_order_detail);
        rv_order_detail.setLayoutManager(new LinearLayoutManager(CustomApplication.getmInstance()));
        rv_order_detail.setAdapter(adapter);

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
