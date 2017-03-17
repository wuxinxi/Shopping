package com.wxx.shopping.utils;

import android.view.View;

import java.util.List;

/**
 * 作者：Tangren_ on 2017/3/14 17:53.
 * 邮箱：wu_tangren@163.com
 * TODO:一句话描述
 */

public interface OnItemClickListener<T> {
    void onClick(View view, int postion, List<T> list);
}
