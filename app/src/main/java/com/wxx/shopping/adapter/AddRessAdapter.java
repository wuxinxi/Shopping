package com.wxx.shopping.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.wxx.shopping.R;
import com.wxx.shopping.bean.AddRessInfo;
import com.wxx.shopping.utils.OnItemClickListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：Tangren_ on 2017/3/22 19:56.
 * 邮箱：wu_tangren@163.com
 * TODO:一句话描述
 */

public class AddRessAdapter extends RecyclerView.Adapter {

    private LayoutInflater mInflater;
    private List<AddRessInfo> list;
    private OnItemClickListener listener;

    public AddRessAdapter() {
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mInflater = LayoutInflater.from(parent.getContext());
        return new AddRessViewHolder(mInflater.inflate(R.layout.item_address, parent, false), list, listener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        AddRessViewHolder viewHolder = (AddRessViewHolder) holder;
        viewHolder.name.setText(list.get(position).getName());
        viewHolder.phone.setText(list.get(position).getPhone());
        viewHolder.address.setText(list.get(position).getAddress());
        boolean isChecked = list.get(position).getIsCheck();
        if (isChecked) {
            viewHolder.checkBox.setChecked(true);
            viewHolder.checkBox.setText("默认地址");
        } else {
            viewHolder.checkBox.setChecked(false);
            viewHolder.checkBox.setText("设为默认");
        }
    }


    public void add(List<AddRessInfo> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void loadMore(List<AddRessInfo> list) {
        if (list == null) {
            add(list);
        } else {
            this.list.addAll(list);
            notifyDataSetChanged();
        }
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public void setClick(OnItemClickListener listener) {
        this.listener = listener;
    }

    class AddRessViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.phone)
        TextView phone;
        @BindView(R.id.address)
        TextView address;
        @BindView(R.id.checkBox)
        CheckBox checkBox;
        @BindView(R.id.edit)
        TextView edit;
        @BindView(R.id.delete)
        TextView delete;

        private OnItemClickListener listener;
        private List<AddRessInfo> list;

        public AddRessViewHolder(View itemView, List<AddRessInfo> list, OnItemClickListener listener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.listener = listener;
            this.list = list;

            edit.setOnClickListener(this);
            delete.setOnClickListener(this);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (listener != null) {
                listener.onClick(v, getAdapterPosition(), list);
            }
        }
    }
}
