package com.grass.grass.di.module;


import android.app.Application;

import com.grass.grass.app.BaseApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by codeest on 16/8/7.
 */

@Module
public class AppModule {
    private final BaseApplication application;

    public AppModule(BaseApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    BaseApplication provideApplicationContext() {
        return application;
    }

}
