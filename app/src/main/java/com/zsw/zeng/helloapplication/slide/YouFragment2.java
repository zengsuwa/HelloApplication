package com.zsw.zeng.helloapplication.slide;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.zsw.zeng.helloapplication.R;
import com.zsw.zeng.helloapplication.noslide.YouFragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class YouFragment2 extends Fragment {
    private Button btn_date;
    private TextView tv_date;
    private DatePickerDialog dialog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_you2, container, false);
        btn_date = (Button) view.findViewById(R.id.btn_date);
        tv_date = (TextView) view.findViewById(R.id.tv_date);
        //获得当前日期的年月日
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int monthOfYear = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        //弹出默认选中的是当前的日期
        dialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            //选择的时间
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Log.e("qqq","您选择的是"+year+"年"+(monthOfYear+1)+"月"+dayOfMonth+"日");
            }
        },year,monthOfYear,dayOfMonth);
        //设置可以取消
        dialog.setCancelable(true);
        //设置点击弹出框外是取消弹出框
        dialog.setCanceledOnTouchOutside(true);
        DatePicker datePicker =dialog.getDatePicker();
        //获得当前时间
        Date date = new Date();
        long currentTime= date.getTime();
        //最小时间为1990-01-01，转化时间格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        long minDate=0;
        try {
             minDate=sdf.parse("2011-09-20").getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //设置最大值为当前时间
        datePicker.setMaxDate(currentTime);
        //设置最小时间
        datePicker.setMinDate(minDate);

        btn_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });
        return view;
    }


}
