package com.xpple.fruits.me.presenter;

import com.xpple.fruits.me.model.LoginModel;
import com.xpple.fruits.me.model.LoginModelImpl;
import com.xpple.fruits.me.model.OnLoginFinishedListener;
import com.xpple.fruits.me.view.LoginView;

/**
 * Created by Administrator on 2016/8/18.
 */
public class LoginPresenterImpl implements LoginPresenter,OnLoginFinishedListener {

    private LoginView loginView;
    private LoginModel loginModel;

    public LoginPresenterImpl(LoginView loginView) {
        this.loginView = loginView;
        this.loginModel = new LoginModelImpl();
    }

    @Override
    public void validateCredentials(String username, String password) {
        if (loginView != null)
        {
            loginView.showProgress();
        }
        loginModel.login(username,password,this);
    }

    @Override
    public void onDestroy() {
        loginView = null;
    }

    @Override
    public void onError(String s) {
        if (loginView != null)
        {
            loginView.hideProgress();
            loginView.loginFail(s);
        }
    }

    @Override
    public void onSuccess() {
        if (loginView != null) {
            loginView.hideProgress();
            loginView.loginSuccess();
        }
    }
}
