package com.wxx.shopping.moudle.type;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.wxx.shopping.R;
import com.wxx.shopping.adapter.MenuAdapter;
import com.wxx.shopping.base.BaseFragment;
import com.wxx.shopping.base.BasePresenter;
import com.wxx.shopping.utils.OnMenuItemClickListener;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * 作者：Tangren_ on 2017/3/15 20:50.
 * 邮箱：wu_tangren@163.com
 * TODO:一句话描述
 */

public class MenuListFragment extends BaseFragment {

    private static final String ARG_MENUS = "arg_menus";

    private static final String SAVE_STATE_POSITION = "save_state_position";

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private ArrayList<String> mMenus;

    private MenuAdapter mAdapter;

    private int mCurrentPosition = -1;

    public static MenuListFragment newInstance(ArrayList<String> menus) {

        Bundle args = new Bundle();
        args.putStringArrayList(ARG_MENUS, menus);
        MenuListFragment fragment = new MenuListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        if (args != null) {
            mMenus = args.getStringArrayList(ARG_MENUS);
        }
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        LinearLayoutManager manager = new LinearLayoutManager(_mActivity);
        recyclerView.setLayoutManager(manager);
        mAdapter = new MenuAdapter(_mActivity);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setDatas(mMenus);

        mAdapter.setOnMenuItemClickListener(new OnMenuItemClickListener() {
            @Override
            public void onItemClick(int position, View view, RecyclerView.ViewHolder vh) {
                showContent(position);
            }

        });

        if (savedInstanceState != null) {
            mCurrentPosition = savedInstanceState.getInt(SAVE_STATE_POSITION);
            mAdapter.setItemChecked(mCurrentPosition);
        } else {
            mCurrentPosition = 0;
            mAdapter.setItemChecked(0);
        }
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_menulist;
    }

    @Override
    protected void initData() {

    }

    private void showContent(int position) {
        if (position == mCurrentPosition) {
            return;
        }
        mCurrentPosition = position;
        mAdapter.setItemChecked(position);
        ContentFragment fragment = ContentFragment.newInstance(position);
        ((TypeFragment) getParentFragment()).switchContentFragment(fragment);
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SAVE_STATE_POSITION, mCurrentPosition);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        recyclerView.setAdapter(null);
    }
}
