package com.wxx.shopping.moudle.hot;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.wxx.shopping.R;
import com.wxx.shopping.adapter.HotAdapter;
import com.wxx.shopping.base.BaseFragment;
import com.wxx.shopping.bean.HotBean;
import com.wxx.shopping.moudle.hot.presenter.HotPresenter;
import com.wxx.shopping.moudle.hot.view.OnResultView;
import com.wxx.shopping.utils.OnItemClickListener;
import com.wxx.shopping.utils.TToast;
import com.wxx.shopping.widget.AutoLoadOnScrollListener;
import com.yanzhenjie.nohttp.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 作者：Tangren_ on 2017/3/15 11:25.
 * 邮箱：wu_tangren@163.com
 * TODO:一句话描述
 */

public class HotFragment extends BaseFragment<OnResultView<HotBean.ListBean>, HotPresenter> implements
        OnResultView<HotBean.ListBean>, OnItemClickListener<HotBean.ListBean>, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.freshLayout)
    SwipeRefreshLayout freshLayout;

    private HotAdapter mAdapter;

    private LinearLayoutManager manager;

    private AutoLoadOnScrollListener listener;

    private static int currPage = 1;

    private Snackbar loadFail;
    private Snackbar loadAll;

    public static HotFragment newInstance() {
        Bundle args = new Bundle();
        HotFragment fragment = new HotFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected HotPresenter createPresenter() {
        return new HotPresenter(this);
    }

    @Subscribe
    @Override
    protected void initView(Bundle savedInstanceState) {
        manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);
        freshLayout.setColorSchemeResources(android.R.color.holo_green_light, android.R.color.holo_blue_light);
        freshLayout.setOnRefreshListener(this);

    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_hot;
    }

    @Override
    protected void initData() {

        freshLayout.post(new Runnable() {
            @Override
            public void run() {
                freshLayout.setRefreshing(true);
                mPresetner.fetchHot(1);
            }
        });

        listener = new AutoLoadOnScrollListener(manager) {
            @Override
            public void onLoadMore(int currentPage) {
                mPresetner.fetchHot(currPage);
            }
        };

        recyclerView.addOnScrollListener(listener);
    }

    @Override
    public void showList(List<HotBean.ListBean> TList, int value) {

        Logger.d("value:" + value);
        //value=1时表示是第一页
        if (value == 1) {
            mAdapter = new HotAdapter(TList);
            recyclerView.setAdapter(mAdapter);
            mAdapter.setClick(this);
        } else mAdapter.loadMore(TList);

        mAdapter.notifyItemRemoved(mAdapter.getItemCount());
        freshLayout.setRefreshing(false);
        listener.setLoading(false);
        currPage = value + 1;

    }

    @Override
    public void onFail(String msg) {
        freshLayout.setRefreshing(false);
        listener.setLoading(false);
        if (mAdapter != null) {
            mAdapter.notifyItemRemoved(mAdapter.getItemCount());
        }

        TToast.showToast(msg);

    }


    @Override
    public void onClick(final View view, final int postion, final List<HotBean.ListBean> list) {

        final HotBean.ListBean listBean = list.get(postion);

        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "rotationX", 0.0F, 360.0F)
                .setDuration(300);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                //下单
                if (view.getId() == R.id.order) {
                    List<HotBean.ListBean> list = new ArrayList<>();
                    list.add(listBean);
                    EventBus.getDefault().post(list);
                }
            }
        });
        animator.start();

    }

    @Override
    public void onRefresh() {
        currPage = 1;
        mPresetner.fetchHot(currPage);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
