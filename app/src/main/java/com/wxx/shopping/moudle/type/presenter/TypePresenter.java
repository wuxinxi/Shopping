package com.wxx.shopping.moudle.type.presenter;

import com.wxx.shopping.base.BasePresenter;
import com.wxx.shopping.base.view.OnResultView;
import com.wxx.shopping.bean.HotBean;
import com.wxx.shopping.moudle.hot.model.HotModel;
import com.wxx.shopping.moudle.type.model.TypePresenterCompl;

import java.util.List;

/**
 * 作者：Tangren_ on 2017/3/18 14:22.
 * 邮箱：wu_tangren@163.com
 * TODO:一句话描述
 */

public class TypePresenter extends BasePresenter<OnResultView<HotBean.ListBean>> {
    private OnResultView view;
    private HotModel<HotBean.ListBean> model = new TypePresenterCompl();

    public TypePresenter(OnResultView view) {
        this.view = view;
    }

    public void fetchType(int typeId, int curPage) {
        if (model != null) {
            model.loadHotList(new HotModel.LoadListener() {
                @Override
                public void onSuccess(List TList, int value) {
                    if (view != null) {
                        if (!TList.toString().equals("[]"))
                            view.showList(TList, value);
                        else view.onFail("已无更多数据");
                    }
                }

                @Override
                public void onFail(String msg) {
                    if (view != null)
                        view.onFail(msg);
                }
            }, typeId, curPage);
        }
    }
}
