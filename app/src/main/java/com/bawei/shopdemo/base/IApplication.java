package com.bawei.shopdemo.base;

import android.app.Application;

import com.uuzuche.lib_zxing.activity.ZXingLibrary;

/**
 * Created by Bonnenu1t丶 on 2017/6/20.
 */

public class IApplication extends Application {

    public static IApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;

        ZXingLibrary.initDisplayOpinion(this);

        application = this;
    }

}
