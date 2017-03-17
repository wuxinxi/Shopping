package com.wxx.shopping.moudle.home;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.wxx.shopping.R;
import com.wxx.shopping.adapter.HomeAdapter;
import com.wxx.shopping.base.BaseFragment;
import com.wxx.shopping.bean.BannerBean;
import com.wxx.shopping.bean.HomeBean;
import com.wxx.shopping.moudle.home.presenter.HomePresenter;
import com.wxx.shopping.moudle.home.view.OnResultView;
import com.wxx.shopping.utils.GlideImageLoader;
import com.wxx.shopping.utils.OnItemClickListener;
import com.wxx.shopping.utils.TToast;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

/**
 * 作者：Tangren_ on 2017/3/14 16:34.
 * 邮箱：wu_tangren@163.com
 * TODO:一句话描述
 */

public class HomeFragment extends BaseFragment<OnResultView, HomePresenter> implements
        OnItemClickListener<HomeBean>, OnResultView, SwipeRefreshLayout.OnRefreshListener, AppBarLayout.OnOffsetChangedListener {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.freshLayout)
    SwipeRefreshLayout freshLayout;
    @BindView(R.id.banner)
    Banner banner;

    private static List<?> images = new ArrayList<>();
    private static List<String> titles = new ArrayList<>();
    @BindView(R.id.search)
    TextView search;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;

    private HomeAdapter mAdapter;

    private LinearLayoutManager manager;


    public static HomeFragment newInstance() {
        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);
        freshLayout.setColorSchemeResources(android.R.color.holo_green_light, android.R.color.holo_blue_light);
        freshLayout.setOnRefreshListener(this);
        appBar.addOnOffsetChangedListener(this);
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_scrolling;
    }

    @Override
    protected void initData() {

        freshLayout.post(new Runnable() {
            @Override
            public void run() {
                freshLayout.setRefreshing(true);
                mPresetner.fetchHomeList();
                mPresetner.fetchBanner();
            }
        });

    }

    @Override
    public void showList(List<HomeBean> homeBeanList) {
        freshLayout.setRefreshing(false);
        mAdapter = new HomeAdapter(homeBeanList);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setClick(this);
    }

    @Override
    public void onFail(String msg) {
        freshLayout.setRefreshing(false);
    }

    @Override
    public void showBanner(List<BannerBean> TList) {

        String imageUrl[] = new String[TList.size()];
        String title[] = new String[TList.size()];
        for (int i = 0; i < TList.size(); i++) {
            imageUrl[i] = TList.get(i).getImgUrl();
            title[i] = TList.get(i).getName();
        }

        List listImage = Arrays.asList(imageUrl);
        List listTitle = Arrays.asList(title);

        images = new ArrayList(listImage);
        titles = new ArrayList(listTitle);
//
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.Default);

        banner.setImages(images);
        banner.setBannerTitles(titles);
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(2500);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }

    @Override
    public void showBannerFail(String msg) {

    }

    @Override
    public void onRefresh() {
        mPresetner.fetchHomeList();
    }

    @Override
    public void onClick(final View view, final int postion, final List<HomeBean> list) {

        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "rotationX", 0.0F, 360.0F)
                .setDuration(300);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                switch (view.getId()) {
                    case R.id.image1:
                        TToast.showToast(list.get(postion).getCpOne().getTitle());
                        break;
                    case R.id.image2:
                        TToast.showToast(list.get(postion).getCpTwo().getTitle());
                        break;
                    case R.id.image3:
                        TToast.showToast(list.get(postion).getCpThree().getTitle());
                        break;
                }
            }
        });
        animator.start();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        banner.stopAutoPlay();
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        if (verticalOffset == 0) {
            search.setVisibility(View.GONE);
        } else {
            search.setVisibility(View.VISIBLE);
        }
    }

}
