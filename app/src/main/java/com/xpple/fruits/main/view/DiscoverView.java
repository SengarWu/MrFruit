package com.xpple.fruits.main.view;

import com.xpple.fruits.main.model.bean.ArticleResult;

/**
 * Created by Administrator on 2016/11/16.
 */

public interface DiscoverView {
    void showProgress();

    void hideProgress();

    void getListSuccess(ArticleResult result);

    void getListFail(String message);
}
