package com.zsw.zeng.helloapplication.mvp.biz;

import java.util.ArrayList;

/**
 * @author zeng
 * @date 2016/11/28
 * @Description:
 */

public class RequestBizImpl implements RequestBiz {
    @Override
    public void requestForData(final OnRequestListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //模拟网络请求，延迟两秒
                try {
                    Thread.sleep(2000);
                    ArrayList<String> data = new ArrayList<String>();
                    for (int i =0;i<10;i++){
                        data.add("item"+i);
                    }
                    if(null!=data){
                        listener.onSuccess(data);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
