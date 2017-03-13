package com.zsw.zeng.helloapplication.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author ZengSuWa
 * @Description：
 * @Company：众鑫贷
 * @Created time：2017/3/13 11:17
 */

public class PreferenceHelper {

    private SharedPreferences mPreference;
    private SharedPreferences.Editor mPreferenceEditor;

    @SuppressLint({"CommitPrefEdits"})
    public PreferenceHelper(Context context) {
        this.mPreference = context.getSharedPreferences(Config.PREFERENCE_NAME, 0);
        this.mPreferenceEditor = this.mPreference.edit();
    }

    public int getInt(String key, int defaultValue) {
        return this.mPreference.getInt(key, defaultValue);
    }

    public boolean putInt(String key, int value) {
        this.mPreferenceEditor.putInt(key, value);
        return this.mPreferenceEditor.commit();
    }

    public String getString(String str, String defaultValue) {
        return this.mPreference.getString(str, defaultValue);
    }

    public boolean putString(String key, String value) {
        this.mPreferenceEditor.putString(key, value);
        return this.mPreferenceEditor.commit();
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        return this.mPreference.getBoolean(key, defaultValue);
    }

    public boolean putBoolean(String key, boolean value) {
        this.mPreferenceEditor.putBoolean(key, value);
        return this.mPreferenceEditor.commit();
    }

    public float getFloat(String key, float defaultValue) {
        return this.mPreference.getFloat(key, defaultValue);
    }

    public boolean putFloat(String key, float value) {
        this.mPreferenceEditor.putFloat(key, value);
        return this.mPreferenceEditor.commit();
    }

    public long getLong(String key, long defaultValue) {
        return this.mPreference.getLong(key, defaultValue);
    }

    public boolean putLong(String key, long value) {
        this.mPreferenceEditor.putLong(key, value);
        return this.mPreferenceEditor.commit();
    }
}
