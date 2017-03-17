package com.wxx.shopping.moudle.type;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.wxx.shopping.R;
import com.wxx.shopping.base.BaseFragment;
import com.wxx.shopping.base.BasePresenter;

import butterknife.BindView;

/**
 * 作者：Tangren_ on 2017/3/15 20:50.
 * 邮箱：wu_tangren@163.com
 * TODO:一句话描述
 */

public class ContentFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    private static final String ARG_TYPE = "arg_type";
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refresh)
    SwipeRefreshLayout refresh;

    private int type;


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
            type = args.getInt(ARG_TYPE, 0);
        }
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
        return R.layout.fragment_content;
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onRefresh() {

    }
}
