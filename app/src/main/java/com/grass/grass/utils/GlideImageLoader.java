package com.grass.grass.utils;

import android.app.Activity;
import android.widget.ImageView;

import com.lzy.imagepicker.loader.ImageLoader;

public class GlideImageLoader implements ImageLoader {

    @Override
    public void displayImage(Activity activity, String path, ImageView imageView, int width, int height) {

        ImageLoadUtils.getInstance().loadLocaltionPic(path,imageView);

    }

    @Override
    public void displayImagePreview(Activity activity, String path, ImageView imageView, int width, int height) {
        ImageLoadUtils.getInstance().loadLocaltionPic(path,imageView);
    }

    @Override
    public void clearMemoryCache() {
    }
}
