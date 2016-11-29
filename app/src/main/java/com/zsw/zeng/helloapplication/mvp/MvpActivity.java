package com.zsw.zeng.helloapplication.mvp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.zsw.zeng.helloapplication.R;
import com.zsw.zeng.helloapplication.mvp.presenter.MvpPresenter;
import com.zsw.zeng.helloapplication.mvp.view.MvpView;

import java.util.List;

/**
 * @author zeng
 * @date 2016/11/28
 * @Description:
 */

public class MvpActivity extends Activity implements MvpView,AdapterView.OnItemClickListener{
    private ListView listView;
    private ProgressBar progressBar;
    private MvpPresenter mvpPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp);
        listView = (ListView) findViewById(R.id.listview);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        mvpPresenter = new MvpPresenter(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mvpPresenter.onResume();
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void setListItem(List<String> data) {
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,data);
        listView.setAdapter(adapter);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mvpPresenter.onItemClick(position);
    }
}
