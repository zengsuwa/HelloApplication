package com.zsw.zeng.helloapplication.slide;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.zsw.zeng.helloapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zeng
 * @date 2016/9/5
 * @Description:
 */
public class SlideActivity extends FragmentActivity {

    private ViewPager viewPager;
    private RadioGroup radioGroup;
    private List<Fragment> fragments = new ArrayList<>();
    private YouFragment2 youFragment2 = new YouFragment2();
    private MyFragment2 myFragment2 = new MyFragment2();
    private HeFragment2 heFragment2 = new HeFragment2();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        radioGroup = (RadioGroup) findViewById(R.id.radiogroup);

        fragments.add(youFragment2);
        fragments.add(myFragment2);
        fragments.add(heFragment2);

        FragmentManager fm = getSupportFragmentManager();

        viewPager.setOffscreenPageLimit(2);
        viewPager.setAdapter(new MyPagerAdapter(fm,fragments));
        viewPager.setCurrentItem(0);
        RadioButton ra = (RadioButton) radioGroup.getChildAt(0);
        ra.setChecked(true);

        initEvent();

    }

    private void initEvent() {
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                RadioButton rb = (RadioButton) radioGroup.getChildAt(position);
                rb.setChecked(true);
                Log.e("qqq",position+"");
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                //checkedId不是角标     2131492947  2131492948  2131492949
                Log.e("qqq",checkedId+"");
                for (int i = 0; i < radioGroup.getChildCount(); i++) {
                    if (radioGroup.getChildAt(i).getId() == checkedId) {
                        viewPager.setCurrentItem(i, false);
                    }
                }
            }
        });

    }

}
