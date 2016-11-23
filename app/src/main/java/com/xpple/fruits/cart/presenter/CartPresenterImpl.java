package com.xpple.fruits.cart.presenter;

import com.xpple.fruits.cart.View.CartView;
import com.xpple.fruits.cart.model.CartModel;
import com.xpple.fruits.cart.model.CartModelImpl;
import com.xpple.fruits.cart.model.OnClearCartsFinishedListener;
import com.xpple.fruits.cart.model.OnDeleteCartFinishedListener;
import com.xpple.fruits.cart.model.OnGetCartListFinishedListener;
import com.xpple.fruits.cart.model.OnModifyCartFinishedListener;
import com.xpple.fruits.cart.model.bean.Cart;
import com.xpple.fruits.cart.model.bean.CartResult;

import java.util.List;

/**
 * Created by Administrator on 2016/11/16.
 */

public class CartPresenterImpl implements CartPresenter, OnGetCartListFinishedListener, OnModifyCartFinishedListener, OnClearCartsFinishedListener, OnDeleteCartFinishedListener {

    private CartModel cartModel;
    private CartView cartView;

    public CartPresenterImpl(CartView cartView)
    {
        this.cartView = cartView;
        cartModel = new CartModelImpl();
    }

    @Override
    public void GetList(int userId, int index, int size) {
        if (cartView != null)
        {
            cartView.showProgress();
        }
        cartModel.getList(userId,index,size,this);
    }

    @Override
    public void modifyCart(List<Cart> carts) {
        if (cartView != null)
        {
            cartView.showProgress();
        }
        cartModel.modifyCart(carts,this);
    }

    @Override
    public void clearCarts(int userId) {
        if (cartView != null)
        {
            cartView.showProgress();
        }
        cartModel.clearCarts(userId,this);
    }

    @Override
    public void deleteCart(List<Integer> cartsId) {
        if (cartView != null)
        {
            cartView.showProgress();
        }
        cartModel.deleteCart(cartsId,this);
    }

    @Override
    public void onDestroy() {
        cartView = null;
    }

    @Override
    public void OnGetListSuccess(CartResult result) {
        if (cartView != null)
        {
            cartView.hideProgress();
            cartView.GetListSuccess(result);
        }
    }

    @Override
    public void OnGetListFail(String message) {
        if (cartView != null)
        {
            cartView.hideProgress();
            cartView.GetListFail(message);
        }
    }

    @Override
    public void onModifyCartSuccess(String message) {
        if (cartView != null)
        {
            cartView.hideProgress();
            cartView.UpdateCartSuccess(message);
        }
    }

    @Override
    public void onModifyCartFail(String message) {
        if (cartView != null)
        {
            cartView.hideProgress();
            cartView.UpdateCartFail(message);
        }
    }

    @Override
    public void OnClearCartsSuccess(String message) {
        if (cartView != null)
        {
            cartView.hideProgress();
            cartView.ClearCartSuccess(message);
        }
    }

    @Override
    public void OnClearCartsFail(String message) {
        if (cartView != null)
        {
            cartView.hideProgress();
            cartView.ClearCartFail(message);
        }
    }

    @Override
    public void OnDeleteCartSuccess(String message) {
        if (cartView != null)
        {
            cartView.hideProgress();
            cartView.DeleteCartSuccess(message);
        }
    }

    @Override
    public void OnDeleteCartFail(String message) {
        if (cartView != null)
        {
            cartView.hideProgress();
            cartView.DeleteCartFail(message);
        }
    }
}
