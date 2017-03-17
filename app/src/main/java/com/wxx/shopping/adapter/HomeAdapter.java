package com.wxx.shopping.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;
import com.wxx.shopping.MyApplication;
import com.wxx.shopping.R;
import com.wxx.shopping.bean.HomeBean;
import com.wxx.shopping.utils.OnItemClickListener;
import com.wxx.shopping.viewholder.HomeViewHolder;

import java.util.List;

/**
 * 作者：Tangren_ on 2017/3/14 18:20.
 * 邮箱：wu_tangren@163.com
 * TODO:一句话描述
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeViewHolder> {

    private static int VIEW_TYPE_L = 0;
    private static int VIEW_TYPE_R = 1;

    private LayoutInflater mInflater;
    private OnItemClickListener listener;
    private List<HomeBean> homeBeanList;

    public HomeAdapter(List<HomeBean> homeBeanList) {
        this.homeBeanList = homeBeanList;
    }

    @Override
    public HomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mInflater = LayoutInflater.from(parent.getContext());
        if (viewType == VIEW_TYPE_R) {
            return new HomeViewHolder(mInflater.inflate(R.layout.item_home_odd, parent, false), listener,homeBeanList);
        }
        return new HomeViewHolder(mInflater.inflate(R.layout.item_home_even, parent, false), listener,homeBeanList);
    }

    @Override
    public void onBindViewHolder(HomeViewHolder holder, int position) {
        HomeBean homeBean = homeBeanList.get(position);
        if (homeBean == null)
            return;
        if (position == 0)
            holder.title.setVisibility(View.GONE);
        holder.title.setText(homeBean.getTitle());

        Picasso.with(MyApplication.getInstance()).load(homeBean.getCpOne().getImgUrl()).placeholder(R.mipmap.ic_only).error(R.mipmap.ic_error).into(holder.image1);
        Picasso.with(MyApplication.getInstance()).load(homeBean.getCpTwo().getImgUrl()).placeholder(R.mipmap.ic_two).error(R.mipmap.ic_error).into(holder.image2);
        Picasso.with(MyApplication.getInstance()).load(homeBean.getCpThree().getImgUrl()).placeholder(R.mipmap.ic_two).error(R.mipmap.ic_error).into(holder.image3);
    }


    @Override
    public int getItemViewType(int position) {
        if (position % 2 == 0) {
            return VIEW_TYPE_R;
        } else return VIEW_TYPE_L;


    }

    @Override
    public int getItemCount() {
        return homeBeanList == null ? 0 : homeBeanList.size();
    }

    public void setClick(OnItemClickListener listener) {
        this.listener = listener;
    }


}
