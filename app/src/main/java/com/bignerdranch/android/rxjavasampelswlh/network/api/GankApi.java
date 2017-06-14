package com.bignerdranch.android.rxjavasampelswlh.network.api;

import com.bignerdranch.android.rxjavasampelswlh.model.GankBeautyResult;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Administrator on 2017/6/14/014.
 */

public interface GankApi {
    @GET("data/福利/{numbers}/{pages}")
    Observable<GankBeautyResult> getBeauty(@Path("numbers") int numbers, @Path("pages") int pages);
}
