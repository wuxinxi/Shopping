package com.wxx.shopping.moudle.hot.model;

import java.util.List;

/**
 * 作者：Tangren_ on 2017/3/15 16:07.
 * 邮箱：wu_tangren@163.com
 * TODO:一句话描述
 */

public interface HotModel<T> {

    void loadHotList(LoadListener listener, int... value);

    interface LoadListener<T> {
        void onSuccess(List<T> TList, int value);

        void onFail(String msg);
    }

}
