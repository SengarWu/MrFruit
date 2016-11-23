package com.xpple.fruits.cart.model.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/11/16.
 */

public class CartResult {
    private List<Cart> carts;
    private int dataCount;

    public List<Cart> getCarts() {
        return carts;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }

    public int getDataCount() {
        return dataCount;
    }

    public void setDataCount(int dataCount) {
        this.dataCount = dataCount;
    }
}
