package com.xpple.fruits.base;

/**
 * Created by Administrator on 2016/10/18.
 */

public interface Callback {
    void success();
    void error(Throwable error);
}
