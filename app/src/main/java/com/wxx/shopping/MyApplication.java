package com.wxx.shopping;

import android.app.Application;

import com.yanzhenjie.nohttp.Logger;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.OkHttpNetworkExecutor;

import me.yokeyword.fragmentation.Fragmentation;

/**
 * 作者：Tangren_ on 2017/3/14 15:21.
 * 邮箱：wu_tangren@163.com
 * TODO:一句话描述
 */

public class MyApplication extends Application {

    private static MyApplication instance;
    private final String DB_NAME = "address.db";

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        NoHttp.initialize(this, new NoHttp.Config()
                .setNetworkExecutor(new OkHttpNetworkExecutor()));
        Logger.setDebug(true);
        Logger.setTag("----Debug日志：----");
        Fragmentation.builder()
                .stackViewMode(Fragmentation.BUBBLE)
                .install();
    }

    public static MyApplication getInstance() {
        if (instance == null) {
            synchronized (MyApplication.class) {
                if (instance == null)
                    instance = new MyApplication();
            }
        }
        return instance;
    }

}
