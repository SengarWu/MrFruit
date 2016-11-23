package com.xpple.fruits.cart.presenter;

import com.xpple.fruits.cart.View.AccountView;
import com.xpple.fruits.cart.model.CartModel;
import com.xpple.fruits.cart.model.CartModelImpl;
import com.xpple.fruits.cart.model.OnGetCartListFinishedListener;
import com.xpple.fruits.cart.model.bean.CartResult;
import com.xpple.fruits.order.model.OnSubmitFinishedListener;
import com.xpple.fruits.order.model.OrderModel;
import com.xpple.fruits.order.model.OrderModelImpl;
import com.xpple.fruits.order.model.bean.Order;

/**
 * Created by Administrator on 2016/11/18.
 */

public class AccountPresenterImpl implements AccountPresenter, OnGetCartListFinishedListener, OnSubmitFinishedListener {

    private CartModel cartModel;
    private OrderModel orderModel;
    private AccountView accountView;

    public AccountPresenterImpl(AccountView accountView)
    {
        this.accountView = accountView;
        cartModel = new CartModelImpl();
        orderModel = new OrderModelImpl();
    }

    @Override
    public void GetCartList(int userId, int index, int size) {
        if (accountView != null)
        {
            accountView.showProgress();
        }
        cartModel.getList(userId,index,size,this);
    }

    @Override
    public void submitOrder(Order order) {
        if (accountView != null)
        {
            accountView.showProgress();
        }
        orderModel.submit(order,this);
    }

    @Override
    public void OnGetListSuccess(CartResult result) {
        if (accountView != null)
        {
            accountView.hideProgress();
            accountView.GetCartListSuccess(result);
        }
    }

    @Override
    public void OnGetListFail(String message) {
        if (accountView != null)
        {
            accountView.hideProgress();
            accountView.GetCartListFail(message);
        }
    }

    @Override
    public void onSubmitError(String message) {
        if (accountView != null)
        {
            accountView.hideProgress();
            accountView.SubmitOrderFail(message);
        }
    }

    @Override
    public void onSubmitSuccess(int orderId) {
        if (accountView != null)
        {
            accountView.hideProgress();
            accountView.SubmitOrderSuccess(orderId);
        }
    }
}
