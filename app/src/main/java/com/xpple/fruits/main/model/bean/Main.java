package com.xpple.fruits.main.model.bean;

import com.xpple.fruits.shop.model.bean.Seeds;

import java.util.List;

/**
 * Created by Administrator on 2016/11/3.
 */

public class Main {
    private int cart;
    private List<Area> areas;
    private List<Advertisements> advertisementses;
    private Fruit fruit;
    private Seeds seed;

    public int isCart() {
        return cart;
    }

    public void setCart(int cart) {
        this.cart = cart;
    }

    public List<Area> getAreas() {
        return areas;
    }

    public void setAreas(List<Area> areas) {
        this.areas = areas;
    }

    public List<Advertisements> getAdvertisementses() {
        return advertisementses;
    }

    public void setAdvertisementses(List<Advertisements> advertisementses) {
        this.advertisementses = advertisementses;
    }

    public Fruit getFruit() {
        return fruit;
    }

    public void setFruit(Fruit fruit) {
        this.fruit = fruit;
    }

    public Seeds getSeed() {
        return seed;
    }

    public void setSeed(Seeds seed) {
        this.seed = seed;
    }
}
