package com.xpple.fruits.shop.presenter;

import com.xpple.fruits.shop.model.OnLoadSeedDetailListener;
import com.xpple.fruits.shop.model.OnLoadSeedFinishedListener;
import com.xpple.fruits.shop.model.SeedModel;
import com.xpple.fruits.shop.model.SeedModelImpl;
import com.xpple.fruits.shop.model.bean.SeedResult;
import com.xpple.fruits.shop.model.bean.Seeds;
import com.xpple.fruits.shop.view.SeedDetailView;
import com.xpple.fruits.shop.view.SeedView;

/**
 * Created by Administrator on 2016/11/11.
 */

public class SeedPresenterImpl implements SeedPresenter,OnLoadSeedFinishedListener, OnLoadSeedDetailListener {
    private SeedModel seedModel;
    private SeedView seedView;
    private SeedDetailView seedDetailView;

    public SeedPresenterImpl(SeedView seedView)
    {
        this.seedView = seedView;
        this.seedModel = new SeedModelImpl();
    }

    public SeedPresenterImpl(SeedDetailView seedDetailView)
    {
        this.seedDetailView = seedDetailView;
        this.seedModel = new SeedModelImpl();
    }

    @Override
    public void loadData(int userId, int areaId, int index, int size) {
        if (index == 0 || index == 1)
        {
            if (seedView != null)
            {
                seedView.showProgress();
            }
        }
        seedModel.loadData(userId,areaId,index,size,this);
    }

    @Override
    public void getSeed(int seedId) {
        if (seedView != null)
        {
            seedView.showProgress();
        }
        seedModel.getSeed(seedId,this);
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onLoadDataError(String message) {
        if (seedView != null)
        {
            seedView.hideProgress();
            seedView.LoadFail(message);
        }
        if (seedDetailView != null)
        {
            seedDetailView.hideProgress();
            seedDetailView.LoadFail(message);
        }
    }

    @Override
    public void onLoadDataSuccess(Seeds seed) {
        if (seedDetailView != null)
        {
            seedDetailView.hideProgress();
            seedDetailView.LoadSuccess(seed);
        }
    }

    @Override
    public void onLoadDataSuccess(SeedResult result) {
        if (seedView != null)
        {
            seedView.hideProgress();
            seedView.LoadSuccess(result);
        }
    }
}
