package com.zsw.zeng.helloapplication.mvp.view;

import java.util.List;

/**
 * @author zeng
 * @date 2016/11/28
 * @Description:
 */

public interface MvpView {
    //显示loading progress
    void showLoading();
    //隐藏loading progress
    void hideLoading();
    //ListView的初始化
    void setListItem(List<String> data);
    //toast消息
    void showMessage(String message);

}
