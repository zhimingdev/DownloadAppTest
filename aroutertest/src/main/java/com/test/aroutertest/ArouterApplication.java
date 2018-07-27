package com.test.aroutertest;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;

public class ArouterApplication extends Application {
    public static ArouterApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        ARouter.openLog();
        ARouter.openDebug();
        ARouter.init(this);
    }

    public static ArouterApplication getInstance() {
        return instance;
    }
}
