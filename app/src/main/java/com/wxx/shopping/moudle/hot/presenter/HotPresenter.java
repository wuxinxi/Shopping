package com.wxx.shopping.moudle.hot.presenter;

import com.wxx.shopping.base.BasePresenter;
import com.wxx.shopping.bean.HotBean;
import com.wxx.shopping.moudle.hot.model.HotModel;
import com.wxx.shopping.moudle.hot.model.HotPresenterCompl;
import com.wxx.shopping.moudle.hot.view.OnResultView;

import java.util.List;

/**
 * 作者：Tangren_ on 2017/3/15 11:58.
 * 邮箱：wu_tangren@163.com
 * TODO:一句话描述
 */

public class HotPresenter extends BasePresenter<OnResultView<HotBean.ListBean>> {

    private OnResultView view;
    private HotModel<HotBean.ListBean> model = new HotPresenterCompl();

    public HotPresenter(OnResultView view) {
        this.view = view;
    }

    public void fetchHot(int page) {
        if (model != null) {
            model.loadHotList(new HotModel.LoadListener() {
                @Override
                public void onSuccess(List TList, int value) {
                    if (view != null) {
                        if (!TList.toString().equals("[]"))
                            view.showList(TList, value);
                        else view.onFail("已加载全部");
                    }
                }

                @Override
                public void onFail(String msg) {
                    if (view != null)
                        view.onFail(msg);
                }
            }, page);
        }
    }
}
