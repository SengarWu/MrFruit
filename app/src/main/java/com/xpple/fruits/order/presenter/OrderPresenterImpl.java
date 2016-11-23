package com.xpple.fruits.order.presenter;

import com.xpple.fruits.order.model.OnCancelFinishedListener;
import com.xpple.fruits.order.model.OnDeleteFinishedListener;
import com.xpple.fruits.order.model.OnEvaluateFinishedListener;
import com.xpple.fruits.order.model.OnGetListFinishedListener;
import com.xpple.fruits.order.model.OnGetOrderFinishedListener;
import com.xpple.fruits.order.model.OnGetPayFinishedListener;
import com.xpple.fruits.order.model.OnSubmitFinishedListener;
import com.xpple.fruits.order.model.OrderModel;
import com.xpple.fruits.order.model.bean.MyOrder;
import com.xpple.fruits.order.model.bean.Order;
import com.xpple.fruits.order.view.BuySeedView;
import com.xpple.fruits.order.view.MyOrderView;

/**
 * Created by Administrator on 2016/11/12.
 */

public class OrderPresenterImpl implements OrderPresenter,OnSubmitFinishedListener, OnGetListFinishedListener, OnGetOrderFinishedListener, OnGetPayFinishedListener, OnDeleteFinishedListener, OnCancelFinishedListener, OnEvaluateFinishedListener {

    private MyOrderView myOrderView;
    private BuySeedView buySeedView;
    private OrderModel orderModel;

    public OrderPresenterImpl(MyOrderView myOrderView)
    {
        this.myOrderView = myOrderView;
    }

    @Override
    public void submit(Order order) {
        if (buySeedView != null)
        {
            buySeedView.showProgress();
        }
        orderModel.submit(order,this);
    }

    @Override
    public void getList(int userId, int index, int size) {
        if (myOrderView != null)
        {
            myOrderView.showProgress();
        }
        orderModel.getOrderList(userId,index,size,this);
    }

    @Override
    public void orderDetail(int orderId) {
        if (myOrderView != null)
        {
            myOrderView.showProgress();
        }
        orderModel.getOrderDetail(orderId,this);
    }

    @Override
    public void deleteOrder(int orderId) {
        if (myOrderView != null)
        {
            myOrderView.showProgress();
        }
        orderModel.deleteOrder(orderId,this);
    }

    @Override
    public void cancelOrder(int orderId) {
        if (myOrderView != null)
        {
            myOrderView.showProgress();
        }
        orderModel.cancelOrder(orderId,this);
    }

    @Override
    public void evaluateOrder(int orderId,String content) {
        if (myOrderView != null)
        {
            myOrderView.showProgress();
        }
        orderModel.evaluateOrder(orderId,content,this);
    }

    @Override
    public void getpayOrder(int orderId) {
        if (myOrderView != null)
        {
            myOrderView.showProgress();
        }
        orderModel.getpayOrder(orderId,this);
    }

    @Override
    public void onDestory() {
        myOrderView = null;
        buySeedView = null;
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

    @Override
    public void onGetOrderListError(String message) {
        if (myOrderView != null)
        {
            myOrderView.hideProgress();
            myOrderView.getListFail(message);
        }
    }

    @Override
    public void onGetOrderListSuccess(MyOrder myOrder) {
        if (myOrderView != null)
        {
            myOrderView.hideProgress();
            myOrderView.getListSuccess(myOrder);
        }
    }

    @Override
    public void OnGetOrderError(String message) {
        if (myOrderView != null)
        {
            myOrderView.hideProgress();
            myOrderView.getDetailFail(message);
        }
    }

    @Override
    public void OnGetOrderSuccess(Order order) {
        if (myOrderView != null)
        {
            myOrderView.hideProgress();
            myOrderView.getDetailSuccess(order);
        }
    }

    @Override
    public void OnGetPayError(String message) {
        if (myOrderView != null)
        {
            myOrderView.hideProgress();
            myOrderView.getpayOrderFail(message);
        }
    }

    @Override
    public void OnGetPaySuccess(String message) {
        if (myOrderView != null)
        {
            myOrderView.hideProgress();
            myOrderView.getpayOrderSuccess(message);
        }
    }

    @Override
    public void OnDeleteSuccess(String message) {
        if (myOrderView != null)
        {
            myOrderView.hideProgress();
            myOrderView.deleteOrderSuccess(message);
        }
    }

    @Override
    public void OnDeleteFail(String message) {
        if (myOrderView != null)
        {
            myOrderView.hideProgress();
            myOrderView.deleteOrderFail(message);
        }
    }

    @Override
    public void OnCancelSuccess(String message) {
        if (myOrderView != null)
        {
            myOrderView.hideProgress();
            myOrderView.cancelOrderSuccess(message);
        }
    }

    @Override
    public void OnCancelFail(String message) {
        if (myOrderView != null)
        {
            myOrderView.hideProgress();
            myOrderView.cancelOrderFail(message);
        }
    }

    @Override
    public void OnEvaluateSuccess(String message) {
        if (myOrderView != null)
        {
            myOrderView.hideProgress();
            myOrderView.evaluateOrderSuccess(message);
        }
    }

    @Override
    public void OnEvaluateFail(String message) {
        if (myOrderView != null)
        {
            myOrderView.hideProgress();
            myOrderView.evaluateOrderFail(message);
        }
    }
}
