package com.xpple.fruits.main.model;

/**
 * Created by Administrator on 2016/11/3.
 */

public interface MainModel {
    void loadData(int areaId,int userId,OnLoadDataFinishedListener listener);
    void addCart(int fruitId,int userId,int amount,OnAddCartFinishedListener listener);
}
