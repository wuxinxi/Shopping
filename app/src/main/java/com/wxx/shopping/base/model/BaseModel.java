package com.wxx.shopping.base.model;

import java.util.List;

/**
 * 作者：Tangren_ on 2017/3/15 11:50.
 * 邮箱：wu_tangren@163.com
 * TODO:int... value
 */

public interface BaseModel<T> {

    void loadHomeList(LoadListener listener, int... value);

    interface LoadListener<T> {
        void onSuccess(List<T> TList, int value);

        void onFail(String msg);
    }
}
