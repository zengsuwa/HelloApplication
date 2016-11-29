package com.zsw.zeng.helloapplication;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * @author zeng
 * @date 2016/9/23
 * @Description:
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
