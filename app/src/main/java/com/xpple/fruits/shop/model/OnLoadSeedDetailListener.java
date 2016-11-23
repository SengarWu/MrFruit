package com.xpple.fruits.shop.model;

import com.xpple.fruits.shop.model.bean.Seeds;

/**
 * Created by Administrator on 2016/11/11.
 */

public interface OnLoadSeedDetailListener {
    void onLoadDataError(String message);
    void onLoadDataSuccess(Seeds seed);
}
