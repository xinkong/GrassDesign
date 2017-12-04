package com.grass.grass.di.component;

import android.app.Activity;


import com.grass.grass.di.module.ActivityModule;
import com.grass.grass.di.scope.ActivityScope;
import com.grass.grass.ui.home.AddMsgActivity;
import com.grass.grass.ui.login.LoginActivity;
import com.grass.grass.ui.MainActivity;
import com.grass.grass.ui.login.RegisterActivity;
import com.grass.grass.ui.my.SettingActivity;

import dagger.Component;

/**
 * Created by codeest on 16/8/7.
 */

@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    Activity getActivity();

    void inject(MainActivity themeActivity);

    void inject(LoginActivity loginActivity);

    void inject(RegisterActivity loginActivity);

    void inject(AddMsgActivity addMsgActivity);

    void inject(SettingActivity settingActivity);
}
