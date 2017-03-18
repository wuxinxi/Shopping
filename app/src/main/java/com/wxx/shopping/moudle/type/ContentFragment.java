package com.wxx.shopping.moudle.type;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.wxx.shopping.R;
import com.wxx.shopping.adapter.TypeCartAdapter;
import com.wxx.shopping.base.BaseFragment;
import com.wxx.shopping.base.view.OnResultView;
import com.wxx.shopping.bean.HotBean;
import com.wxx.shopping.moudle.type.presenter.TypePresenter;
import com.wxx.shopping.utils.TToast;
import com.wxx.shopping.widget.AutoLoadOnScrollListener;
import com.yanzhenjie.nohttp.Logger;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者：Tangren_ on 2017/3/15 20:50.
 * 邮箱：wu_tangren@163.com
 * TODO:一句话描述
 */

public class ContentFragment extends BaseFragment<OnResultView<HotBean.ListBean>, TypePresenter> implements OnResultView<HotBean.ListBean>, SwipeRefreshLayout.OnRefreshListener {

    private static final String ARG_TYPE = "arg_type";
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refresh)
    SwipeRefreshLayout refresh;
    @BindView(R.id.refreshBtn)
    Button refreshBtn;

    private int type;

    private TypeCartAdapter mAdapter;

    private GridLayoutManager manager;

    private AutoLoadOnScrollListener listener;

    private static int currPage = 1;

    public static ContentFragment newInstance(int type) {

        Bundle args = new Bundle();
        args.putInt(ARG_TYPE, type);
        ContentFragment fragment = new ContentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            type = args.getInt(ARG_TYPE, 0) + 1;
        }
    }

    @Override
    protected TypePresenter createPresenter() {
        return new TypePresenter(this);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        refresh.setColorSchemeResources(android.R.color.holo_green_light, android.R.color.holo_blue_light);
        refresh.setOnRefreshListener(this);
        mAdapter = new TypeCartAdapter();
        manager = new GridLayoutManager(getActivity(), 2);

        recyclerView.setLayoutManager(manager);
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_content;
    }

    @Override
    protected void initData() {

        refresh.post(new Runnable() {
            @Override
            public void run() {
                refresh.setRefreshing(true);
                mPresetner.fetchType(type, 1);
            }
        });

        listener = new AutoLoadOnScrollListener(manager) {
            @Override
            public void onLoadMore(int currentPage) {

                mPresetner.fetchType(type, currPage);
            }
        };
        recyclerView.addOnScrollListener(listener);
    }

    @Override
    public void onRefresh() {
        mPresetner.fetchType(type, 1);
    }

    @Override
    public void showList(List<HotBean.ListBean> TList, int value) {
        Logger.d("ContentFragment:" + TList.toString());
        currPage = value + 1;
        if (value == 1) {
            mAdapter.change(TList);
            recyclerView.setAdapter(mAdapter);
        } else mAdapter.loadMore(TList);

        mAdapter.notifyItemRemoved(mAdapter.getItemCount());
        refresh.setRefreshing(false);
        listener.setLoading(false);
    }

    @Override
    public void onFail(String msg) {
        refresh.setRefreshing(false);
        TToast.showToast(msg);
        if (mAdapter.getItemCount() <= 0) {
            refreshBtn.setVisibility(View.VISIBLE);
        } else {
            refreshBtn.setVisibility(View.GONE);
        }
        mAdapter.notifyItemRemoved(mAdapter.getItemCount());
    }

    @OnClick(R.id.refreshBtn)
    public void onClick() {
        mPresetner.fetchType(type, 1);
    }

}
