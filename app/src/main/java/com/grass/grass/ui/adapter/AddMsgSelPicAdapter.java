package com.grass.grass.ui.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import com.grass.grass.R;
import com.grass.grass.ui.adapter.base.BaseQuickAdapter;
import com.grass.grass.ui.adapter.base.BaseViewHolder;
import com.grass.grass.utils.ImageLoadUtils;

import java.util.List;

/**
 * Created by huchao on 2017/11/22.
 */

public class AddMsgSelPicAdapter extends BaseQuickAdapter<String> {

    private int mMaxImgCount;

    public static final String defaultPic = "default_add_pic";


    public AddMsgSelPicAdapter(Context context, int itemIds, int maxImgCount) {
        super(context, itemIds);
        this.mMaxImgCount = maxImgCount;
    }

    public void setSelImages(List<String> selPath) {
        clear();
        addAll(selPath);
        if (selPath.size() < mMaxImgCount) {
            add(defaultPic);
        }
    }


    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BaseViewHolder baseViewHolder = super.onCreateViewHolder(parent, viewType);
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        ViewGroup.LayoutParams layoutParams = baseViewHolder.itemView.getLayoutParams();
        layoutParams.height = width / 3;
        layoutParams.width = width / 3;
        baseViewHolder.itemView.setLayoutParams(layoutParams);
        return baseViewHolder;
    }

    @Override
    public void convert(BaseViewHolder viewHolder, String data, int position) {

        ImageView iv = viewHolder.getView(R.id.sel_pic);

        if (defaultPic.equals(data)) {
            iv.setImageResource(R.mipmap.msg_pic_add);
        } else {
            ImageLoadUtils.getInstance().loadLocaltionPic(data, iv);
        }

    }
}
