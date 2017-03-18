package com.wxx.shopping.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wxx.shopping.R;
import com.wxx.shopping.bean.HotBean;
import com.wxx.shopping.utils.OnItemClickListener;

import java.util.List;

/**
 * 作者：Tangren_ on 2017/3/18 14:39.
 * 邮箱：wu_tangren@163.com
 * TODO:一句话描述
 */

public class TypeCartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public ImageView imageView;
    public TextView title;
    public TextView prices;
    public TextView sell;
    private OnItemClickListener listener;
    private List<HotBean.ListBean> listBeen;

    public TypeCartViewHolder(View itemView, OnItemClickListener listener, List<HotBean.ListBean> listBeen) {
        super(itemView);
        this.listener = listener;
        this.listBeen = listBeen;
        imageView = (ImageView) itemView.findViewById(R.id.image_type_cart);
        title = (TextView) itemView.findViewById(R.id.title_type_cart);
        prices = (TextView) itemView.findViewById(R.id.prices_type_cart);
        sell = (TextView) itemView.findViewById(R.id.sell_type_cart);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (listener != null)
            listener.onClick(v, getAdapterPosition(), listBeen);
    }
}
