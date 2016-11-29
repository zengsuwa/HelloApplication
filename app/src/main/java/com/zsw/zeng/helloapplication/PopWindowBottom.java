package com.zsw.zeng.helloapplication;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.List;


/***
 * popWindow
 * 
 * @author Mr.Liu
 * 
 */
public class PopWindowBottom {

	private LayoutInflater inflater;

	private PopupWindow popupWindow;

	private View popBmView;// 子view
	private View popParentBmView;// 父view(子pop的显示view)

	private View viewOut;
	private ListView lvPopBm;// listview
	private PopWindowBmAdapter bmAdapter;

	private PopWindowCallBack popWindowCallBack;// 回调函数

	private List<String> dataList;

	private List<String> popItems;//真正popItem

	/**
	 * 构造器
	 *
	 * @param context
	 * @param dataList
	 */
	public PopWindowBottom(Context context, List<String> dataList) {
		inflater = LayoutInflater.from(context);
		this.dataList = dataList;
	}

	/**
	 * 调用该方法 显示pop
	 * 
	 * @param popParentBmView
	 */
	public void showView(View popParentBmView) {

		this.popParentBmView = popParentBmView;

		initView();
		initData();
		initEvent();

	}

	private void initView() {

		popBmView = inflater.inflate(R.layout.pop_photo_view, null);

		viewOut = popBmView.findViewById(R.id.view_popbm_bkg);
		lvPopBm = (ListView) popBmView.findViewById(R.id.lv_popbm);

	}

	private void initData() {

		popupWindow = new PopupWindow(popBmView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		// 使其聚集
		popupWindow.setFocusable(true);
		// 设置允许在外点击消失
		popupWindow.setOutsideTouchable(true);
		// 这个是为了点击“返回Back”也能使其消失，并且并不会影响你的背景（很神奇的）
		popupWindow.setBackgroundDrawable(new BitmapDrawable());
		// 动画渐变
		popupWindow.setAnimationStyle(android.R.style.Animation_InputMethod);

		//根据tag传值，判断popItem项目，1为附件列项，2为照片列项
		popItems = dataList;


		bmAdapter = new PopWindowBmAdapter();
		lvPopBm.setAdapter(bmAdapter);

		popupWindow.showAtLocation(popParentBmView, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);

	}

	private void initEvent() {

		viewOut.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				popupWindow.dismiss();
			}
		});

		lvPopBm.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

				popWindowCallBack.setCallBack(position);
				popupWindow.dismiss();

			}
		});

	}

	public PopWindowBottom setPopWindowBottom(PopWindowCallBack popWindowCallBack) {
		this.popWindowCallBack = popWindowCallBack;
		return this;
	}

	private class PopWindowBmAdapter extends BaseAdapter {


		@Override
		public int getCount() {
			return popItems.size();
		}

		@Override
		public Object getItem(int position) {
			return position;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			if (convertView == null) {
				convertView = inflater.inflate(R.layout.adapter_pop_bottom, null);
			}

			TextView textView = (TextView) convertView.findViewById(R.id.tv_pop_bottom);
			textView.setText( popItems.get(position));

			return convertView;
		}

	}

	/**
	 * 回调
	 *
	 * @author liuyi
	 *
	 */
	public interface PopWindowCallBack {
		public void setCallBack(int position);
	}

}
