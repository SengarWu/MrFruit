package com.xpple.fruits.main.model.bean;

/**
 * Created by Administrator on 2016/11/3.
 */

public class Fruit {
    public int Id;
    /**
     * 名字
     */
    public String Name;
    /**
     * 图片URL
     */
    public String Picture;
    /**
     * 价格
     */
    public double Price;
    /**
     * 折扣价
     */
    public double Discount;
    /**
     * 单位
     */
    public String Unit;
    /**
     * 显示的水果数量
     */
    public int num;
    /**
     *按钮是否可见
     */
    public boolean visible;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPicture() {
        return Picture;
    }

    public void setPicture(String picture) {
        Picture = picture;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public double getDiscount() {
        return Discount;
    }

    public void setDiscount(double discount) {
        Discount = discount;
    }

    public String getUnit() {
        return Unit;
    }

    public void setUnit(String unit) {
        Unit = unit;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
