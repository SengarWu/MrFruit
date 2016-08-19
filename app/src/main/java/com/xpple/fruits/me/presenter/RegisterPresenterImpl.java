package com.xpple.fruits.me.presenter;

import com.xpple.fruits.me.model.OnRegisterFinishedListener;
import com.xpple.fruits.me.model.RegisterModel;
import com.xpple.fruits.me.model.RegisterModelImpl;
import com.xpple.fruits.me.view.RegisterView;

/**
 * Created by Administrator on 2016/8/19.
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
    public void onError() {
        if (registerView != null)
        {
            registerView.registerFail();
        }
    }

    @Override
    public void onSuccess() {
        if (registerView != null)
        {
            registerView.registerSuccess();
        }
    }


    @Override
    public String onRegister(String username, String phone, String password, String code) {
        if (registerView != null)
        {
            registerView.showProgress();
        }
        return registerModel.register(username,phone,password,code,this);
    }

    @Override
    public void onDestroy() {
        registerView = null;
    }
}
