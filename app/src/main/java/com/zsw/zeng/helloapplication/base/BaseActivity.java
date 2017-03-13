package com.zsw.zeng.helloapplication.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.WindowManager;

import com.zsw.zeng.helloapplication.AppApplication;
import com.zsw.zeng.helloapplication.R;
import com.zsw.zeng.helloapplication.utils.NetWorkUtil;
import com.zsw.zeng.helloapplication.utils.PreferenceHelper;
import com.zsw.zeng.helloapplication.utils.ToastUtils;
import com.zsw.zeng.helloapplication.widget.ProgressDialog;

import butterknife.ButterKnife;

public abstract class BaseActivity extends Activity implements BaseInterface {
    /** 加载弹出框 */
    private ProgressDialog progressDialog;
    /** activity */
    public Activity activity;
    /** prefrence存储 */
    private PreferenceHelper preferenceHelper;

    protected void onCreate(Bundle savedInstanceState,int layoutId) {
        super.onCreate(savedInstanceState);
        setContentView(layoutId);
        ButterKnife.bind(this);

        progressDialog = new ProgressDialog(this, true);
        activity = this;
        this.preInit(savedInstanceState);
        this.initBoot();
        this.initViews();
        this.initData(this.getIntent());
        this.initEvents();
    }

    /**
     * 获取屏幕宽度
     * @return
     */
    public int screenWidth(){
        WindowManager wm = (WindowManager) activity.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        int width = display.getWidth();
        return width;
    }

    /**
     * 准备时初始化
     *
     * @param savedInstanceState
     */
    public void preInit(Bundle savedInstanceState) {
        // 设置页面竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //初始化preference
        preferenceHelper = AppApplication.getPreferenceHelper();
    }

    /**
     * 显示加载框
     */
    public void showProgressDialog() {
        progressDialog.showProgressDialog();
    }

    /**
     * 隐藏加载框
     */
    public void dismissProgressDialog() {
        progressDialog.dismissProgressDialog();
    }

    /**
     * 弹出字符串吐司
     *
     * @param msg 吐司内容
     */
    public void showToast(String msg) {
        ToastUtils.show(this, msg);
    }

    /**
     * 弹出资源类型的吐司
     *
     * @param resId 吐司内容
     */
    public void showToast(int resId) {
        this.showToast(this.getString(resId));
    }

    /**
     * 不带数据的Activity 跳转
     *
     * @param clazz
     */
    public void startActivity(Class<?> clazz) {
        this.startActivity(clazz, (Bundle) null, 0);
    }

    /**
     * 带数据的Activity跳转
     *
     * @param clazz
     * @param bundle
     */
    public void startActivity(Class<?> clazz, Bundle bundle) {
        this.startActivity(clazz, bundle, 0);
    }

    /**
     * 带请求码的activity跳转
     *
     * @param clazz
     * @param requestCode
     */
    public void startActivity(Class<?> clazz, int requestCode) {
        this.startActivity(clazz, (Bundle) null, requestCode);
    }

    /**
     * 带请求码和数据的activity跳转
     *
     * @param clazz
     * @param bundle
     * @param requestCode
     */
    public void startActivity(Class<?> clazz, Bundle bundle, int requestCode) {
        Intent intent = new Intent(this, clazz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }

        if (requestCode == 0) {
            this.startActivity(intent);
        } else {
            this.startActivityForResult(intent, requestCode);
        }

    }

    /**
     * 是否 没有网络
     * @return 返回true 就说明没有网络，返回false 就说明网络已连接
     */
    public boolean noNetWork() {
        return !NetWorkUtil.isNetworkConnected(this);
    }

    /**
     * 当没有网时，弹出吐司，提示用户检查网络
     */
    public void showNetWorkError() {
        showToast("请检查您的网络!");
    }
}
