package com.wxx.shopping.moudle.manager;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.wxx.shopping.R;
import com.wxx.shopping.base.BaseActivity;
import com.wxx.shopping.base.BasePresenter;
import com.wxx.shopping.bean.AddRessInfo;
import com.wxx.shopping.utils.DBManager;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者：Tangren_ on 2017/3/23 14:49.
 * 邮箱：wu_tangren@163.com
 * TODO:一句话描述
 */

public class AppendAddress extends BaseActivity {

    @BindView(R.id.search)
    TextView search;
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.consignee)
    EditText consignee;
    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.address)
    EditText address;
    @BindView(R.id.street)
    EditText street;
    @BindView(R.id.save)
    Button save;

    private final int DEFAULT = 0;

    @Override
    protected void initView() {
        search.setVisibility(View.GONE);
        toolBar.setTitle("收货地址");
        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_apendaddress;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }


    @OnClick(R.id.save)
    public void onClick(View view) {
        String con = consignee.getText().toString();
        String pho = phone.getText().toString();
        String addres = address.getText().toString();
        String streets = street.getText().toString();
        if (con.equals("") || pho.equals("") || addres.equals("") || streets.equals("")) {
            AddRessInfo info = new AddRessInfo(null, con, pho, addres, streets, false);
            DBManager.insert(info);
        }

        Intent intent = new Intent();
        this.setResult(DEFAULT);
        finishActivityFromRight();
    }
}
