package com.vienan.baseNiceRecyclerViewAdapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

/**
 * Created by vienan on 16/1/27.
 */
public abstract class BaseNiceRecyclerViewAdapter<VH extends RecyclerView.ViewHolder, H, T, F>
        extends RecyclerView.Adapter<VH> {

    public static final int TYPE_HEADER = -2;
    public static final int TYPE_ITEM = -1;
    public static final int TYPE_FOOTER = -3;
    private H header;
    private List<T> items = Collections.EMPTY_LIST;
    private F footer;


    @Override
    public int getItemCount() {
        int addSize=hasHeader()?2:1;
        return items == null ? addSize : items.size() + addSize;
    }

    @Override
    public int getItemViewType(int position) {
        int viewType = TYPE_ITEM;
        if (isHeaderPosition(position)) {
            return TYPE_HEADER;
        } else if (isFooterPosition(position)) {
            return TYPE_FOOTER;
        }
        return viewType;
    }

    protected boolean isHeaderPosition(int position) {
        return hasHeader() && position == 0;
    }

    protected boolean isFooterPosition(int position) {
        return items == null || position == getItemCount()-1;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_HEADER:
                return onCreateHeaderViewHolder(parent, viewType);
            case TYPE_ITEM:
                return onCreateItemViewHolder(parent, viewType);
            case TYPE_FOOTER:
                return onCreateFooterViewHolder(parent, viewType);
        }
        throw new RuntimeException("there is no type that matches the type " + viewType + " + make sure your using types correctly");
    }


    @Override
    public void onBindViewHolder(VH holder, int position) {
        if (isHeaderPosition(position)) {
            onBindHeaderViewHolder(holder, position);
        } else if (isFooterPosition(position)) {
            onBindFooterViewHolder(holder, position);
        } else {
            onBindItemViewHolder(holder, position);
        }
    }

    public boolean hasHeader() {
        return getHeader() != null;
    }

    public H getHeader() {
        return header;
    }

    public void setHeader(H header) {
        this.header = header;
    }

    public T getItem(int position) {
        if (hasHeader() && hasItems()) {
            --position;
        }
        return items.get(position);
    }

    private boolean hasItems() {
        return items.size() > 0;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public F getFooter() {
        return footer;
    }

    public void setFooter(F footer) {
        this.footer = footer;
    }

    protected abstract VH onCreateHeaderViewHolder(ViewGroup parent, int viewType);

    protected abstract VH onCreateItemViewHolder(ViewGroup parent, int viewType);

    protected abstract VH onCreateFooterViewHolder(ViewGroup parent, int viewType);

    protected abstract void onBindHeaderViewHolder(VH holder, int position);

    protected abstract void onBindItemViewHolder(VH holder, int position);

    protected abstract void onBindFooterViewHolder(VH holder, int position);
}
