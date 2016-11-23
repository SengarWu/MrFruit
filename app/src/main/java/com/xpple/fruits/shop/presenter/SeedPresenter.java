package com.xpple.fruits.shop.presenter;

/**
 * Created by Administrator on 2016/11/11.
 */

public interface SeedPresenter {
    void loadData(int userId, int areaId, int index, int size);
    void getSeed(int seedId);
    void onDestroy();
}
