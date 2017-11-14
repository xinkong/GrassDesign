package com.grass.grass.utils.glide;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
import com.bumptech.glide.load.model.GlideUrl;
import com.grass.grass.app.Constants;

import java.io.File;
import java.io.InputStream;

public class OkHttpGlideModule implements com.bumptech.glide.module.GlideModule {
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        // Do nothing.
        //设置图片的显示格式ARGB_565(指图片大小为32bit)
//        builder.setDecodeFormat(DecodeFormat.PREFER_RGB_565);
        //设置磁盘缓存目录（和创建的缓存目录相同）
//        File storageDirectory = Environment.getExternalStorageDirectory();
        String downloadDirectoryPath = Constants.PATH_CACHE + "/GlideCache";
        File file = new File(downloadDirectoryPath);
        if(!file.exists()){
            file.mkdirs();
        }
//        设置缓存的大小为200M
        int cacheSize = 200 * 1000 * 1000;
        builder.setDiskCache(new DiskLruCacheFactory(downloadDirectoryPath, cacheSize));


    }

    @Override
    public void registerComponents(Context context, Glide glide, Registry registry) {
        registry.replace(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory());
    }

}