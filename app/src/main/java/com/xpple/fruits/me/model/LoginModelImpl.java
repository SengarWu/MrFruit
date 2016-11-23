package com.xpple.fruits.me.model;

import com.xpple.fruits.base.CustomApplication;
import com.xpple.fruits.me.model.bean.User;
import com.xpple.fruits.me.model.server.UserService;
import com.xpple.fruits.utils.SharedPreferencesHelper;

import rx.Subscriber;

/**
 * Created by Administrator on 2016/8/18.
 */
public class LoginModelImpl implements LoginModel {

    @Override
    public void login(final String username, final String password, final OnLoginFinishedListener listener) {
        UserService.getInstance().login(new Subscriber<User>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                listener.onError(e.getMessage());
            }

            @Override
            public void onNext(User user) {
                //保存信息到本地
                SharedPreferencesHelper sharedPreferencesHelper = new SharedPreferencesHelper(CustomApplication.getmInstance());
                sharedPreferencesHelper.setUserId(user.getId());
                sharedPreferencesHelper.setUserName(user.getUsername());
                sharedPreferencesHelper.setUserPhone(user.getUserphone());
                sharedPreferencesHelper.setUserNickname(user.getNickname());
                sharedPreferencesHelper.setUserLoginkey(user.getLoginkey());
                sharedPreferencesHelper.setUserToken(user.getToken());
                listener.onSuccess();
            }
        },username,password);
    }
}
