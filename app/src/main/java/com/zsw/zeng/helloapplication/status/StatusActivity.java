package com.zsw.zeng.helloapplication.status;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.zsw.zeng.helloapplication.R;

/**
 * @author zeng
 * @date 2016/11/28
 * @Description:
 */

public class StatusActivity extends Activity {
    private Button systemStatus;
    private Button secondStatus;
    private Button thirdStatus;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);
        systemStatus = (Button) findViewById(R.id.system_status);
        secondStatus = (Button) findViewById(R.id.second_status);
        thirdStatus = (Button) findViewById(R.id.third_status);
        systemStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到系统实现沉浸式状态栏
                intent = new Intent(StatusActivity.this, SystemStatusActivity.class);
                startActivity(intent);
            }
        });
        secondStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到沉浸式状态栏
                intent = new Intent(StatusActivity.this, SecondStatusActivity.class);
                startActivity(intent);
            }
        });
        thirdStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转第三方实现沉浸式状态栏
                intent = new Intent(StatusActivity.this, ThirdStatusActivity.class);
                startActivity(intent);
            }
        });
    }
}
