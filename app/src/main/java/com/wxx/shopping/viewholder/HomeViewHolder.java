package com.wxx.shopping.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wxx.shopping.R;
import com.wxx.shopping.bean.HomeBean;
import com.wxx.shopping.utils.OnItemClickListener;

import java.util.List;

/**
 * 作者：Tangren_ on 2017/3/14 17:52.
 * 邮箱：wu_tangren@163.com
 * TODO:一句话描述
 */

public class HomeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private OnItemClickListener listener;
    public TextView title;
    public ImageView image1, image2, image3;

    private List<HomeBean> list;

    public HomeViewHolder(View itemView, OnItemClickListener listener, List<HomeBean> list) {
        super(itemView);
        this.listener = listener;
        this.list = list;
        title = (TextView) itemView.findViewById(R.id.title);
        image1 = (ImageView) itemView.findViewById(R.id.image1);
        image2 = (ImageView) itemView.findViewById(R.id.image2);
        image3 = (ImageView) itemView.findViewById(R.id.image3);

        itemView.setOnClickListener(this);
        image1.setOnClickListener(this);
        image2.setOnClickListener(this);
        image3.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (listener != null) {
            listener.onClick(v, getAdapterPosition(), list);
        }

    }


}
