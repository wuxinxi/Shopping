package com.wxx.shopping.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;

import com.wxx.shopping.R;

import butterknife.ButterKnife;
import me.yokeyword.fragmentation.SupportActivity;

/**
 * 作者：Tangren_ on 2017/3/14 15:12.
 * 邮箱：wu_tangren@163.com
 * TODO:一句话描述
 */

public abstract class BaseActivity<V, T extends BasePresenter<V>> extends SupportActivity {

    protected abstract void initView();

    protected abstract int initLayout();

    protected abstract void initData();

    protected T mPresenter;

    protected abstract T createPresenter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(initLayout());
        ButterKnife.bind(this);
        initView();
        mPresenter = createPresenter();
        if (mPresenter != null)
            mPresenter.attachView((V) this);
        initData();

    }


    public void showLoading() {

    }

    public void disLoading() {
    }


    protected void startActivityFromRight(Intent intent) {
        startActivity(intent);
        overridePendingTransition(R.anim.base_slide_right_in, R.anim.base_slide_remain);
    }

    protected void finishActivityFromRight() {
        finish();
        overridePendingTransition(0, R.anim.base_slide_right_out);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null)
            mPresenter.detachView();
    }

    @Override
    public void onBackPressedSupport() {
        super.onBackPressedSupport();
        finishActivityFromRight();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finishActivityFromRight();
        }
        return super.onOptionsItemSelected(item);
    }
}
