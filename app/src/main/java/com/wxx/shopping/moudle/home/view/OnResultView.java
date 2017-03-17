package com.wxx.shopping.moudle.home.view;

import com.wxx.shopping.base.BaseView;
import com.wxx.shopping.bean.BannerBean;
import com.wxx.shopping.bean.HomeBean;

import java.util.List;

/**
 * 作者：Tangren_ on 2017/3/14 18:27.
 * 邮箱：wu_tangren@163.com
 * TODO:用泛型省得每个modeul都要写一个view层了
 */

public interface OnResultView extends BaseView {
    void showList(List<HomeBean> TList);

    void onFail(String msg);

    void showBanner(List<BannerBean> TList);

    void showBannerFail(String msg);

    class Default {
        public static OnResultView getDefault() {
            return new OnResultView() {
                @Override
                public void showList(List<HomeBean> TList) {

                }

                @Override
                public void onFail(String msg) {

                }

                @Override
                public void showBanner(List<BannerBean> TList) {

                }

                @Override
                public void showBannerFail(String msg) {

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
