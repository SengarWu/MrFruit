package com.xpple.fruits.main.view;

import com.xpple.fruits.main.model.bean.Main;

/**
 * Created by Administrator on 2016/11/3.
 */

public interface MainView {
    void showProgress();

    void hideProgress();

    void LoadSuccess(Main main);

    void LoadFail(String s);

    void addCartSuccess(String s);

    void addCartFail(String s);
}
