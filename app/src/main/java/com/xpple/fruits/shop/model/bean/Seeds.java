package com.xpple.fruits.shop.model.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/11/11.
 */

public class Seeds implements Serializable {
    /**
     * 果种id
     */
    public int Id;
    /**
     * 果种名字
     */
    public String Name;
    /**
     * 果种图片
     */
    public String Picture;
    /**
     * 果种价格
     */
    public double Price;
    /**
     * 状态（0活动果种，1免费果种，2已解锁，3未解锁）
     */
    public int state;
    /**
     * 果种数量
     */
    public int seed_num;
    /**
     * 果种介绍
     */
    public String Introduce;
    /**
     * 需要等级
     */
    public int NeedGrade;
    /**
     * 总成长值
     */
    public int TotalGrowth;
    /**
     * 库存
     */
    public int Stock;
    /**
     * 兑换信息
     */
    public String ExchangeMessage;

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

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getSeed_num() {
        return seed_num;
    }

    public void setSeed_num(int seed_num) {
        this.seed_num = seed_num;
    }

    public String getIntroduce() {
        return Introduce;
    }

    public void setIntroduce(String introduce) {
        Introduce = introduce;
    }

    public int getNeedGrade() {
        return NeedGrade;
    }

    public void setNeedGrade(int needGrade) {
        NeedGrade = needGrade;
    }

    public int getTotalGrowth() {
        return TotalGrowth;
    }

    public void setTotalGrowth(int totalGrowth) {
        TotalGrowth = totalGrowth;
    }

    public int getStock() {
        return Stock;
    }

    public void setStock(int stock) {
        Stock = stock;
    }

    public String getExchangeMessage() {
        return ExchangeMessage;
    }

    public void setExchangeMessage(String exchangeMessage) {
        ExchangeMessage = exchangeMessage;
    }
}
