package com.mxx.myimmatationdemo.di.module;

import com.mxx.myimmatationdemo.app.MyApp;
import com.mxx.myimmatationdemo.core.DataManager;
import com.mxx.myimmatationdemo.core.db.DbHelper;
import com.mxx.myimmatationdemo.core.db.DbHelperImpl;
import com.mxx.myimmatationdemo.core.http.HttpHelper;
import com.mxx.myimmatationdemo.core.http.HttpHelperImpl;
import com.mxx.myimmatationdemo.core.prefs.PreferenceHelper;
import com.mxx.myimmatationdemo.core.prefs.PreferenceHelperImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class AppModule {
    private final MyApp application;

    public AppModule(MyApp instance) {
        this.application = instance;
    }

    @Provides
    @Singleton
    MyApp provideApplicationContext(){
        return application;
    }

    @Provides
    @Singleton
    HttpHelper provideHttpHelper(HttpHelperImpl httpHelperImpl) {
        return httpHelperImpl;
    }

    @Provides
    @Singleton
    DbHelper provideDBHelper(DbHelperImpl realmHelper) {
        return realmHelper;
    }

    @Provides
    @Singleton
    PreferenceHelper providePreferencesHelper(PreferenceHelperImpl implPreferencesHelper) {
        return implPreferencesHelper;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(HttpHelper httpHelper, DbHelper dbhelper, PreferenceHelper preferencesHelper) {
        return new DataManager(httpHelper, dbhelper, preferencesHelper);
    }


}
