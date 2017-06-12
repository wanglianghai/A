package com.bignerdranch.android.rxjavasampelswlh.network.api;

import com.bignerdranch.android.rxjavasampelswlh.model.Zb;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2017/6/11/011.
 */

public interface ZbApi {
    @GET("search")
    Observable<List<Zb>> search(@Query("q") String query);
}
