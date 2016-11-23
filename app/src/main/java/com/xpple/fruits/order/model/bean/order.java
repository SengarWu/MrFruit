package com.xpple.fruits.order.model.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/11/12.
 */

public class Order {
    public int Id;
    public int UserId;
    public String State;
    public String PaymentMethod;
    public int Type;
    public String Time;
    public double sum;
    public List<FruitExtro> fruits;
    public List<SeedExtro> seeds;
    public List<GetExtro> gets;
    public OrderExtro orderExtro;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getPaymentMethod() {
        return PaymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        PaymentMethod = paymentMethod;
    }

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public List<FruitExtro> getFruits() {
        return fruits;
    }

    public void setFruits(List<FruitExtro> fruits) {
        this.fruits = fruits;
    }

    public List<SeedExtro> getSeeds() {
        return seeds;
    }

    public void setSeeds(List<SeedExtro> seeds) {
        this.seeds = seeds;
    }

    public List<GetExtro> getGets() {
        return gets;
    }

    public void setGets(List<GetExtro> gets) {
        this.gets = gets;
    }

    public OrderExtro getorderExtro() {
        return orderExtro;
    }

    public void setorderExtro(OrderExtro extro) {
        this.orderExtro = orderExtro;
    }
}
