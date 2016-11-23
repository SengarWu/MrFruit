package com.xpple.fruits.main.presenter;

/**
 * Created by Administrator on 2016/11/3.
 */

public interface MainPresenter {
    void loadData(int areaId,int userId);
    void addCart(int fruitId,int userId,int amount);
    void onDestroy();
}
