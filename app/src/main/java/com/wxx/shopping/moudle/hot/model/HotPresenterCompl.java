package com.wxx.shopping.moudle.hot.model;

import com.wxx.shopping.bean.HotBean;
import com.wxx.shopping.http.CallServer;
import com.wxx.shopping.http.HttpListener;
import com.wxx.shopping.http.ListRequest;
import com.wxx.shopping.utils.API;
import com.yanzhenjie.nohttp.Logger;
import com.yanzhenjie.nohttp.rest.CacheMode;
import com.yanzhenjie.nohttp.rest.Response;

/**
 * 作者：Tangren_ on 2017/3/15 12:15.
 * 邮箱：wu_tangren@163.com
 * TODO:一句话描述
 */

public class HotPresenterCompl implements HotModel<HotBean.ListBean> {

    @Override
    public void loadHotList(LoadListener listener, int... value) {
        ListRequest request = new ListRequest(API.WARES_HOT, API.HOT_TYPE);
        request.add("curPage", value[0]);
        request.add("pageSize", API.PAGESIZE);
        request.setCacheMode(CacheMode.REQUEST_NETWORK_FAILED_READ_CACHE);
        CallServer.getHttpclient().add(1, request, new LoadHot(listener));
    }

    private class LoadHot implements HttpListener<HotBean> {

        private LoadListener<HotBean.ListBean> listener;

        public LoadHot(LoadListener<HotBean.ListBean> listener) {
            this.listener = listener;
        }

        @Override
        public void success(int what, Response<HotBean> response) {
            Logger.d("LoadHot：" + response.get());
            HotBean hotBean = response.get();
            listener.onSuccess(hotBean.getList(), hotBean.getCurrentPage());
        }

        @Override
        public void fail(int what, Response<HotBean> response) {
            Logger.d("失败");
            listener.onFail("网络或服务器异常！");
        }
    }

}
