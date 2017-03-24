package com.zsw.zeng.helloapplication.fixtest;

import android.app.Application;
import android.content.Context;


import com.zsw.zeng.helloapplication.constant.Constant;
import com.zsw.zeng.helloapplication.fixutils.FixDexUtils;
import com.zsw.zeng.helloapplication.utils.Config;
import com.zsw.zeng.helloapplication.utils.PreferenceHelper;


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

    @Override protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        FixDexUtils.loadFixedDex(this);
    }
}
