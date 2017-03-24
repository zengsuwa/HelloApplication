package com.zsw.zeng.helloapplication.fixtest;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.zsw.zeng.helloapplication.R;
import com.zsw.zeng.helloapplication.fixutils.FixDexUtils;
import com.zsw.zeng.helloapplication.textutils.MyTestClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class HotFixActivity extends Activity {
    private Button hottest;
    private Button fix;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hot_fix);
        hottest = (Button) findViewById(R.id.hottest);
        fix = (Button) findViewById(R.id.fix);

        hottest.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                MyTestClass myTestClass = new MyTestClass();
                myTestClass.testFix(HotFixActivity.this);
            }
        });

        fix.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                fixBug();
            }
        });
    }

    private void fixBug() {
        //下载服务器的dex包
        File fileDir = this.getDir("odex", MODE_PRIVATE);
        String name = "classes2.dex";
        //私有目录
        String filePath = fileDir.getAbsolutePath() + File.separator + name;
        File file = new File(filePath);

        if (file.exists()) {
            Log.i("INFO", "dex已存在");
            file.delete();
        }
        InputStream is = null;
        FileOutputStream os = null;
        try {
            is = new FileInputStream(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + name);
            os = new FileOutputStream(filePath);

            int len = 0;
            byte[] buffer = new byte[1024];
            while ((len = is.read(buffer)) != -1) {
                os.write(buffer, 0, len);
            }
            File f = new File(filePath);
            if (f.exists()) {
                Toast.makeText(this, "dex overwrite", Toast.LENGTH_SHORT).show();
            }
            FixDexUtils.loadFixedDex(this);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                os.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
