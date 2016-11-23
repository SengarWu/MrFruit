package com.xpple.fruits.utils;

/**
 * Created by Administrator on 2016/4/19.
 */

/**
 * 为了防止用户疯狂的点击某个button，写个方法防止按钮连续点击
 */
public class Utils {
    private static long lastClickTime;
    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();  //毫秒
        if ( time - lastClickTime < 500) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

    public static boolean isFastDoubleLongClick() {
        long time = System.currentTimeMillis();  //毫秒
        if ( time - lastClickTime < 1000) {
            return true;
        }
        lastClickTime = time;
        return false;
    }
}

