package com.wxx.shopping.moudle.home.model;

import com.wxx.shopping.bean.BannerBean;
import com.wxx.shopping.bean.HomeBean;

import java.util.List;

/**
 * 作者：Tangren_ on 2017/3/16 10:44.
 * 邮箱：wu_tangren@163.com
 * TODO:一句话描述
 */

public interface HomeModel {

    void loadHomeList(LoadListener listener);

    interface LoadListener {

        void onSuccess(List<HomeBean> TList);

        void onFail(String msg);
    }

    void loadBanner(LoadBannerListener listener);

    interface LoadBannerListener {
        void onSuccess(List<BannerBean> TList);

        void onFail(String msg);
    }
}
