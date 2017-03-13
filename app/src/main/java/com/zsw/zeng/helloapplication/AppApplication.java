package com.zsw.zeng.helloapplication;

import android.app.Application;
import android.content.Context;


import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.zsw.zeng.helloapplication.constant.Constant;
import com.zsw.zeng.helloapplication.utils.Config;
import com.zsw.zeng.helloapplication.utils.PreferenceHelper;

import java.io.File;


/**
 * Created by liuyi on 15/10/15.
 */
public class AppApplication extends Application {

    private static Context context;
    private static PreferenceHelper preferenceHelper;


    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        Config.PREFERENCE_NAME = Constant.APP_PRE_NAME;
    }

    public static Context getContext() {
        return context;
    }

    /**
     * 获取数据存储
     *
     * @return
     */
    public static PreferenceHelper getPreferenceHelper() {
        synchronized (AppApplication.class) {
            if (preferenceHelper == null) {
                preferenceHelper = new PreferenceHelper(context);
            }
        }
        return preferenceHelper;
    }


}
