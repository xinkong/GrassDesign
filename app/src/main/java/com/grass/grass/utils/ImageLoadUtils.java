package com.grass.grass.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.grass.grass.R;
import com.grass.grass.utils.glide.GlideApp;
import com.grass.grass.utils.glide.GlideRoundTransform;

import io.reactivex.Flowable;

/**
 * Created by huchao on 2017/9/25.
 */

public class ImageLoadUtils {

    private static ImageLoadUtils instance;

    public static ImageLoadUtils getInstance() {
        if (instance == null) {
            synchronized (ImageLoadUtils.class) {
                if (instance == null) {
                    instance = new ImageLoadUtils();
                }
            }
        }
        return instance;
    }


    /**
     * 加载网络图片
     * @param url
     * @param imageView
     */
    public void loadImage(String url, ImageView imageView) {
        if (checkActivityIsDestroyed(imageView)){
            return;
        }
        GlideApp.with(imageView.getContext()).load(url)
                .placeholder(R.mipmap.default_img)
                .error(R.mipmap.default_img)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);

    }

    /**
     * 加载网络图片
     * @param url
     * @param imageView
     */
    public void loadImage(String url, ImageView imageView, final ImageLoaderListener listener) {
        if (checkActivityIsDestroyed(imageView)){
            return;
        }
        listener.startDown();
        GlideApp.with(imageView.getContext()).load(url)
                .placeholder(R.mipmap.default_img)
                .error(R.mipmap.default_img)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        listener.downError();
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        listener.downFinish();
                        return false;

                    }
                })
                .into(imageView);

    }

    private boolean checkActivityIsDestroyed(ImageView imageView) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            Context context = imageView.getContext();
            if(context instanceof Activity && ((Activity)context).isDestroyed()){
                return true;
            }
        }
        return false;
    }

    /**
     * 加载本地图片
     * @param id
     * @param imageView
     */
    public void loadImage(int id, ImageView imageView) {
        GlideApp.with(imageView.getContext()).load(id)
                .placeholder(R.mipmap.default_img)
                .error(R.mipmap.default_img)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);

    }

    /**
     * 加载网络图片
     *
     * @param context
     * @param url
     * @param imageView
     */
//    public void loadImage(Context context, String url, ImageView imageView) {
//        if (checkActivityIsDestroyed(imageView)){
//            return;
//        }
//        GlideApp.with(context).load(url)
//                .placeholder(R.mipmap.default_img)
//                .error(R.mipmap.default_img)
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .into(imageView);
//
//    }

    /**
     * 加载圆形图片
     *
     * @param url
     * @param imageView
     */
    public void loadCircleImage(String url, ImageView imageView) {
        if (checkActivityIsDestroyed(imageView)){
            return;
        }
        GlideApp.with(imageView.getContext()).load(url)
                .placeholder(R.mipmap.default_img)
                .error(R.mipmap.default_img)
                .apply(RequestOptions.circleCropTransform())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }

    /**
     * 加载圆角图片
     *
     * @param context
     * @param url
     * @param dp
     * @param imageView
     */
    public void loadRoundedImage(Context context, String url, int dp, ImageView imageView) {
        if (checkActivityIsDestroyed(imageView)){
            return;
        }
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .priority(Priority.HIGH)
                .transform(new GlideRoundTransform(dp));
        GlideApp.with(context)
                .load(url)
                .placeholder(R.mipmap.default_img)
                .error(R.mipmap.default_img)
                .apply(options)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }
}
