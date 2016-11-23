package com.xpple.fruits.order.model.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/11/18.
 */

public class MyOrder {
    private List<OrderResult> order;
    private int dataCount;

    public List<OrderResult> getOrder() {
        return order;
    }

    public void setOrder(List<OrderResult> order) {
        this.order = order;
    }

    public int getDataCount() {
        return dataCount;
    }

    public void setDataCount(int dataCount) {
        this.dataCount = dataCount;
    }
}
