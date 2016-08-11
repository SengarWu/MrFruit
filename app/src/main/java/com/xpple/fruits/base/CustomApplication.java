package com.xpple.fruits.base;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by Administrator on 2016/6/20.
 */
public class CustomApplication extends Application {

    public static CustomApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        mInstance = this;
    }

    public static CustomApplication getmInstance() {
        return mInstance;
    }
}
