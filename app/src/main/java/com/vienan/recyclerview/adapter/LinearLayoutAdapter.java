package com.vienan.recyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vienan.baseNiceRecyclerViewAdapter.BaseNiceRecyclerViewAdapter;
import com.vienan.recyclerview.R;
import com.vienan.recyclerview.model.FooterObject;
import com.vienan.recyclerview.model.HeaderObject;
import com.vienan.recyclerview.model.ItemObject;

/**
 * Created by vienan on 16/1/27.
 */
public class LinearLayoutAdapter extends BaseNiceRecyclerViewAdapter<RecyclerView.ViewHolder, HeaderObject, ItemObject, FooterObject> {

    private Context context;
    private LayoutInflater inflater;
    private View footerView;
    public LinearLayoutAdapter(Context context, View footerView) {
        this.context=context;
        this.footerView=footerView;
        this.inflater=LayoutInflater.from(context);

    }


    @Override
    protected RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent, int viewType) {
        View headerView=inflater.inflate(R.layout.layout_header,parent,false);
        return new ViewHolderHeader(headerView);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateItemViewHolder(ViewGroup parent, int viewType) {
        View itemView=inflater.inflate(R.layout.layout_item,parent,false);
        return new ViewHolderItem(itemView);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateFooterViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolderFooter(footerView);
    }

    @Override
    protected void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {
        HeaderObject headerObject=getHeader();
        ViewHolderHeader holderHeader= (ViewHolderHeader) holder;
        holderHeader.render(headerObject);
    }

    @Override
    protected void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
        ItemObject itemObject=getItem(position);
        ViewHolderItem holderItem= (ViewHolderItem) holder;
        holderItem.render(itemObject);
    }

    @Override
    protected void onBindFooterViewHolder(RecyclerView.ViewHolder holder, int position) {

        FooterObject footerObject=getFooter();
        ViewHolderFooter holderFooter= (ViewHolderFooter) holder;
        holderFooter.render(footerObject);

    }

    static class ViewHolderHeader extends RecyclerView.ViewHolder{

        public ViewHolderHeader(View itemView) {
            super(itemView);
        }

        //do what you want
        public void render(HeaderObject headerObject){

        }
    }

    static class ViewHolderItem extends RecyclerView.ViewHolder{

        TextView tvItem;
        public ViewHolderItem(View itemView) {
            super(itemView);
            tvItem= (TextView) itemView.findViewById(R.id.tv_item);
        }
        //do what you want
        public void render(ItemObject itemObject){
            if (itemObject==null) return;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //onclick
                }
            });
            tvItem.setText(itemObject.getItemText());

        }
    }

    static class ViewHolderFooter extends RecyclerView.ViewHolder{

        public ViewHolderFooter(View itemView) {
            super(itemView);
            initView();
        }

        private void initView() {
            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.gravity= Gravity.CENTER;
            itemView.setLayoutParams(params);
        }

        //do what you want
        public void render(FooterObject footerObject){

        }
    }
}
