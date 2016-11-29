package com.zsw.zeng.helloapplication.noslide;


import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;

import com.zsw.zeng.helloapplication.R;

public class NoSlideActivity extends Activity implements View.OnClickListener {

    private FrameLayout frame;
    private RadioButton you;
    private RadioButton my;
    private RadioButton he;
    private MyFragment myFragment;
    private HeFragment heFragment;
    private YouFragment youFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noslide);

        frame = (FrameLayout) findViewById(R.id.frame);
        my = (RadioButton) findViewById(R.id.my);
        you = (RadioButton) findViewById(R.id.you);
        he = (RadioButton) findViewById(R.id.he);

        my.setOnClickListener(this);
        you.setOnClickListener(this);
        he.setOnClickListener(this);
        //首先 我们先选定一个
        select(0);
        you.setChecked(true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.you:
                select(0);
                break;
            case R.id.my:
                select(1);
                break;
            case R.id.he:
                select(2);
                break;
        }
    }

    private void select(int i) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        hidtFragment(ft);
        switch (i) {
            case 0:
                if (youFragment == null) {
                    youFragment = new YouFragment();
                    ft.add(R.id.frame, youFragment);
                } else {
                    ft.show(youFragment);
                }
                break;
            case 1:
                if (myFragment == null) {
                    myFragment = new MyFragment();
                    ft.add(R.id.frame, myFragment);
                } else {
                    ft.show(myFragment);
                }
                break;
            case 2:
                if (heFragment == null) {
                    heFragment = new HeFragment();
                    ft.add(R.id.frame, heFragment);
                } else {
                    ft.show(heFragment);
                }
                break;
        }
        ft.commit();   //提交事务
        if(i==2){
            heFragment.getString(new HeFragment.MyCallBack() {
                @Override
                public void getData(String message) {
                    Log.e("qqq",message);
                }
            });
        }
    }

    //隐藏所有Fragment
    private void hidtFragment(FragmentTransaction fragmentTransaction) {
        if (myFragment != null) {
            fragmentTransaction.hide(myFragment);
        }
        if (youFragment != null) {
            fragmentTransaction.hide(youFragment);
        }
        if (heFragment != null) {
            fragmentTransaction.hide(heFragment);
        }

    }
}
