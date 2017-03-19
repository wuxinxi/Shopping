package com.wxx.shopping.adapter;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;
import com.wxx.shopping.MyApplication;
import com.wxx.shopping.R;
import com.wxx.shopping.bean.HotBean;
import com.wxx.shopping.utils.OnItemClickListener;
import com.wxx.shopping.viewholder.FootViewHolder;
import com.wxx.shopping.viewholder.TypeCartViewHolder;

import java.util.List;

/**
 * 作者：Tangren_ on 2017/3/18 14:39.
 * 邮箱：wu_tangren@163.com
 * TODO:一句话描述
 */

public class TypeCartAdapter extends RecyclerView.Adapter {

    private static final int ITEM_NORMAL = 0;

    public static final int ITEM_FOOT = 1;

    private List<HotBean.ListBean> listBeen;

    private LayoutInflater mInflater;

    private OnItemClickListener listener;

    public TypeCartAdapter() {

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mInflater = LayoutInflater.from(parent.getContext());
        if (viewType == ITEM_NORMAL) {
            return new TypeCartViewHolder(mInflater.inflate(R.layout.item_type_cart, parent, false), listener, listBeen);
        } else if (viewType == ITEM_FOOT)
            return new FootViewHolder(mInflater.inflate(R.layout.item_foot, parent, false));
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position == getItemCount() - 1)
            return;
        HotBean.ListBean bean = listBeen.get(position);
        if (bean == null)
            return;
        if (holder instanceof TypeCartViewHolder) {
            Picasso.with(MyApplication.getInstance()).load(bean.getImgUrl())
                    .placeholder(R.mipmap.ic_only)
                    .error(R.mipmap.ic_error)
                    .into(((TypeCartViewHolder) holder).imageView);
            ((TypeCartViewHolder) holder).title.setText(bean.getName());
            ((TypeCartViewHolder) holder).prices.setText("￥" + bean.getPrice());
            ((TypeCartViewHolder) holder).sell.setText("已售："+bean.getSale());
        }

    }

    //如果是GridLayoutManager时上拉刷新为1个Item
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) manager;
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {

                    return getItemViewType(position) == ITEM_FOOT ? gridLayoutManager.getSpanCount() : 1;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return listBeen == null ? 0 : listBeen.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position + 1 == getItemCount())
            return ITEM_FOOT;
        else
            return ITEM_NORMAL;
    }

    public void loadMore(List<HotBean.ListBean> list) {
        if (list == null) {
            change(list);
        } else {
            this.listBeen.addAll(list);
            notifyDataSetChanged();
        }
    }

    public void change(List<HotBean.ListBean> list) {
        this.listBeen = list;
        notifyDataSetChanged();
    }

    public void setClick(OnItemClickListener<HotBean.ListBean> listener) {
        this.listener = listener;
    }
}
