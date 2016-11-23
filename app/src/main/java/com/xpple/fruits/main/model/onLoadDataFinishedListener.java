package com.xpple.fruits.main.model;

import com.xpple.fruits.main.model.bean.Main;

/**
 * Created by Administrator on 2016/11/6.
 */

public interface OnLoadDataFinishedListener {
    void onLoadDataError(String message);

    void onLoadDataSuccess(Main main);
}
