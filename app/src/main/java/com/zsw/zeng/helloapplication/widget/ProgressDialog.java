package com.zsw.zeng.helloapplication.widget;

import android.app.Dialog;
import android.content.Context;

import com.zsw.zeng.helloapplication.R;

/**
 * @author ZengSuWa
 * @Description：加载圈
 * @Company：众鑫贷
 * @Created time：2017/3/10 14:41
 */

public class ProgressDialog {
    private Dialog dialog;
    private Context context;
    private boolean cancelable;

    public ProgressDialog(Context context, boolean cancelable) {
        this.context = context;
        this.cancelable = cancelable;
    }

    /**
     * 显示加载框
     */
    public void showProgressDialog() {
        if (dialog == null) {
            //去掉dialog自带的样式
            dialog = new Dialog(context,R.style.progress_dialog);
            //设置弹出框的内容
            dialog.setContentView(R.layout.progress_dialog);
            //设置进度条是否可以按退回键取消
            dialog.setCancelable(cancelable);
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            //设置点击进度对话框外的区域对话框不消失
            dialog.setCanceledOnTouchOutside(!cancelable);
            if(!dialog.isShowing()){
                dialog.show();
            }

        }

    }

    /**
     * 隐藏加载框
     */
    public void dismissProgressDialog() {
        if(dialog != null){
            dialog.dismiss();
            dialog = null;
        }
    }

}
