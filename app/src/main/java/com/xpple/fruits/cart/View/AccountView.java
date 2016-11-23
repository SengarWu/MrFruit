package com.xpple.fruits.cart.View;

import com.xpple.fruits.cart.model.bean.CartResult;

/**
 * Created by Administrator on 2016/11/18.
 */

public interface AccountView {
    void showProgress();

    void hideProgress();

    void GetCartListSuccess(CartResult result);

    void GetCartListFail(String message);

    void SubmitOrderSuccess(int orderId);

    void SubmitOrderFail(String message);
}
