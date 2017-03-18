package com.wxx.shopping.moudle.type.model;

import com.google.gson.Gson;
import com.wxx.shopping.bean.HotBean;
import com.wxx.shopping.http.CallServer;
import com.wxx.shopping.http.HttpListener;
import com.wxx.shopping.moudle.hot.model.HotModel;
import com.wxx.shopping.utils.API;
import com.yanzhenjie.nohttp.Logger;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.CacheMode;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;

/**
 * 作者：Tangren_ on 2017/3/18 14:26.
 * 邮箱：wu_tangren@163.com
 * TODO:一句话描述
 */

public class TypePresenterCompl implements HotModel<HotBean.ListBean> {

    @Override
    public void loadHotList(final LoadListener listener, int... value) {
        Logger.d("contentFragment 发起请求！");
        Request<String> request = NoHttp.createStringRequest(API.WARES_LIST, RequestMethod.GET);
        request.add("categoryId", value[0]);
        request.add("curPage", value[1]);
        request.add("pageSize", API.PAGESIZE);
        request.setCacheMode(CacheMode.NONE_CACHE_REQUEST_NETWORK);
        CallServer.getHttpclient().add(1, request, new HttpListener<String>() {
            @Override
            public void success(int what, Response<String> response) {
                Logger.d("contentFragment 发起请求，得到数据：" + response.get().toString());
                HotBean bean = new Gson().fromJson(response.get().toString(), HotBean.class);
                listener.onSuccess(bean.getList(), bean.getCurrentPage());
            }

            @Override
            public void fail(int what, Response<String> response) {
                listener.onFail("网络或服务器异常！");
            }
        });
    }
}
