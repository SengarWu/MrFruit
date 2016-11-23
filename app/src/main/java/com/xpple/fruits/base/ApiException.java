package com.xpple.fruits.base;

/**
 * Created by Administrator on 2016/11/1.
 */

public class ApiException extends RuntimeException{

    public ApiException(String detailMessage) {
        super(detailMessage);
    }
}
