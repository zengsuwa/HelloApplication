package com.zsw.zeng.helloapplication.status;


import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.zsw.zeng.helloapplication.R;

import java.lang.reflect.Field;

/**
 * 第二种方式 ：
 * 实现思路，添加隐藏布局，然后我们动态的计算状态栏的高度，
 * 然后把这个高度设置成这个隐藏的布局的高度，便可以实现
 * 在这里我们通过反射来获取状态栏的高度
 */
public class SecondStatusActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_status);
        initState();
    }

    private void initState(){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT){
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            LinearLayout llBar = (LinearLayout) findViewById(R.id.ll_bar);
            llBar.setVisibility(View.VISIBLE);
            //获得状态栏的高度
            int statusHeight= getStatusBarHeight();
            //动态设置隐藏布局的高度
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) llBar.getLayoutParams();
            params.height = statusHeight;
            llBar.setLayoutParams(params);
        }
    }

    /**
     * 通过反射的方式获取状态栏高度
     * @return
     */
    private int getStatusBarHeight() {
        try {
            Class<?> c = Class.forName("com.android.internal.R$dimen");
            Object obj = c.newInstance();
            Field field = c.getField("status_bar_height");
            int x = Integer.parseInt(field.get(obj).toString());
            return getResources().getDimensionPixelSize(x);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

}
