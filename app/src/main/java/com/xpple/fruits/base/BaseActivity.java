package com.xpple.fruits.base;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Toast;

import com.xpple.fruits.utils.CommonUtils;

/**
 * Created by Administrator on 2016/6/20.
 */
public class BaseActivity extends FragmentActivity {
    protected Context mContext;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        //禁止横屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    public boolean isNetConnected() {
        return CommonUtils.isNetworkAvailable(mContext);
    }

    public void showToastShort(String message)
    {
        Toast.makeText(mContext,message,Toast.LENGTH_SHORT).show();
    }

    public void showToastLong(String message)
    {
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
    }

    public void startAnimActivity(Class<?> cla) {
        this.startActivity(new Intent(this, cla));
    }

    public void startAnimActivity(Intent intent) {
        this.startActivity(intent);
    }

    protected   <T extends View> T $(int resId) {
        return (T) super.findViewById(resId);
    }

}