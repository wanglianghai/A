package com.bignerdranch.android.rxjavasampelswlh.holder;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bignerdranch.android.rxjavasampelswlh.R;
import com.bignerdranch.android.rxjavasampelswlh.model.Item;
import com.bumptech.glide.Glide;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/6/18/018.
 */

public class ItemHolder extends RecyclerView.ViewHolder {
    @Bind(R.id.map_item_image) ImageView mImageView;
    @Bind(R.id.map_item_text) TextView mTextView;

    public ItemHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bindItem(Item item) {
        Glide.with(mTextView.getContext()).load(item.mUrl).into(mImageView);
        mTextView.setText(item.mDate);
    }
}
