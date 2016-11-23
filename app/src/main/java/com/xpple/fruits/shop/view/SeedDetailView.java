package com.xpple.fruits.shop.view;

import com.xpple.fruits.shop.model.bean.Seeds;

/**
 * Created by Administrator on 2016/11/11.
 */

public interface SeedDetailView {
    void showProgress();

    void hideProgress();

    void LoadSuccess(Seeds seed);

    void LoadFail(String s);
}
