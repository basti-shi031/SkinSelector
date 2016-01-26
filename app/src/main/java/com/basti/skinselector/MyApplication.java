package com.basti.skinselector;

import android.app.Application;

import com.zhy.changeskin.SkinManager;

/**
 * Created by SHIBW-PC on 2016/1/26.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SkinManager.getInstance().init(this);
    }
}
