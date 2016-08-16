package com.xpple.fruits.bean;

/**
 * Created by Administrator on 2016/8/13.
 */
public class SeedEntity {
    public String id;
    public String seed_image;
    public String seed_name;
    public double seed_price;
    public String seed_state;
    public int seed_num;

    public String getId() {
        return id;
    }

    public String getSeed_image() {
        return seed_image;
    }

    public String getSeed_name() {
        return seed_name;
    }

    public double getSeed_price() {
        return seed_price;
    }

    public String getSeed_state() {
        return seed_state;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getSeed_num() {
        return seed_num;
    }

    public void setSeed_num(int seed_num) {
        this.seed_num = seed_num;
    }

    public void setSeed_image(String seed_image) {
        this.seed_image = seed_image;
    }

    public void setSeed_name(String seed_name) {
        this.seed_name = seed_name;
    }

    public void setSeed_price(double seed_price) {
        this.seed_price = seed_price;
    }

    public void setSeed_state(String seed_state) {
        this.seed_state = seed_state;
    }
}
