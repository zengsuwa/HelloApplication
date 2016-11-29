package com.zsw.zeng.helloapplication.noslide;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.zsw.zeng.helloapplication.R;


public class HeFragment extends Fragment {
    private TextView tv;
    private Button button;
    String message = "";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_he, container, false);
        tv = (TextView) view.findViewById(R.id.tv);
        button = (Button) view.findViewById(R.id.button);
        message = tv.getText().toString();
        return view;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    //实现数据传递
    public void getString(MyCallBack callBack) {
        callBack.getData(message);
    }

    //创建接口
    public interface MyCallBack {
        public void getData(String message);
    }

}
