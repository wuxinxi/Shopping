package com.wxx.shopping.base;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * 作者：Tangren_ on 2017/3/14 15:38.
 * 邮箱：wu_tangren@163.com
 * TODO:一句话描述
 */

public abstract class BaseFragment2<V, T extends BasePresenter<V>> extends Fragment {

    protected T mPresetner;

    protected abstract T createPresenter();

    private View rootView;

    protected abstract void initView();

    protected abstract int initLayout();

    protected abstract void initData();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(initLayout(), container, false);
            ButterKnife.bind(this, rootView);
            initView();
            mPresetner = createPresenter();
            if (mPresetner != null)
                mPresetner.attachView((V) this);
            initData();
        }
        ViewGroup group = (ViewGroup) rootView.getParent();
        if (group != null)
            group.removeView(rootView);
        return rootView;
    }

    public void showLoading() {
    }

    public void disLoading() {
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresetner != null)
            mPresetner.detachView();
    }
}
