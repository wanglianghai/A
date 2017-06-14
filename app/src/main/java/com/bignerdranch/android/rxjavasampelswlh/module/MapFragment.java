package com.bignerdranch.android.rxjavasampelswlh.module;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bignerdranch.android.rxjavasampelswlh.BaseFragment;
import com.bignerdranch.android.rxjavasampelswlh.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/6/10/010.
 */

public class MapFragment extends BaseFragment {
    private int mPages = 1;
    @Bind(R.id.map_page) TextView mPageTextView;
    @Bind(R.id.button_previous) Button mPreviousButton;
    @Bind(R.id.fragment_map_recycler_view) RecyclerView mRecyclerView;
    @Bind(R.id.map_swipe_refresh) SwipeRefreshLayout mRefreshLayout;

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
        mPageTextView.setText(getString(R.string.page_number, mPages + ""));
        if (mPages == 1) {
            mPreviousButton.setEnabled(false);
        } else {
            mPreviousButton.setEnabled(true);
        }
    }
}
