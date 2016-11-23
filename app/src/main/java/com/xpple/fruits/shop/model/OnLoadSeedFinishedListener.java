package com.xpple.fruits.shop.model;

import com.xpple.fruits.shop.model.bean.SeedResult;

/**
 * Created by Administrator on 2016/11/11.
 */

public interface OnLoadSeedFinishedListener {
    void onLoadDataError(String message);
    void onLoadDataSuccess(SeedResult result);
}
