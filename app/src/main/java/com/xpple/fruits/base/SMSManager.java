package com.xpple.fruits.base;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.jude.utils.JUtils;

import cn.smssdk.EventHandler;
import cn.smssdk.OnSendMessageHandler;
import cn.smssdk.SMSSDK;

/**
 * Created by Administrator on 2016/10/18.
 */

public class SMSManager {
    public static final String APPKEY = "1077112ae0d07";
    public static final String APPSECRET ="c6574ad97b90a11038716e9360675757";
    private static final String TAG = "SMSManager";

    private CallbackEventHandler mHandler = new CallbackEventHandler();

    public SMSManager(Context mContext)
    {
        //初始化短信验证
        SMSSDK.initSDK(mContext, APPKEY, APPSECRET);
        //注册短信回调
        SMSSDK.registerEventHandler(mHandler);
    }

    //发送验证码
    public void getSMS(String phone,final Callback callback) {
        SMSSDK.getVerificationCode("86", phone, new OnSendMessageHandler() {
            @Override
            public boolean onSendMessage(String s, String s1) {
                return false;
            }
        });
        mHandler.setCallback(callback);
    }

    private class CallbackEventHandler extends EventHandler {
        Callback mCallback;
        Handler handler = new Handler();
        public void setCallback(Callback mCallback) {
            this.mCallback = mCallback;
        }

        @Override
        public void afterEvent(int event, int result, final Object data) {
            super.afterEvent(event, result, data);
            Log.e(TAG, "afterEvent: event:"+event+",result:"+result+",data:"+data );
            if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE && result == SMSSDK.RESULT_COMPLETE)
            {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        mCallback.success();
                        mCallback = null;
                    }
                });
            }
            else{
                JUtils.Log("Error:" + data.toString());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        mCallback.error((Throwable) data);
                        mCallback = null;
                    }
                });
            }
        }
    }

    public void uninit()
    {
        SMSSDK.unregisterAllEventHandler();
    }

}
