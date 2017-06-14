package com.bignerdranch.android.rxjavasampelswlh.util;

import android.util.Log;

import rx.functions.Func1;

import com.bignerdranch.android.rxjavasampelswlh.model.GankBeauty;
import com.bignerdranch.android.rxjavasampelswlh.model.GankBeautyResult;
import com.bignerdranch.android.rxjavasampelswlh.model.Item;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/6/14/014.
 */

public class GankBeautyResultMap implements Func1<GankBeautyResult, List<Item>> {
    private static final String TAG = "GankBeautyResultMap";
    private static GankBeautyResultMap INSTANCE = new GankBeautyResultMap();

    private GankBeautyResultMap(){}

    public static GankBeautyResultMap getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Item> call(GankBeautyResult gankBeautyResult) {
        List<GankBeauty> gankBeauties = gankBeautyResult.mBeauties;
        List<Item> items = new ArrayList<>(gankBeauties.size());
                                                            //2017-06-13T07:12:55.795Z
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SS'Z'");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yy/MM/dd HH:mm:ss");

        for (GankBeauty g: gankBeauties) {
            Item i = new Item();
            try {
                Date date = inputFormat.parse(g.createdAt);
                i.mDate = outputFormat.format(date);
            } catch (ParseException e) {
                Log.e(TAG, "call: ", e);
            }

            i.mUrl = g.url;
            items.add(i);
        }

        return items;
    }
}
