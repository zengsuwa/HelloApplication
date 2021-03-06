package com.zsw.zeng.helloapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.zsw.zeng.helloapplication.fixtest.HotFixActivity;
import com.zsw.zeng.helloapplication.mvp.MvpActivity;
import com.zsw.zeng.helloapplication.noslide.NoSlideActivity;
import com.zsw.zeng.helloapplication.slide.SlideActivity;
import com.zsw.zeng.helloapplication.status.StatusActivity;
import com.zsw.zeng.helloapplication.ui.ProgressActivity;

/**
 * @author zeng
 * @date 2016/9/5
 * @Description:
 */
public class MainActivity extends Activity {

    private Button noslide;
    private Button isslide;
    private Button status;
    private Button mvp;
    private Button progressDialog;
    private Button hotfix;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        noslide = (Button) findViewById(R.id.noslide);
        isslide = (Button) findViewById(R.id.isslide);
        status = (Button) findViewById(R.id.status);
        mvp = (Button) findViewById(R.id.mvp);
        progressDialog = (Button) findViewById(R.id.progressDialog);
        hotfix = (Button) findViewById(R.id.hotfix);
        noslide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到不滑动的主界面
                intent = new Intent(MainActivity.this, NoSlideActivity.class);
                startActivity(intent);
            }
        });
        isslide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到滑动的主界面
                intent = new Intent(MainActivity.this, SlideActivity.class);
                startActivity(intent);
            }
        });
        status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到沉浸式状态栏
                intent = new Intent(MainActivity.this,StatusActivity.class);
                startActivity(intent);
            }
        });

        mvp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到系统实现沉浸式状态栏
                intent = new Intent(MainActivity.this, MvpActivity.class);
                startActivity(intent);
            }
        });
        progressDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //显示加载圈
                intent = new Intent(MainActivity.this, ProgressActivity.class);
                startActivity(intent);
            }
        });
        hotfix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到系统实现沉浸式状态栏
                intent = new Intent(MainActivity.this, HotFixActivity.class);
                startActivity(intent);
            }
        });
    }
}
