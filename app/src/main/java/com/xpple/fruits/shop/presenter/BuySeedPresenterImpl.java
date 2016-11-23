package com.xpple.fruits.shop.presenter;

import com.xpple.fruits.order.model.OnSubmitFinishedListener;
import com.xpple.fruits.order.model.OrderModel;
import com.xpple.fruits.order.model.OrderModelImpl;
import com.xpple.fruits.order.model.bean.Order;
import com.xpple.fruits.order.view.BuySeedView;

/**
 * Created by Administrator on 2016/11/18.
 */

public class BuySeedPresenterImpl implements BuySeedPresenter, OnSubmitFinishedListener {

    private OrderModel orderModel;
    private BuySeedView buySeedView;

    public BuySeedPresenterImpl(BuySeedView buySeedView)
    {
        this.buySeedView = buySeedView;
        orderModel = new OrderModelImpl();
    }

    @Override
    public void submitSeedOrder(Order order) {
        if (buySeedView != null)
        {
            buySeedView.showProgress();
        }
        orderModel.submit(order,this);
    }

    @Override
    public void onSubmitError(String message) {
        if (buySeedView != null)
        {
            buySeedView.hideProgress();
            buySeedView.submitFail(message);
        }
    }

    @Override
    public void onSubmitSuccess(int orderId) {
        if (buySeedView != null)
        {
            buySeedView.hideProgress();
            buySeedView.submitSuccess(orderId);
        }
    }
}
