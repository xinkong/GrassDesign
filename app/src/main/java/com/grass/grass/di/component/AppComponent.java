package com.grass.grass.di.component;


import com.grass.grass.app.BaseApplication;
import com.grass.grass.di.module.AppModule;
import com.grass.grass.di.module.HttpModule;
import com.grass.grass.utils.http.HttpUrlManager;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;

/**
 * Created by codeest on 16/8/7.
 */

@Singleton
@Component(modules = {AppModule.class, HttpModule.class})
public interface AppComponent {
    BaseApplication getContext();  // 提供App的Context

    //在httpModule中提供出来访问实例
    HttpUrlManager getHttpUrlManager();

    //提供OkHttpClient供外部访问
    OkHttpClient getOkHttpClient();
}
