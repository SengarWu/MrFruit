package com.xpple.fruits.utils;

import android.content.Context;

/**
 * Created by Administrator on 2016/5/13.
 * dp 和 px转换工具类
 */
public class DensityUtil {

    /**
     * 根据手机的分辨率从dp的单位转成为 px（像素）
     * @param context
     * @param dpValue
     * @return
     */
    public  static int dip2px(Context context,float dpValue){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int)(dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从px（像素）的单位 转成为 dp
     * @param context
     * @param pxValue
     * @return
     */
    public static int px2dip(Context context,float pxValue){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int)(pxValue / scale + 0.5f);
    }
}
