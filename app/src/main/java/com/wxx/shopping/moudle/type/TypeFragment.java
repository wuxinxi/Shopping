package com.wxx.shopping.moudle.type;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.wxx.shopping.R;
import com.wxx.shopping.base.BaseFragment;
import com.wxx.shopping.base.BasePresenter;

import java.util.ArrayList;

import butterknife.BindView;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * 作者：Tangren_ on 2017/3/15 20:40.
 * 邮箱：wu_tangren@163.com
 * TODO:一句话描述
 */

public class TypeFragment extends BaseFragment {

    @BindView(R.id.leftFragment)
    FrameLayout leftFragment;
    @BindView(R.id.rightFragment)
    FrameLayout rightFragment;


    public static TypeFragment newInstance() {
        Bundle args = new Bundle();
        TypeFragment fragment = new TypeFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        init(savedInstanceState);
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_type;
    }

    @Override
    protected void initData() {

    }

    private void init(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            ArrayList<String> listMenus = new ArrayList<>();
            listMenus.add("热门推荐");
            listMenus.add("品牌男装");
            listMenus.add("内衣配饰");
            listMenus.add("家用电器");
            listMenus.add("电脑办公");
            listMenus.add("手机数码");
            listMenus.add("母婴频道");
            listMenus.add("图书");
            listMenus.add("家居家纺");
            listMenus.add("居家生活");
            listMenus.add("个性化妆");
            listMenus.add("鞋靴箱帽");
            listMenus.add("奢侈礼品");
            listMenus.add("珠宝饰品");

            MenuListFragment menuListFragment = MenuListFragment.newInstance(listMenus);
            loadRootFragment(R.id.leftFragment, menuListFragment);
            replaceLoadRootFragment(R.id.rightFragment, ContentFragment.newInstance(0), false);
        }
    }


    public void switchContentFragment(ContentFragment fragment) {
        SupportFragment contentFragment = findChildFragment(ContentFragment.class);
        if (contentFragment != null) {
            contentFragment.replaceFragment(fragment, false);
        }
    }

    @Override
    public boolean onBackPressedSupport() {
        return true;
    }
}
