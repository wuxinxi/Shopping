package com.wxx.shopping.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wxx.shopping.http.CallServer;
import com.wxx.shopping.moudle.home.HomeFragment;

import butterknife.ButterKnife;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * 作者：Tangren_ on 2017/3/14 15:38.
 * 邮箱：wu_tangren@163.com
 * TODO:懒加载
 */

public abstract class BaseFragment<V, T extends BasePresenter<V>> extends SupportFragment {

    protected T mPresetner;

    protected abstract T createPresenter();

    private View rootView;

    protected abstract void initView(Bundle savedInstanceState);

    protected abstract int initLayout();

    protected abstract void initData();

    protected OnBackToFirstListener listener;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(initLayout(), container, false);
            ButterKnife.bind(this, rootView);
            initView(savedInstanceState);
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

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnBackToFirstListener) {
            listener = (OnBackToFirstListener) context;
        } else {
            throw new RuntimeException(context.toString() + "must implement OnbackFirstListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    public void showLoading() {
    }

    public void disLoading() {
    }


    @Override
    public boolean onBackPressedSupport() {
        if (getChildFragmentManager().getBackStackEntryCount() > 1) {
            popChild();
        } else {
            if (this instanceof HomeFragment) {//如果是第一个framgent 就退出app,并且停止所有的网络请求
                CallServer.getHttpclient().stopAll();
                _mActivity.finish();
            } else listener.onBackToFirstFragment(); //如果不是就返回第一个
        }
        return true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresetner != null)
            mPresetner.detachView();
    }

    public interface OnBackToFirstListener {
        void onBackToFirstFragment();
    }
}
