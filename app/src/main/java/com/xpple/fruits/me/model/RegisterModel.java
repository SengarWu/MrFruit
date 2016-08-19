package com.xpple.fruits.me.model;

/**
 * Created by Administrator on 2016/8/19.
 */
public interface RegisterModel {
    /**
     *
     * @param username 用户名
     * @param phone 手机号
     * @param password 密码
     * @param code 邀请码
     * @return
     */
    String register(String username,String phone,String password,String code,OnRegisterFinishedListener onRegisterFinishedListener);

}
