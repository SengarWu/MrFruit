package com.xpple.fruits.shop.model.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/11/11.
 */

public class SeedResult {
    private int dataCount;
    private List<Seeds> seeds;

    public int getDataCount() {
        return dataCount;
    }

    public void setDataCount(int dataCount) {
        this.dataCount = dataCount;
    }

    public List<Seeds> getSeeds() {
        return seeds;
    }

    public void setSeeds(List<Seeds> seeds) {
        this.seeds = seeds;
    }
}
