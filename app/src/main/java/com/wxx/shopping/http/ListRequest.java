package com.wxx.shopping.http;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wxx.shopping.bean.BannerBean;
import com.wxx.shopping.bean.HomeBean;
import com.wxx.shopping.bean.HotBean;
import com.wxx.shopping.utils.API;
import com.yanzhenjie.nohttp.Headers;
import com.yanzhenjie.nohttp.Logger;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.RestRequest;
import com.yanzhenjie.nohttp.rest.StringRequest;

import java.util.List;

/**
 * 作者：Tangren_ on 2017/3/14 19:30.
 * 邮箱：wu_tangren@163.com
 * TODO:一句话描述
 */

public class ListRequest extends RestRequest {

    private static int TYPE;

    public ListRequest(String url, int TYPE) {
        this(url, RequestMethod.GET, TYPE);
    }

    public ListRequest(String url, RequestMethod requestMethod, int TYPE) {
        super(url, requestMethod);
        this.TYPE = TYPE;
    }


    @Override
    public Object parseResponse(Headers responseHeaders, byte[] responseBody) throws Exception {
        String response = StringRequest.parseResponseString(headers(), responseBody);
        Logger.d("res:" + response);
        if (TYPE == API.HOME_TYPE) {
            Logger.d("HomeBean开始");
            return new Gson().fromJson(response, new TypeToken<List<HomeBean>>() {
            }.getType());
        } else if (TYPE == API.HOT_TYPE) {
            return new Gson().fromJson(response, HotBean.class);
        } else if (TYPE == API.BANNER_TYPE) {
            Logger.d("Banner开始");
            return new Gson().fromJson(response, new TypeToken<List<BannerBean>>() {
            }.getType());
        }
        return null;
    }


}
