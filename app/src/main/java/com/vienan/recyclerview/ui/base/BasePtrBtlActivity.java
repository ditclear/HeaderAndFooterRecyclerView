package com.vienan.recyclerview.ui.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.vienan.recyclerview.R;
import com.vienan.recyclerview.callback.LoadmoreCallback;
import com.vienan.recyclerview.callback.RefreshCallback;
import com.vienan.recyclerview.ui.GridLayoutActivity;
import com.vienan.recyclerview.ui.MainActivity;
import com.vienan.recyclerview.ui.StaggeredGridLayoutActivity;
import com.wang.avi.AVLoadingIndicatorView;

/**
 * Created by vienan on 16/1/27.
 */
public abstract class BasePtrBtlActivity extends AppCompatActivity implements LoadmoreCallback, RefreshCallback {

    boolean isLoadingMoreData;
    boolean isRefreshingData;
    boolean isLoadedAllData;
    protected View footerView;
    private TextView tv_loading;

    protected Toolbar toobar;
    protected SwipeRefreshLayout refreshLayout;
    private AVLoadingIndicatorView progress_loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initBasePtrBlmData();


        initLoadMoreView();
    }

    protected void initToolbar(int id, int subTitleId) {
        toobar = (Toolbar) findViewById(id);
        toobar.setSubtitle(getResources().getString(subTitleId));
        setSupportActionBar(toobar);
    }

    protected void initRefreshView(int id) {
        refreshLayout = (SwipeRefreshLayout) findViewById(id);
        refreshLayout.setColorSchemeResources(R.color.main_green, R.color.orange, R.color.lighterRed);
        refreshLayout.setProgressViewOffset(false, 0, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 36, getResources().getDisplayMetrics()));
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshLayout();
            }
        });

    }

    protected abstract void refreshLayout();


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.action_LinearLayout:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.action_GridLayout:
                intent = new Intent(this, GridLayoutActivity.class);
                startActivity(intent);
                break;
            case R.id.action_StaggeredGridLayout:
                intent = new Intent(this, StaggeredGridLayoutActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initLoadMoreView() {

        footerView = LayoutInflater.from(this).inflate(R.layout.load_more, null);
        progress_loading = (AVLoadingIndicatorView) footerView.findViewById(R.id.progress_loading);
        tv_loading = (TextView) footerView.findViewById(R.id.tv_loading);
        footerView.setVisibility(View.GONE);
        tv_loading.setText("loading...");

    }

    private void initBasePtrBlmData() {

        isLoadedAllData = false;
        isLoadingMoreData = false;
        isRefreshingData = false;
    }

    protected void shortToast(String info) {
        Toast.makeText(this, info, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoadmoreStart(String info) {

        footerView.setVisibility(View.VISIBLE);
        isLoadingMoreData = true;
        progress_loading.setVisibility(View.VISIBLE);
        tv_loading.setText(!TextUtils.isEmpty(info) ?
                info : getResources().getString(R.string.text_loading));

    }

    @Override
    public void onLoadmoreSucced(String info) {
        isLoadingMoreData = false;
        footerView.setVisibility(View.VISIBLE);
        progress_loading.setVisibility(View.GONE);
        tv_loading.setText(!TextUtils.isEmpty(info) ?
                info : getResources().getString(R.string.text_load_succeed));
        shortToast(info);
    }

    @Override
    public void onLoadmoreFail(String info) {

        isLoadingMoreData = false;
        footerView.setVisibility(View.VISIBLE);
        progress_loading.setVisibility(View.GONE);
        tv_loading.setText(!TextUtils.isEmpty(info) ?
                info : getResources().getString(R.string.text_load_fail));
        shortToast(info);
    }

    @Override
    public void onLoadedAll(String info) {

        isLoadingMoreData = false;
        isLoadedAllData = true;
        progress_loading.setVisibility(View.GONE);
        footerView.setVisibility(View.VISIBLE);
        tv_loading.setText(!TextUtils.isEmpty(info) ?
                info : getResources().getString(R.string.text_loaded_All));
        shortToast(info);
    }

    @Override
    public boolean isAbleLoadmore() {

        if(isRefreshingData||isLoadingMoreData||isLoadedAllData){
            return false;
        }
        return true;
    }

    @Override
    public void onRefreshStart(String info) {
        isRefreshingData = true;
        isLoadedAllData = false;

        footerView.setVisibility(View.GONE);
        if (refreshLayout != null) {
            refreshLayout.setRefreshing(true);
        }
    }

    @Override
    public void onRefreshFailed(String info) {
        isRefreshingData = false;
        if (refreshLayout != null) {
            refreshLayout.setRefreshing(false);
        }
        shortToast(info);
    }

    @Override
    public void onRefreshSucced(String info) {
        isRefreshingData = false;
        if (refreshLayout != null) {
            refreshLayout.setRefreshing(false);
        }
        shortToast(info);
    }

    @Override
    public boolean isAbleRefresh() {
        if(isRefreshingData||isLoadingMoreData){
            return false;
        }
        return true;
    }
}
