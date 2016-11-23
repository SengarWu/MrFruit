package com.xpple.fruits.shop.presenter;

import com.xpple.fruits.shop.model.FruitModel;
import com.xpple.fruits.shop.model.FruitModelImpl;
import com.xpple.fruits.shop.model.OnLoadFruitFinishedListener;
import com.xpple.fruits.shop.model.bean.FruitResult;
import com.xpple.fruits.shop.view.FruitView;

/**
 * Created by Administrator on 2016/11/7.
 */

public class FruitPresenterImpl implements FruitPresenter,OnLoadFruitFinishedListener {
    private FruitModel fruitModel;
    private FruitView fruitView;

    public FruitPresenterImpl(FruitView fruitView)
    {
        this.fruitView = fruitView;
        this.fruitModel = new FruitModelImpl();
    }

    @Override
    public void loadData(int userId, int areaId, int index, int size) {
        if (index == 0 || index == 1)
        {
            if (fruitView != null)
            {
                fruitView.showProgress();
            }
        }
        fruitModel.loadData(userId,areaId,index,size,this);
    }

    @Override
    public void onDestroy() {
        fruitView = null;
    }

    @Override
    public void onLoadDataError(String message) {
        if (fruitView != null)
        {
            fruitView.hideProgress();
            fruitView.LoadFail(message);
        }
    }

    @Override
    public void onLoadDataSuccess(FruitResult result) {
        if (fruitView != null)
        {
            fruitView.hideProgress();
            fruitView.LoadSuccess(result);
        }
    }
}
