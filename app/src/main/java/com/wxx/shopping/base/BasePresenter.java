package com.wxx.shopping.base;

import java.lang.ref.WeakReference;

/**
 * 作者：Tangren_ on 2017/3/14 15:19.
 * 邮箱：wu_tangren@163.com
 * TODO:一句话描述
 */

public abstract class BasePresenter<T> {

    protected WeakReference<T> viewRef;

    public void attachView(T view) {
        viewRef = new WeakReference<T>(view);
    }

    public void detachView(){
        if (viewRef!=null){
            viewRef.clear();
            viewRef=null;
        }
    }

}
