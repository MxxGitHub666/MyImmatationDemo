package com.mxx.myimmatationdemo;

import android.app.Application;
import android.content.Context;

/**
 * Created by 98179 on 2019/6/18.
 */

public class MyApp extends Application{

    private static Context mContext;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

    public static Context getContext() {
        return mContext;
    }
}
