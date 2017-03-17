package com.wxx.shopping.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;
import com.wxx.shopping.MyApplication;
import com.wxx.shopping.R;
import com.wxx.shopping.bean.HotBean;
import com.wxx.shopping.utils.OnAddSubListener;
import com.wxx.shopping.viewholder.CartViewHolder;

import java.util.List;

/**
 * 作者：Tangren_ on 2017/3/16 19:24.
 * 邮箱：wu_tangren@163.com
 * TODO:一句话描述
 */

public class CartAdapter extends RecyclerView.Adapter<CartViewHolder> {

    private List<HotBean.ListBean> listBeen;

    private LayoutInflater inflater;

    private OnAddSubListener listener;

    public CartAdapter(List<HotBean.ListBean> listBeen) {
        this.listBeen = listBeen;
    }

    @Override
    public CartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        inflater = LayoutInflater.from(parent.getContext());
        return new CartViewHolder(inflater.inflate(R.layout.item_cart, parent, false), listBeen, listener);
    }

    @Override
    public void onBindViewHolder(CartViewHolder holder, int position) {
        HotBean.ListBean bean = listBeen.get(position);
        if (bean == null) return;
        Picasso.with(MyApplication.getInstance()).load(bean.getImgUrl()).into(holder.imageView_cart);
        holder.checkBox_cart.setChecked(true);
        holder.prices_cart.setText("￥：" + bean.getPrice());
        holder.title_cart.setText(bean.getName());
    }

    @Override
    public int getItemCount() {
        return listBeen == null ? 0 : listBeen.size();
    }

    public void setAddClick(OnAddSubListener listener) {
        this.listener = listener;
    }

    public void setSubClick(OnAddSubListener listener) {
        this.listener = listener;
    }


    public void remove(int postion) {
        listBeen.remove(postion);
        notifyDataSetChanged();
    }

    public void add(List<HotBean.ListBean> list) {
        if (list.size() == 0) {
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
}
