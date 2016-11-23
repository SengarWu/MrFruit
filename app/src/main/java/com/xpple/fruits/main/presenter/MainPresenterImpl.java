package com.xpple.fruits.main.presenter;

import com.xpple.fruits.main.model.MainModel;
import com.xpple.fruits.main.model.MainModelImpl;
import com.xpple.fruits.main.model.OnAddCartFinishedListener;
import com.xpple.fruits.main.model.OnLoadDataFinishedListener;
import com.xpple.fruits.main.model.bean.Main;
import com.xpple.fruits.main.view.MainView;

/**
 * Created by Administrator on 2016/11/3.
 */

public class MainPresenterImpl implements MainPresenter,OnLoadDataFinishedListener,OnAddCartFinishedListener {

    private MainView mainView;
    private MainModel mainModel;

    public MainPresenterImpl(MainView mainView)
    {
        this.mainView = mainView;
        this.mainModel = new MainModelImpl();
    }

    @Override
    public void loadData(int areaId, int userId) {
        if (mainView != null)
        {
            mainView.showProgress();
        }
        mainModel.loadData(areaId,userId,this);
    }

    @Override
    public void addCart(int fruitId, int userId, int amount) {
        mainModel.addCart(fruitId,userId,amount,this);
    }

    @Override
    public void onDestroy() {
        mainView = null;
    }

    @Override
    public void onAddCartSuccess(String message) {
        mainView.addCartSuccess(message);
    }

    @Override
    public void onAddCartError(String message) {
        mainView.addCartFail(message);
    }

    @Override
    public void onLoadDataError(String message) {
        if (mainView != null)
        {
            mainView.hideProgress();
            mainView.LoadFail(message);
        }
    }

    @Override
    public void onLoadDataSuccess(Main main) {
        if (mainView != null)
        {
            mainView.hideProgress();
            mainView.LoadSuccess(main);
        }
    }
}
