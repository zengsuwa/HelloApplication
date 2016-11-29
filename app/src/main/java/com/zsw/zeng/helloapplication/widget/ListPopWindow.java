package com.zsw.zeng.helloapplication.widget;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zsw.zeng.helloapplication.R;

import java.util.List;

/**
 * @author zeng
 * @date 2016/9/23
 * @Description:
 */
public class ListPopWindow {
    private Context context;
    private PopupWindow popupWindow;
    private List<String> list;
    private LayoutInflater inflater;
    private View popParentBmView;// 父view(子pop的显示view)
    private PopWindowCallBack popWindowCallBack;// 回调函数
    private RelativeLayout rl_bg;
    private ListView mylist;
    private Button cancle;


    public ListPopWindow(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    /**
     * 调用该方法 显示pop
     *
     * @param popParentBmView
     */
    public void showView(View popParentBmView) {

        this.popParentBmView = popParentBmView;
        initView();
        initEvent();
    }

    public ListPopWindow setListPopWindow(PopWindowCallBack popWindowCallBack) {
        this.popWindowCallBack = popWindowCallBack;
        return this;
    }

    /**
     * 回调
     *
     * @author liuyi
     */
    public interface PopWindowCallBack {
        public void setCallBack(int position);
    }

    public void initView() {
        inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.pop_window, null);
        rl_bg = (RelativeLayout) view.findViewById(R.id.rl_bg);
        mylist = (ListView) view.findViewById(R.id.mylist);
        cancle = (Button) view.findViewById(R.id.btn_cancle);
        mylist.setAdapter(new MyPopWindowAdapter());
        popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        // 使其聚集
        popupWindow.setFocusable(true);
        // 设置允许在外点击消失
        popupWindow.setOutsideTouchable(true);
        // 这个是为了点击“返回Back”也能使其消失，并且并不会影响你的背景（很神奇的）
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        // 动画渐变
        popupWindow.setAnimationStyle(android.R.style.Animation_InputMethod);
        popupWindow.showAtLocation(popParentBmView, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);

    }

    private void initEvent() {

        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        rl_bg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        mylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                popWindowCallBack.setCallBack(position);
                popupWindow.dismiss();

            }
        });
    }

    class MyPopWindowAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View itemview = null;
            if (convertView == null) {
                itemview = inflater.inflate(R.layout.item_popwindow, null);
            } else {
                itemview = convertView;
            }
            TextView content = (TextView) itemview.findViewById(R.id.content);
            content.setText(list.get(position));

            return itemview;
        }
    }

}
