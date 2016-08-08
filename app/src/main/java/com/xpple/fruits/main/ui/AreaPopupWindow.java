package com.xpple.fruits.main.ui;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;

import com.xpple.fruits.R;
import com.xpple.fruits.utils.DensityUtil;

/**
 * Created by Administrator on 2016/8/8.
 */
public class AreaPopupWindow extends PopupWindow{
    private Button btn_area1;
    private View parentView;

    public  AreaPopupWindow(Activity context, View.OnClickListener itemsOnClick)
    {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        parentView = inflater.inflate(R.layout.activity_area_select_dialog,null);
        btn_area1 = (Button) parentView.findViewById(R.id.btn_area1);
        btn_area1.setOnClickListener(itemsOnClick);
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
                int height = parentView.findViewById(R.id.pop_layout).getTop();
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
