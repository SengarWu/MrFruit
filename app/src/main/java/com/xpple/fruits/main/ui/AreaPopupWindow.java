package com.xpple.fruits.main.ui;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.xpple.fruits.R;
import com.xpple.fruits.main.adapter.AreaAdapter;
import com.xpple.fruits.main.model.bean.Area;
import com.xpple.fruits.utils.DensityUtil;

import java.util.List;

/**
 * Created by Administrator on 2016/8/8.
 */
public class AreaPopupWindow extends PopupWindow{
    private RecyclerView rv_main;
    private View parentView;
    AreaAdapter adapter_area;

    public  AreaPopupWindow(Activity context, List<Area> data, OnItemChildClickListener itemChildClickListener)
    {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        parentView = inflater.inflate(R.layout.activity_area_select_dialog,null);
        rv_main = (RecyclerView) parentView.findViewById(R.id.rv_main);
        //设置布局管理器 , 将布局设置成2列的GridView
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context,2);
        rv_main.setLayoutManager(gridLayoutManager);
        adapter_area = new AreaAdapter(data);
        //设置动画效果
        adapter_area.openLoadAnimation();
        rv_main.setAdapter(adapter_area);
        //子控件点击事件监听
        rv_main.addOnItemTouchListener(itemChildClickListener);

        //设置AreaPopupWindow的View
        this.setContentView(parentView);
        //设置AreaPopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置AreaPopupWindow弹出窗体的高
        this.setHeight(DensityUtil.dip2px(context,200));
        //设置AreaPopupWindow弹出窗体可点击
        this.setFocusable(true);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        //设置AreaPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        //parentView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        parentView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int height = parentView.findViewById(R.id.rv_main).getTop();
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
