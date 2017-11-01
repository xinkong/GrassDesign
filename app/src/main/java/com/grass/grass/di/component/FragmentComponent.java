package com.grass.grass.di.component;

import android.app.Activity;


import com.grass.grass.di.module.FragmentModule;
import com.grass.grass.di.scope.FragmentScope;
import com.grass.grass.ui.MainActivity;
import com.grass.grass.ui.home.HomeFragment;
import com.grass.grass.ui.my.MyInfoFragment;

import dagger.Component;

/**
 * Created by codeest on 16/8/7.
 */

@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {

    Activity getActivity();

    void inject(HomeFragment homeFragment);

    void inject(MyInfoFragment infoFragment);
}
