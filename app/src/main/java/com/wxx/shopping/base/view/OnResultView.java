package com.wxx.shopping.base.view;

import com.wxx.shopping.base.BaseView;

import java.util.List;

/**
 * 作者：Tangren_ on 2017/3/14 18:27.
 * 邮箱：wu_tangren@163.com
 * TODO:用泛型省得每个modeul都要写一个view层了
 */

public interface OnResultView<T> extends BaseView {

    void showList(List<T> TList, int value);

    void onFail(String msg);

    class Default {
        public static OnResultView getDefault() {
            return new OnResultView() {
                @Override
                public void showList(List TList, int value) {

                }

                @Override
                public void onFail(String msg) {

                }


                @Override
                public void showLoading() {

                }

                @Override
                public void disLoading() {

                }
            };
        }
    }
}
