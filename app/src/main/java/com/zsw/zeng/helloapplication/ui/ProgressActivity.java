package com.zsw.zeng.helloapplication.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;

import com.zsw.zeng.helloapplication.R;
import com.zsw.zeng.helloapplication.base.BaseActivity;
import com.zsw.zeng.helloapplication.widget.ProgressDialog;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 模拟访问接口时显示加载框
 */
public class ProgressActivity extends BaseActivity {

    @BindView(R.id.btn_progress) Button btnProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.activity_progress);
    }

    @OnClick(R.id.btn_progress) public void onClick() {
        final ProgressDialog dialog = new ProgressDialog(activity, true);
        dialog.showProgressDialog();
        new Handler().postDelayed(new Runnable() {
            @Override public void run() {
                dialog.dismissProgressDialog();
            }
        }, 1000);

    }

    @Override public void initBoot() {

    }

    @Override public void initViews() {

    }

    @Override public void initData(Intent var1) {

    }

    @Override public void initEvents() {
        showToast("hahahah");
    }
}
