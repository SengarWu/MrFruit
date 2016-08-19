package com.xpple.fruits.me.model;

import android.os.Handler;

/**
 * Created by Administrator on 2016/8/19.
 */
public class RegisterModelImpl implements RegisterModel {

    private String message = "网络访问出错！";

    @Override
    public String register(final String username, final String phone, final String password, final String code, final OnRegisterFinishedListener listener) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //模拟网络访问
                boolean net = true;
                if (net)
                {
                    message = "网络消息：登陆成功！";
                    listener.onSuccess();
                }
                else
                {
                    message = "网络消息：登陆失败！";
                    listener.onError();
                }
            }
        },2000);
        return message;
    }
}
