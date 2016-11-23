package com.xpple.fruits.shop.view;

import com.xpple.fruits.shop.model.bean.FruitResult;

/**
 * Created by Administrator on 2016/11/7.
 */

public interface FruitView {
    void showProgress();

    void hideProgress();

    void LoadSuccess(FruitResult result);

    void LoadFail(String s);
}
