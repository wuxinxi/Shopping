package com.wxx.shopping.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.wxx.shopping.R;
import com.wxx.shopping.bean.HotBean;
import com.wxx.shopping.utils.OnAddSubListener;
import com.wxx.shopping.widget.NumberAddSubView;

import java.util.List;

/**
 * 作者：Tangren_ on 2017/3/16 19:25.
 * 邮箱：wu_tangren@163.com
 * TODO:一句话描述
 */

public class CartViewHolder extends RecyclerView.ViewHolder implements NumberAddSubView.OnButtonClickListener {
    public CheckBox checkBox_cart;
    public ImageView imageView_cart;
    public TextView title_cart;
    public TextView prices_cart;
    public NumberAddSubView numberAddSubView;

    private OnAddSubListener listener;
    private List<HotBean.ListBean> listBeen;

    public CartViewHolder(View itemView, List<HotBean.ListBean> listBeen, OnAddSubListener listener) {
        super(itemView);
        this.listener = listener;
        this.listBeen = listBeen;
        checkBox_cart = (CheckBox) itemView.findViewById(R.id.checkbox_cart);
        imageView_cart = (ImageView) itemView.findViewById(R.id.imageView_cart);
        title_cart = (TextView) itemView.findViewById(R.id.title_cart);
        prices_cart = (TextView) itemView.findViewById(R.id.prices_cart);
        numberAddSubView = (NumberAddSubView) itemView.findViewById(R.id.addSub);

        numberAddSubView.setOnButtonClickListener(this);
    }

    @Override
    public void onButtonAddClick(View view, int value) {
        if (listener != null)
            listener.onAddClick(view, getAdapterPosition(), listBeen, value);

    }

    @Override
    public void onButtonSubClick(View view, int value) {
        if (listener != null)
            listener.onSubClick(view, getAdapterPosition(), listBeen, value);
    }
}
