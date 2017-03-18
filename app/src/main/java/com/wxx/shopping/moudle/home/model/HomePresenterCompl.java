package com.wxx.shopping.moudle.home.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wxx.shopping.bean.BannerBean;
import com.wxx.shopping.bean.HomeBean;
import com.wxx.shopping.http.CallServer;
import com.wxx.shopping.http.HttpListener;
import com.wxx.shopping.utils.API;
import com.yanzhenjie.nohttp.Logger;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.CacheMode;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;

import java.util.List;

/**
 * 作者：Tangren_ on 2017/3/14 18:31.
 * 邮箱：wu_tangren@163.com
 * TODO:一句话描述
 */

public class HomePresenterCompl implements HomeModel {

    @Override
    public void loadHomeList(LoadListener listener) {

        Request<String> request = NoHttp.createStringRequest(API.CAMPAIGN_HOME, RequestMethod.GET);
        request.setCacheMode(CacheMode.NONE_CACHE_REQUEST_NETWORK);
        CallServer.getHttpclient().add(0, request, new Load(listener));
    }

    @Override
    public void loadBanner(LoadBannerListener listener) {

        Request<String> request = NoHttp.createStringRequest(API.BANNER, RequestMethod.GET);
        request.setCacheMode(CacheMode.NONE_CACHE_REQUEST_NETWORK);
        CallServer.getHttpclient().add(1, request, new Load(listener));
    }


    private class Load implements HttpListener<String> {

        private LoadListener loadListener;
        private LoadBannerListener loadBannerListener;

        public Load(LoadListener loadListener) {
            this.loadListener = loadListener;
        }

        public Load(LoadBannerListener loadBannerListener) {
            this.loadBannerListener = loadBannerListener;
        }

        @Override
        public void success(int what, Response<String> response) {
            Logger.d("SUCCESS:" + response.get().toString());
            if (what == 0) {
                List<HomeBean> homeBeanList = new Gson().fromJson(response.get().toString(), new TypeToken<List<HomeBean>>() {
                }.getType());
                loadListener.onSuccess(homeBeanList);
            } else if (what == 1) {
                List<BannerBean> banners = new Gson().fromJson(response.get().toString(), new TypeToken<List<BannerBean>>() {
                }.getType());

                loadBannerListener.onSuccess(banners);
            }

        }

        @Override
        public void fail(int what, Response<String> response) {
            if (what == 0) {
                loadListener.onFail("网络或服务器异常！");
            } else if (what == 1) {
                loadBannerListener.onFail("网络或服务器异常！");
            }
        }
    }

}
