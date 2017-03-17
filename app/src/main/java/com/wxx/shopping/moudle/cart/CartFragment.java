package com.wxx.shopping.moudle.cart;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.wxx.shopping.R;
import com.wxx.shopping.adapter.CartAdapter;
import com.wxx.shopping.base.BaseFragment;
import com.wxx.shopping.base.BasePresenter;
import com.wxx.shopping.bean.HotBean;
import com.wxx.shopping.utils.OnAddSubListener;
import com.wxx.shopping.utils.TToast;
import com.wxx.shopping.utils.Utils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者：Tangren_ on 2017/3/15 21:26.
 * 邮箱：wu_tangren@163.com
 * TODO:购物车
 */

public class CartFragment extends BaseFragment implements OnAddSubListener {

    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.edit)
    TextView edit;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.checkbox)
    CheckBox checkbox;
    @BindView(R.id.sum)
    TextView sum;
    @BindView(R.id.settlement)
    Button settlement;

    private LinearLayoutManager manager;

    private boolean isEdited = false;//未点击编辑时

    private List<HotBean.ListBean> cartList = new ArrayList<>();

    private CartAdapter mAdapter;

    private double sumMoney = 0.0;//下单总金额

    public static CartFragment newInstance() {
        Bundle args = new Bundle();
        CartFragment fragment = new CartFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        name.setText("购物车");
        edit.setVisibility(View.VISIBLE);
        EventBus.getDefault().register(this);
        manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_cart;
    }

    @Override
    protected void initData() {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void addList(List<HotBean.ListBean> list) {
        if (mAdapter == null) {
            mAdapter = new CartAdapter(list);
            recyclerView.setAdapter(mAdapter);
            mAdapter.setAddClick(this);
            mAdapter.setSubClick(this);
        } else {
            mAdapter.add(list);
        }
        cartList.addAll(list);
        sumMoney = 0.0;
        calculationSum();

    }

    private void calculationSum() {
        for (int i = 0; i < cartList.size(); i++) {
            double prices = cartList.get(i).getPrice();
            sumMoney = sumMoney + prices;
        }
        sum.setText("合计:￥" + Utils.checkDouble(sumMoney));
    }

    @OnClick({R.id.name, R.id.edit, R.id.settlement})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.name:
                break;
            case R.id.edit:
                TToast.showToast("点击");
                if (isEdited) {
                    edit.setText("编辑");
                    checkbox.setVisibility(View.INVISIBLE);
                    isEdited = false;
                } else {
                    edit.setText("删除");
                    checkbox.setVisibility(View.VISIBLE);
                    isEdited = true;
                }
                break;
            case R.id.settlement:
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    @Override
    public void onAddClick(View view, int postion, List<HotBean.ListBean> list, int value) {
        sumMoney = sumMoney + list.get(postion).getPrice();
        sum.setText("合计：￥" + Utils.checkDouble(sumMoney));
    }

    @Override
    public void onSubClick(View view, int postion, List<HotBean.ListBean> list, int value) {
        sumMoney = sumMoney - list.get(postion).getPrice();
        sum.setText("合计：￥" + Utils.checkDouble(sumMoney));
        if (value == 0) {
            mAdapter.remove(postion);
            if (mAdapter.getItemCount() == 0) {
                sumMoney = 0.0;
                sum.setText("结算：￥0.0");
                cartList.clear();
            }
        }
    }
}
