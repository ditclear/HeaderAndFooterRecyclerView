package com.vienan.recyclerview.ui;

import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.vienan.recyclerview.R;
import com.vienan.recyclerview.adapter.StaggeredGridLayoutAdapter;
import com.vienan.recyclerview.model.HeaderObject;
import com.vienan.recyclerview.model.ItemObject;
import com.vienan.recyclerview.ui.base.BasePtrBtlActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by vienan on 16/1/28.
 */
public class StaggeredGridLayoutActivity extends BasePtrBtlActivity {


    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    StaggeredGridLayoutAdapter adapter;
    List<ItemObject> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initToolbar(R.id.toolbar, R.string.StaggeredGridLayout);

        initRefreshView(R.id.swipe_refresh_layout);

        setupRecyclerView();


    }

    private void setupRecyclerView() {
        if (itemList == null) {
            itemList = createItemList();
        }
        adapter=new StaggeredGridLayoutAdapter(this,footerView);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        SpacesItemDecoration decoration = new SpacesItemDecoration((int) getResources().getDimension(R.dimen.item_decoration));

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(decoration);
        adapter.setHeader(new HeaderObject("header"));//header can be null
        adapter.setItems(itemList);

        recyclerView.setAdapter(adapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    StaggeredGridLayoutManager manager = (StaggeredGridLayoutManager) recyclerView.getLayoutManager();
                    int[] lastVisibleItem = manager.findLastCompletelyVisibleItemPositions(null);
                    int spanCount=manager.getSpanCount();
                    int totalItemCount = manager.getItemCount();
                    if (lastVisibleItem[spanCount-1] == (totalItemCount - 1)) {
                        loadMore();

                    }
                }
            }
        });

    }

    /**
     *load more data
     */
    private void loadMore() {
        if (isAbleLoadmore()) {
            onLoadmoreStart(null);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    addLoadItem();
                }
            }, 2000);
        }

    }

    private void addLoadItem() {
        if (itemList != null) {
            itemList.add(new ItemObject("loaded one"));
            itemList.add(new ItemObject("loaded two"));
            itemList.add(new ItemObject("loaded three"));
            itemList.add(new ItemObject("loaded four"));
            onLoadmoreSucced("load succeed");
            adapter.notifyItemRangeInserted(itemList.size() - 1, 4);
            return;
        }
        onLoadmoreFail("load failed");
    }

    @Override
    protected void refreshLayout() {

        if (isAbleRefresh()) {
            onRefreshStart(null);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    addRefreshItem();

                }
            }, 2000);

        }
    }

    private void addRefreshItem() {

        if (itemList != null) {
            itemList.add(0, new ItemObject("refreshed two"));
            itemList.add(0, new ItemObject("refreshed one"));
            int insertPos = adapter.hasHeader() ? 1 : 0;
            adapter.notifyItemRangeInserted(insertPos, 2);
            if (!adapter.hasHeader()) {
                recyclerView.smoothScrollToPosition(0);
            }
            onRefreshSucced("refresh succeed");
            return;
        }
        onRefreshFailed("refresh failed");

    }

    private List<ItemObject> createItemList() {
        List<ItemObject> itemObjects = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            itemObjects.add(new ItemObject("Item " + i));
        }
        return itemObjects;
    }

    static public class SpacesItemDecoration extends RecyclerView.ItemDecoration {

        private int space;

        public SpacesItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);

            outRect.bottom = space * 2;
        }
    }
}
