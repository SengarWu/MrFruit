package com.xpple.fruits.shop.model.bean;

import com.xpple.fruits.main.model.bean.Fruit;

import java.util.List;

/**
 * Created by Administrator on 2016/11/7.
 */

public class FruitResult {
    private List<Fruit> fruits;
    private int dataCount;
    private Double cost;

    public List<Fruit> getFruits() {
        return fruits;
    }

    public void setFruits(List<Fruit> fruits) {
        this.fruits = fruits;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public int getDataCount() {
        return dataCount;
    }

    public void setDataCount(int dataCount) {
        this.dataCount = dataCount;
    }
}
