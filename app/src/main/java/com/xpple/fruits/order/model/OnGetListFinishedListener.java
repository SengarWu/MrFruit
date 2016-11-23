package com.xpple.fruits.order.model;

import com.xpple.fruits.order.model.bean.MyOrder;

/**
 * Created by Administrator on 2016/11/13.
 */

public interface OnGetListFinishedListener {
    void onGetOrderListError(String message);
    void onGetOrderListSuccess(MyOrder myOrder);
}
