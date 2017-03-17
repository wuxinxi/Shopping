package com.wxx.shopping.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.wxx.shopping.R;
import com.wxx.shopping.bean.HotBean;
import com.wxx.shopping.utils.OnItemClickListener;

import java.util.List;

/**
 * 作者：Tangren_ on 2017/3/15 15:32.
 * 邮箱：wu_tangren@163.com
 * TODO:一句话描述
 */

public class HotViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public ImageView imageView;
    public TextView title;
    public TextView prices;
    public Button order;

    private OnItemClickListener listener;
    private List<HotBean.ListBean> listBeen;


    public HotViewHolder(View itemView, OnItemClickListener listener, List<HotBean.ListBean> listBeen) {
        super(itemView);
        this.listBeen = listBeen;
        this.listener = listener;
        imageView = (ImageView) itemView.findViewById(R.id.imageView);
        title = (TextView) itemView.findViewById(R.id.title);
        prices = (TextView) itemView.findViewById(R.id.prices);
        order = (Button) itemView.findViewById(R.id.order);

        order.setOnClickListener(this);
        imageView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (listener != null)
            listener.onClick(v, getAdapterPosition(), listBeen);
    }


}
