package com.wxx.shopping.moudle.mine;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.wxx.shopping.R;
import com.wxx.shopping.base.BaseFragment;
import com.wxx.shopping.base.BasePresenter;
import com.wxx.shopping.widget.TextViewBubble;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 作者：Tangren_ on 2017/3/15 21:25.
 * 邮箱：wu_tangren@163.com
 * TODO:一句话描述
 */

public class MineFragment extends BaseFragment implements AppBarLayout.OnOffsetChangedListener {


    @BindView(R.id.head)
    CircleImageView head;
    @BindView(R.id.user)
    TextView user;
    @BindView(R.id.level)
    ProgressBar level;
    @BindView(R.id.level_info)
    TextView levelInfo;
    @BindView(R.id.level_image)
    TextView levelImage;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.no_pay)
    TextViewBubble noPay;
    @BindView(R.id.no_receipt)
    TextViewBubble noReceipt;
    @BindView(R.id.no_eva)
    TextViewBubble noEva;
    @BindView(R.id.service)
    TextViewBubble service;
    @BindView(R.id.coupon)
    TextViewBubble coupon;
    @BindView(R.id.my_order)
    TextViewBubble myOrder;
    @BindView(R.id.my_money)
    TextView myMoney;
    @BindView(R.id.address_manager)
    TextView addressManager;

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
        toolbarLayout.setTitleEnabled(false);
        toolbar.setTitle("");
        appBar.addOnOffsetChangedListener(this);
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initData() {

    }


    @OnClick({R.id.head, R.id.user, R.id.no_pay, R.id.no_receipt, R.id.no_eva, R.id.service,
            R.id.coupon, R.id.my_order, R.id.my_money, R.id.address_manager})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.head:
                break;
            case R.id.user:
                break;
            case R.id.no_pay:
                break;
            case R.id.no_receipt:
                break;
            case R.id.no_eva:
                break;
            case R.id.service:
                break;
            case R.id.coupon:
                break;
            case R.id.my_order:
                break;
            case R.id.my_money:
                break;
            case R.id.address_manager:
                break;
        }
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        if (verticalOffset == 0)
            toolbar.setTitle("");
        else toolbar.setTitle("个人中心");
    }

}
