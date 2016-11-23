package com.xpple.fruits.cart.View;

import com.xpple.fruits.cart.model.bean.CartResult;

/**
 * Created by Administrator on 2016/11/16.
 */

public interface CartView {
    void showProgress();

    void hideProgress();

    void GetListSuccess(CartResult result);

    void GetListFail(String message);

    void UpdateCartSuccess(String message);

    void UpdateCartFail(String message);

    void DeleteCartSuccess(String message);

    void DeleteCartFail(String message);

    void ClearCartSuccess(String message);

    void ClearCartFail(String message);
}
