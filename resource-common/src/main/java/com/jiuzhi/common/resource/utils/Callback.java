package com.jiuzhi.common.resource.utils;

/**
 * Created by Administrator on 2017/3/20.
 */
public abstract class Callback<T> {

    public abstract <T> T invoke();

}
