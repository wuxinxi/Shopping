package com.wxx.shopping.utils;

import android.view.View;

import com.wxx.shopping.bean.HotBean;

import java.util.List;

/**
 * 作者：Tangren_ on 2017/3/16 20:16.
 * 邮箱：wu_tangren@163.com
 * TODO:一句话描述
 */

public interface OnAddSubListener {

    void onAddClick(View view, int postion, List<HotBean.ListBean> list, int value);


    void onSubClick(View view, int postion, List<HotBean.ListBean> list, int value);
}
