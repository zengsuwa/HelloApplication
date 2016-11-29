package com.zsw.zeng.helloapplication.mvp.presenter;

import android.os.Handler;
import android.os.Looper;
import android.provider.ContactsContract;

import com.zsw.zeng.helloapplication.mvp.biz.OnRequestListener;
import com.zsw.zeng.helloapplication.mvp.biz.RequestBiz;
import com.zsw.zeng.helloapplication.mvp.biz.RequestBizImpl;
import com.zsw.zeng.helloapplication.mvp.view.MvpView;

import java.util.List;

/**
 * @author zeng
 * @date 2016/11/28
 * @Description:
 */

public class MvpPresenter {
    private MvpView mvpView;
    RequestBiz requestBiz;
    private Handler handler;

    public MvpPresenter(MvpView mvpView) {
        this.mvpView = mvpView;
        this.requestBiz = new RequestBizImpl();
        this.handler = new Handler(Looper.getMainLooper());
    }

    public void onResume(){
        mvpView.showLoading();
        requestBiz.requestForData(new OnRequestListener() {
            @Override
            public void onSuccess(final List<String> data) {
                //由于请求开启了新线程，所以用handler去更新界面
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        mvpView.hideLoading();
                        mvpView.setListItem(data);
                    }
                });
            }

            @Override
            public void onFailed() {
                mvpView.hideLoading();
                mvpView.showMessage("请求失败");
            }
        });
    }
    public void onItemClick(int postion){
        mvpView.showMessage("点击了item"+postion);
    }

}
