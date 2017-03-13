package com.zsw.zeng.helloapplication.base;

import android.content.Intent;

/**
 * @author ZengSuWa
 * @Description：
 * @Company：众鑫贷
 * @Created time：2017/3/13 10:22
 */

public interface BaseInterface {

    void initBoot();

    void initViews();

    void initData(Intent var1);

    void initEvents();

}
