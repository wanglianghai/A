package com.bignerdranch.android.rxjavasampelswlh;

import android.app.Application;

/**
 * Created by Administrator on 2017/6/9/009.
 */

public class App extends Application {
    private static App INSTANCE;

    public static App getInstance() {
        return INSTANCE;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
    }
}
