package com.wxx.shopping.moudle.mine;

import android.os.Bundle;

import com.wxx.shopping.base.BaseFragment;
import com.wxx.shopping.base.BasePresenter;

/**
 * 作者：Tangren_ on 2017/3/15 21:25.
 * 邮箱：wu_tangren@163.com
 * TODO:一句话描述
 */

public class MineFragment extends BaseFragment {


    public static MineFragment newInstance() {
        Bundle args = new Bundle();
        MineFragment fragment = new MineFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected int initLayout() {
        return 0;
    }

    @Override
    protected void initData() {

    }
}
