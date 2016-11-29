package com.zsw.zeng.helloapplication.mvp.biz;

import java.util.List;

/**
 * @author zeng
 * @date 2016/11/28
 * @Description:
 */

public interface OnRequestListener {
    void onSuccess(List<String> data);
    void onFailed();

}
