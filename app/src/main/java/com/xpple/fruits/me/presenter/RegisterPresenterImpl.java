package com.xpple.fruits.me.presenter;

import com.xpple.fruits.me.model.OnRegisterFinishedListener;
import com.xpple.fruits.me.model.RegisterModel;
import com.xpple.fruits.me.model.RegisterModelImpl;
import com.xpple.fruits.me.view.RegisterView;

/**
 * Created by Administrator on 2016/11/1.
 */

public class RegisterPresenterImpl implements RegisterPresenter,OnRegisterFinishedListener {

    private RegisterView registerView;
    private RegisterModel registerModel;

    public RegisterPresenterImpl(RegisterView registerView)
    {
        this.registerView = registerView;
        this.registerModel = new RegisterModelImpl();
    }

    @Override
    public void register(String name, String phone, String pass,String code) {
        if (registerView != null)
        {
            registerView.showProgress();
        }
        registerModel.register(name,phone,pass,code,this);
    }

    @Override
    public void onDestroy() {
        registerView = null;
    }

    @Override
    public void onError(String message) {
        if (registerView != null)
        {
            registerView.hideProgress();
            registerView.registerFail(message);
        }
    }

    @Override
    public void onSuccess(String message) {
        if (registerView != null)
        {
            registerView.hideProgress();
            registerView.registerSuccess(message);
        }
    }
}
