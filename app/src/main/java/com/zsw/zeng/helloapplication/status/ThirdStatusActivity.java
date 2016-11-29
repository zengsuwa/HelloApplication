package com.zsw.zeng.helloapplication.status;


import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.zsw.zeng.helloapplication.R;

import java.lang.reflect.Field;

/**
 * 第二种方式 ：
 * 实现思路，添加隐藏布局，然后我们动态的计算状态栏的高度，
 * 然后把这个高度设置成这个隐藏的布局的高度，便可以实现
 * 在这里我们通过反射来获取状态栏的高度
 */
public class ThirdStatusActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_status);
        initState();
    }

    private void initState(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            // 激活状态栏
            tintManager.setStatusBarTintEnabled(true);
            // enable navigation bar tint 激活导航栏
            tintManager.setNavigationBarTintEnabled(true);
            //设置系统栏设置颜色
            //tintManager.setTintColor(R.color.red);
            //给状态栏设置颜色
            tintManager.setStatusBarTintResource(R.color.slateblue);
            //Apply the specified drawable or color resource to the system navigation bar.
            //给导航栏设置资源
            tintManager.setNavigationBarTintResource(R.color.slateblue);
        }
    }


}
