package com.grass.grass.ui.adapter.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huchao on 2017/11/16.
 */

public abstract class BaseQuickAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {

    private List<T> mDatas;
    protected Context mContext;
    private LayoutInflater mLayoutInflater;
    private int mItemLayoutIds;

    public BaseQuickAdapter(Context context,int itemIds) {
        mDatas = new ArrayList<>();
        this.mContext = context;
        this.mLayoutInflater = LayoutInflater.from(mContext);
        this.mItemLayoutIds = itemIds;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BaseViewHolder(mLayoutInflater.inflate(mItemLayoutIds, parent,false));
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        T t = mDatas.get(position);
        holder.itemView.setOnClickListener(view -> {
            if(this.listener!=null){
                listener.onItemClick(position);
            }
        });
        convert(holder,t,position);
    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    public abstract void convert(BaseViewHolder viewHolder, T data, int position);

    public void addAll(List<T> datas){
        this.mDatas.addAll(datas);
        notifyDataSetChanged();
    }

    public void add(T data){
        this.mDatas.add(data);
        notifyDataSetChanged();
    }

    public T getData(int position){
        return mDatas.get(position);
    }

    public void removeItem(int position){
        this.mDatas.remove(position);
        notifyDataSetChanged();
    }

    public void clear(){
        this.mDatas.clear();
    }


    private  OnRecylyViewItemClickListener listener;
    public void setOnRecylyViewItemClickListener(OnRecylyViewItemClickListener listener){
        this.listener = listener;
    }
    public interface OnRecylyViewItemClickListener {
        void onItemClick(int position);
    }

}
