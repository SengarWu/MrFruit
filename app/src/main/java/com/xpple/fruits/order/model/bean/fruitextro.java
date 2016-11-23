package com.xpple.fruits.order.model.bean;

/**
 * Created by Administrator on 2016/11/12.
 */

public class FruitExtro {
    public String Name;
    public int Num;
    public double OriginalPrice;
    public double RealPay;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getNum() {
        return Num;
    }

    public void setNum(int num) {
        Num = num;
    }

    public double getOriginalPrice() {
        return OriginalPrice;
    }

    public void setOriginalPrice(double originalPrice) {
        OriginalPrice = originalPrice;
    }

    public double getRealPay() {
        return RealPay;
    }

    public void setRealPay(double realPay) {
        RealPay = realPay;
    }
}
