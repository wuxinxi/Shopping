package com.wxx.shopping.moudle.home.presenter;

import com.wxx.shopping.base.BasePresenter;
import com.wxx.shopping.bean.BannerBean;
import com.wxx.shopping.bean.HomeBean;
import com.wxx.shopping.moudle.home.model.HomeModel;
import com.wxx.shopping.moudle.home.model.HomePresenterCompl;
import com.wxx.shopping.moudle.home.view.OnResultView;
import com.yanzhenjie.nohttp.Logger;

import java.util.List;

/**
 * 作者：Tangren_ on 2017/3/14 18:29.
 * 邮箱：wu_tangren@163.com
 * TODO:一句话描述
 */

public class HomePresenter extends BasePresenter<OnResultView> {

    private OnResultView view;

    private HomeModel model = new HomePresenterCompl();

    public HomePresenter(OnResultView view) {
        this.view = view;
    }

    public void fetchHomeList() {

        if (model != null) {
            model.loadHomeList(new HomeModel.LoadListener() {
                @Override
                public void onSuccess(List<HomeBean> TList) {
                    if (view != null) {
                        Logger.d("List<HomeBean> TList" + TList.toString());
                        view.showList(TList);
                    }
                }

                @Override
                public void onFail(String msg) {
                    if (view != null)
                        view.onFail(msg);
                }
            });
        }
    }

    public void fetchBanner() {
        if (model != null) {
            model.loadBanner(new HomeModel.LoadBannerListener() {
                @Override
                public void onSuccess(List<BannerBean> TList) {
                    if (view != null) {
                        view.showBanner(TList);
                    }
                }

                @Override
                public void onFail(String msg) {
                    if (view != null)
                        view.showBannerFail(msg);
                }
            });
        }
    }

}
