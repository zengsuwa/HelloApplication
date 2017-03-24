package com.zsw.zeng.helloapplication.textutils;

import android.content.Context;
import android.widget.Toast;

/**
 * @author ZengSuWa
 * @Description：
 * @Company：众鑫贷
 * @Created time：2017/3/15 17:25
 */

public class MyTestClass {

    public void testFix(Context context) {
        int a = 10;
        int b = 0;
        Toast.makeText(context, "a除以b等于" + a / b, Toast.LENGTH_SHORT).show();
    }
}
