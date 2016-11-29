package com.zsw.zeng.helloapplication.status;


import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;

import com.zsw.zeng.helloapplication.R;

/**
 * 第一种方式 ：系统的方式沉浸式状态栏实现
 * 当系统版本为4.4或者4.4以上时可以使用沉浸式状态栏
 */
public class SystemStatusActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_status);
        initState();
    }

    private void initState(){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT){
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

}
