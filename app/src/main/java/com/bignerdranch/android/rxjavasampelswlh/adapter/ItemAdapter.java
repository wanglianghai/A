package com.bignerdranch.android.rxjavasampelswlh.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.android.rxjavasampelswlh.R;
import com.bignerdranch.android.rxjavasampelswlh.holder.ItemHolder;
import com.bignerdranch.android.rxjavasampelswlh.model.Item;
import com.bignerdranch.android.rxjavasampelswlh.module.MapFragment;

import java.util.List;

/**
 * Created by Administrator on 2017/6/18/018.
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemHolder> {
    private List<Item> mItems;

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.map_item, parent, false);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        holder.bindItem(mItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mItems == null ? 0 : mItems.size();
    }

    public void setItems(List<Item> items) {
        mItems = items;
        notifyDataSetChanged();
    }
}
