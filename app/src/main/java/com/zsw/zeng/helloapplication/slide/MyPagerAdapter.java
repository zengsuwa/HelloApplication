package com.zsw.zeng.helloapplication.slide;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * @author zeng
 * @date 2016/9/7
 * @Description:
 */
public class MyPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> list;

    public MyPagerAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
