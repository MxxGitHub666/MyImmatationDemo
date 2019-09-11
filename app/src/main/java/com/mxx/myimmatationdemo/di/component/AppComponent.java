package com.mxx.myimmatationdemo.di.component;

import com.mxx.myimmatationdemo.app.MyApp;
import com.mxx.myimmatationdemo.core.DataManager;
import com.mxx.myimmatationdemo.di.module.AbstractAllActivityModule;
import com.mxx.myimmatationdemo.di.module.AppModule;
import com.mxx.myimmatationdemo.di.module.HttpModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.support.AndroidSupportInjectionModule;


@Singleton
@Component(modules = {AndroidInjectionModule.class,
        AndroidSupportInjectionModule.class,
        AbstractAllActivityModule.class,
        AppModule.class,
        HttpModule.class})
public interface AppComponent {

    /**
     * 注入MyApp实例
     *
     * @param myApp MyApp
     */
    void inject(MyApp myApp);

    /**
     * 提供App的Context
     *
     * @return GeeksApp context
     */
    MyApp getContext();

    /**
     * 数据中心
     *
     * @return DataManager
     */
    DataManager getDataManager();

}
