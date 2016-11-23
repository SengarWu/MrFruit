package com.xpple.fruits.shop.presenter;

/**
 * Created by Administrator on 2016/11/7.
 */

public interface FruitPresenter {
    void loadData(int userId, int areaId, int index, int size);
    void onDestroy();
}
