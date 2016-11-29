package com.zsw.zeng.helloapplication.update;

import android.app.Activity;
import android.os.Bundle;

import com.zsw.zeng.helloapplication.R;

import ezy.boost.update.UpdateManager;

/**
 * @author zeng
 * @date 2016/11/29
 * @Description:
 */

public class UpdateActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        //进入应用时检查更新
        UpdateManager.check(this);
    }
}
