package com.xpple.fruits.bean;

/**
 * Created by Administrator on 2016/8/9.
 */
public class CartEntity  {
    public String fruit_Image;
    public String fruit_name;
    public Boolean ischeck;
    public double fruit_price;
    public String fruit_unit;
    public double sum;
    public int number;

    public void setFruit_Image(String fruit_Image) {
        this.fruit_Image = fruit_Image;
    }

    public void setFruit_name(String fruit_name) {
        this.fruit_name = fruit_name;
    }

    public void setIscheck(Boolean ischeck) {
        this.ischeck = ischeck;
    }

    public void setFruit_price(double fruit_price) {
        this.fruit_price = fruit_price;
    }

    public void setFruit_unit(String fruit_unit) {
        this.fruit_unit = fruit_unit;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getFruit_Image() {
        return fruit_Image;
    }

    public String getFruit_name() {
        return fruit_name;
    }

    public Boolean getIscheck() {
        return ischeck;
    }

    public double getFruit_price() {
        return fruit_price;
    }

    public String getFruit_unit() {
        return fruit_unit;
    }

    public double getSum() {
        return sum;
    }

    public int getNumber() {
        return number;
    }
}
