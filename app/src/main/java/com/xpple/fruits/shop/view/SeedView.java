package com.xpple.fruits.shop.view;

import com.xpple.fruits.shop.model.bean.SeedResult;

/**
 * Created by Administrator on 2016/11/11.
 */

public interface SeedView {
    void showProgress();

    void hideProgress();

    void LoadSuccess(SeedResult result);

    void LoadFail(String s);
}
