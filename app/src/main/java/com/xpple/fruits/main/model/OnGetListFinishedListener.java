package com.xpple.fruits.main.model;

import com.xpple.fruits.main.model.bean.ArticleResult;

/**
 * Created by Administrator on 2016/11/16.
 */

public interface OnGetListFinishedListener {
    void onGetListSuccess(ArticleResult result);
    void OnGetListError(String message);
}
