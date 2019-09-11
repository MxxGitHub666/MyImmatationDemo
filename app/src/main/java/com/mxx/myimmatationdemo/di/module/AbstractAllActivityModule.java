package com.mxx.myimmatationdemo.di.module;


import com.mxx.myimmatationdemo.di.component.BaseActivityComponent;
import com.mxx.myimmatationdemo.ui.main.activity.LoginActivity;
import com.mxx.myimmatationdemo.ui.main.activity.RegisterActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module(subcomponents = {BaseActivityComponent.class})
public interface AbstractAllActivityModule {

//    @ContributesAndroidInjector(modules = MainActivityModule.class)
//    abstract MainActivity contributesMainActivityInjector();

    @ContributesAndroidInjector(modules = LoginActivityModule.class)
    abstract LoginActivity contributesLoginActivityInjector();

    @ContributesAndroidInjector(modules = RegisterActivityModule.class)
    abstract RegisterActivity contributesRegisterActivityInjector();
}
