package com.xpple.fruits.base;

import android.app.Application;

/**
 * Created by Administrator on 2016/6/20.
 */
public class CustomApplication extends Application {

    public static CustomApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static CustomApplication getmInstance() {
        return mInstance;
    }
}
