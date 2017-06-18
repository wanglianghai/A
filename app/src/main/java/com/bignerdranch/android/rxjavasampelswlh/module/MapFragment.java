package com.bignerdranch.android.rxjavasampelswlh.module;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bignerdranch.android.rxjavasampelswlh.BaseFragment;
import com.bignerdranch.android.rxjavasampelswlh.R;
import com.bignerdranch.android.rxjavasampelswlh.model.GankBeautyResult;
import com.bignerdranch.android.rxjavasampelswlh.model.Item;
import com.bignerdranch.android.rxjavasampelswlh.network.Network;
import com.bignerdranch.android.rxjavasampelswlh.util.GankBeautyResultMap;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/6/10/010.
 */

public class MapFragment extends BaseFragment {
    private static final String TAG = "MapFragment";
    private int mPages = 2;

    private MapAdapter mAdapter = new MapAdapter();
    @Bind(R.id.map_page) TextView mPageTextView;
    @Bind(R.id.button_previous) Button mPreviousButton;
    @Bind(R.id.fragment_map_recycler_view) RecyclerView mRecyclerView;
    @Bind(R.id.map_swipe_refresh) SwipeRefreshLayout mRefreshLayout;

    private Observer<List<Item>> mObserver = new Observer<List<Item>>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            Log.e(TAG, "onError: ", e);
        }

        @Override
        public void onNext(List<Item> gankBeautyResults) {
            mRefreshLayout.setRefreshing(false);
            mAdapter.setItems(gankBeautyResults);
        }
    };

    @OnClick(R.id.button_previous)
    void previousPage() {
        mPages--;
        updateButton();
    }

    @OnClick(R.id.button_next)
    void nextPage() {
        mPages++;
        updateButton();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        ButterKnife.bind(this, view);
        mRefreshLayout.setEnabled(false);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.setAdapter(mAdapter);
        updateButton();
        return view;
    }

    @Override
    protected int getDialogRes() {
        return R.layout.dialog_map;
    }

    @Override
    protected int getTitleRes() {
        return R.string.title_map;
    }

    void updateButton() {
        mAdapter.setItems(null);
        mRefreshLayout.setRefreshing(true);
        mPageTextView.setText(getString(R.string.page_number, mPages + ""));
        if (mPages == 1) {
            mPreviousButton.setEnabled(false);
        } else {
            mPreviousButton.setEnabled(true);
        }
        loadPage();
    }

    private void loadPage() {
        unSubscription();
        Log.i(TAG, "loadPage: " + mPages);
        mSubscription = Network.getGankApi()
                .getBeauty(10, mPages)
                .map(GankBeautyResultMap.getInstance())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mObserver);
    }

    class MapHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.map_item_image) ImageView mImageView;
        @Bind(R.id.map_item_text) TextView mTextView;

        public MapHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindItem(Item item) {
            Glide.with(getActivity()).load(item.mUrl).into(mImageView);
            mTextView.setText(item.mDate);
        }
    }

    private class MapAdapter extends RecyclerView.Adapter<MapHolder> {
        private List<Item> mItems;

        @Override
        public MapHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = getActivity().getLayoutInflater().inflate(R.layout.map_item, parent, false);
            return new MapHolder(view);
        }

        @Override
        public void onBindViewHolder(MapHolder holder, int position) {
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
}
