package com.xpple.fruits.cart.model;

import com.xpple.fruits.cart.model.bean.CartResult;

/**
 * Created by Administrator on 2016/11/16.
 */

public interface OnGetCartListFinishedListener {
    void OnGetListSuccess(CartResult result);
    void OnGetListFail(String message);
}
