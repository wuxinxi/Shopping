package com.wxx.shopping.moudle.manager;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wxx.shopping.R;
import com.wxx.shopping.adapter.AddRessAdapter;
import com.wxx.shopping.base.BaseActivity;
import com.wxx.shopping.base.BasePresenter;
import com.wxx.shopping.bean.AddRessInfo;
import com.wxx.shopping.utils.OnItemClickListener;
import com.wxx.shopping.utils.Utils;
import com.yanzhenjie.nohttp.Logger;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者：Tangren_ on 2017/3/22 18:20.
 * 邮箱：wu_tangren@163.com
 * TODO:一句话描述
 */

public class ManagerActivity extends BaseActivity implements OnItemClickListener {
    @BindView(R.id.search)
    TextView search;
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.addess)
    Button addess;

    private List<AddRessInfo> list;
    private AddRessAdapter mAdapter;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, ManagerActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initView() {
        search.setVisibility(View.GONE);
        toolBar.setTitle("地址管理");
        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mAdapter = new AddRessAdapter();
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        mAdapter.setClick(this);
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_manager;
    }

    @Override
    protected void initData() {
        try {
            list = new Gson().fromJson(Utils.info, new TypeToken<List<AddRessInfo>>() {
            }.getType());
        } catch (Exception e) {
            e.getMessage();

        }

        Logger.d(Utils.info);


        mAdapter.add(list);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @OnClick(R.id.addess)
    public void onClick() {

    }

    @Override
    public void onClick(View view, int postion, List list) {
        switch (view.getId()) {
            case R.id.delete:
                Toast.makeText(this, "删除", Toast.LENGTH_SHORT).show();
                break;
            case R.id.edit:
                Toast.makeText(this, "编辑", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
