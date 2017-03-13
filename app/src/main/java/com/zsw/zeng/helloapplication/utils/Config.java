package com.zsw.zeng.helloapplication.utils;

import com.zsw.zeng.helloapplication.enums.LOGLEVEL;

/**
 * @author ZengSuWa
 * @Description：
 * @Company：众鑫贷
 * @Created time：2017/3/13 11:25
 */

public class Config {

    public static String PREFERENCE_NAME = "AndroidSharePreferenceFile";
    public static LOGLEVEL ANDROID_LOG_LEVEL;

    public Config() {
    }

    static {
        ANDROID_LOG_LEVEL = LOGLEVEL.DEFAULT;
    }

}
