package com.xpple.fruits.bean;

/**
 * Created by Administrator on 2016/8/13.
 */
public class FruitEntity  {
    public String fruit_image;
    public String fruit_name;
    public double fruit_price;
    public double fruit_discount;
    public String fruit_unit;
    public int fruit_num;
    public boolean visible;

    public String getFruit_image() {
        return fruit_image;
    }

    public String getFruit_name() {
        return fruit_name;
    }

    public double getFruit_price() {
        return fruit_price;
    }

    public double getFruit_discount() {
        return fruit_discount;
    }

    public String getUint() {
        return fruit_unit;
    }

    public String getFruit_unit() {
        return fruit_unit;
    }

    public int getFruit_num() {
        return fruit_num;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setFruit_image(String fruit_image) {
        this.fruit_image = fruit_image;
    }

    public void setFruit_name(String fruit_name) {
        this.fruit_name = fruit_name;
    }

    public void setFruit_price(double fruit_price) {
        this.fruit_price = fruit_price;
    }

    public void setFruit_discount(double fruit_discount) {
        this.fruit_discount = fruit_discount;
    }

    public void setUint(String fruit_unit) {
        this.fruit_unit = fruit_unit;
    }

    public void setFruit_unit(String fruit_unit) {
        this.fruit_unit = fruit_unit;
    }

    public void setFruit_num(int fruit_num) {
        this.fruit_num = fruit_num;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
