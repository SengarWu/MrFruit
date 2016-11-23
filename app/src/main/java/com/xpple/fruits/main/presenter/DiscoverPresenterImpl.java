package com.xpple.fruits.main.presenter;

import com.xpple.fruits.main.model.DiscoverModel;
import com.xpple.fruits.main.model.DiscoverModelImpl;
import com.xpple.fruits.main.model.OnGetListFinishedListener;
import com.xpple.fruits.main.model.bean.ArticleResult;
import com.xpple.fruits.main.view.DiscoverView;

/**
 * Created by Administrator on 2016/11/16.
 */

public class DiscoverPresenterImpl implements DiscoverPresenter, OnGetListFinishedListener {

    private DiscoverModel discoverModel;
    private DiscoverView discoverView;

    public DiscoverPresenterImpl(DiscoverView discoverView)
    {
        this.discoverView = discoverView;
        this.discoverModel = new DiscoverModelImpl();
    }

    @Override
    public void getList(int index, int size) {
        if (discoverView != null)
        {
            discoverView.showProgress();
        }
        discoverModel.getList(index,size,this);
    }

    @Override
    public void onDestroy() {
        discoverView = null;
    }

    @Override
    public void onGetListSuccess(ArticleResult result) {
        if (discoverView != null)
        {
            discoverView.hideProgress();
            discoverView.getListSuccess(result);
        }
    }

    @Override
    public void OnGetListError(String message) {
        if (discoverView != null)
        {
            discoverView.hideProgress();
            discoverView.getListFail(message);
        }
    }
}
