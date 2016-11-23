package com.xpple.fruits.shop.model;

import com.xpple.fruits.shop.model.bean.FruitResult;

/**
 * Created by Administrator on 2016/11/7.
 */

public interface OnLoadFruitFinishedListener {
    void onLoadDataError(String message);
    void onLoadDataSuccess(FruitResult result);
}
