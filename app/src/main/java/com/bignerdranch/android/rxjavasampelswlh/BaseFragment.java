package com.bignerdranch.android.rxjavasampelswlh;

import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;

import butterknife.OnClick;
import rx.Subscription;

/**
 * Created by Administrator on 2017/6/11/011.
 */

public abstract class BaseFragment extends Fragment {
    protected Subscription mSubscription;
    protected abstract int getDialogRes();
    protected abstract int getTitleRes();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unSubscription();
    }

    @OnClick(R.id.tip_button)
    void tip() {
        new AlertDialog.Builder(getActivity())
                .setTitle(getTitleRes())
                .setView(getActivity().getLayoutInflater().inflate(getDialogRes(), null))
                .show();
    }

    protected void unSubscription() {
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }
}
