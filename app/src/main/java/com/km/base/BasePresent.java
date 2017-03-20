package com.km.base;

/**
 *
 * Created by yuan on 2017/3/19.
 */

public abstract class BasePresent<T> {

    public T mView;

    public void attach(T mView){
        this.mView = mView;
    }

    public void dettach(){
        mView = null;
    }
}