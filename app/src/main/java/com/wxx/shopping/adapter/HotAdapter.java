package com.wxx.shopping.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;
import com.wxx.shopping.MyApplication;
import com.wxx.shopping.R;
import com.wxx.shopping.bean.HotBean;
import com.wxx.shopping.utils.OnItemClickListener;
import com.wxx.shopping.viewholder.FootViewHolder;
import com.wxx.shopping.viewholder.HotViewHolder;
import com.yanzhenjie.nohttp.Logger;

import java.util.List;

/**
 * 作者：Tangren_ on 2017/3/15 15:40.
 * 邮箱：wu_tangren@163.com
 * TODO:一句话描述
 */

public class HotAdapter extends RecyclerView.Adapter {

    private static final int ITEM_NORMAL = 0;

    private static final int ITEM_FOOT = 1;

    private static final int ITEM_NONE = 2;

    private OnItemClickListener listener;

    private List<HotBean.ListBean> listBeen;

    private LayoutInflater inflater;

    public HotAdapter(List<HotBean.ListBean> listBeen) {
        this.listBeen = listBeen;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        inflater = LayoutInflater.from(parent.getContext());
        if (viewType == ITEM_NORMAL) {
            return new HotViewHolder(inflater.inflate(R.layout.item_hot, parent, false), listener, listBeen);
        } else if (viewType == ITEM_FOOT)
            return new FootViewHolder(inflater.inflate(R.layout.item_foot, parent, false));
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position == getItemCount() - 1)
            return;
        HotBean.ListBean bean = listBeen.get(position);
        if (bean == null) {
            Logger.d("为空了");
            return;
        }
        if (holder instanceof HotViewHolder) {
            Picasso.with(MyApplication.getInstance()).load(bean.getImgUrl())
                    .placeholder(R.mipmap.ic_only)
                    .error(R.mipmap.ic_error)
                    .into(((HotViewHolder) holder).imageView);
            ((HotViewHolder) holder).title.setText(bean.getName());
            ((HotViewHolder) holder).prices.setText("￥：" + bean.getPrice());
        }
    }


    @Override
    public int getItemCount() {
        return listBeen == null ? 0 : listBeen.size() + 1;
    }


    @Override
    public long getItemId(int position) {
        return position;
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

    private void change(List<HotBean.ListBean> list) {
        this.listBeen = list;
        notifyDataSetChanged();
    }

    public void setClick(OnItemClickListener<HotBean.ListBean> listener) {
        this.listener = listener;
    }


}
