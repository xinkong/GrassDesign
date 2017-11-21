
package com.grass.grass.ui.adapter.base;

import android.support.annotation.IdRes;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.grass.grass.utils.ImageLoadUtils;

public class BaseViewHolder extends RecyclerView.ViewHolder {

    private final SparseArray<View> views;

    public BaseViewHolder(final View view) {
        super(view);
        this.views = new SparseArray<>();
    }

    @SuppressWarnings("unchecked")
    public <T extends View> T getView(@IdRes int viewId) {
        View view = views.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            views.put(viewId, view);
        }
        return (T) view;
    }

    //设置textView文字
    public void setText(int viewId,String text){
        ((TextView)getView(viewId)).setText(text);
    }

    //设置图片
    public void setImageResource(int viewId,String url){
        ImageView imageView = getView(viewId);
        ImageLoadUtils.getInstance().loadImage(url,imageView);
    }


}
