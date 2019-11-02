package com.mxx.myimmatationdemo.di.module;


import com.mxx.myimmatationdemo.di.component.BaseActivityComponent;
import com.mxx.myimmatationdemo.ui.hierarchy.activity.KnowledgeHierarchyDetailActivity;
import com.mxx.myimmatationdemo.ui.main.activity.AboutUsActivity;
import com.mxx.myimmatationdemo.ui.main.activity.ArticleDetailActivity;
import com.mxx.myimmatationdemo.ui.main.activity.LoginActivity;
import com.mxx.myimmatationdemo.ui.main.activity.NewMainActivity;
import com.mxx.myimmatationdemo.ui.main.activity.RegisterActivity;
import com.mxx.myimmatationdemo.ui.main.activity.SearchListActivity;
import com.mxx.myimmatationdemo.ui.main.activity.SplashActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module(subcomponents = {BaseActivityComponent.class})
public interface AbstractAllActivityModule {

    @ContributesAndroidInjector(modules = NewMainActivityModule.class)
    abstract NewMainActivity contributesMainActivityInjector();

    @ContributesAndroidInjector(modules = LoginActivityModule.class)
    abstract LoginActivity contributesLoginActivityInjector();

    @ContributesAndroidInjector(modules = RegisterActivityModule.class)
    abstract RegisterActivity contributesRegisterActivityInjector();


    @ContributesAndroidInjector(modules = SplashActivityModule.class)
    abstract SplashActivity contributesSplashActivityInjector();

    @ContributesAndroidInjector(modules = KnowledgeHierarchyDetailActivityModule.class)
    abstract KnowledgeHierarchyDetailActivity contributesKnowledgeHierarchyDetailActivityInjector();

    @ContributesAndroidInjector(modules = ArticleDetailActivityModule.class)
    abstract ArticleDetailActivity contributesArticleDetailActivityInjector();

    @ContributesAndroidInjector(modules = AboutUsActivityModule.class)
    abstract AboutUsActivity contributesAboutUsActivityInjector();

    @ContributesAndroidInjector(modules = SearchListActivityModule.class)
    abstract SearchListActivity contributesSearchListActivityInjector();

}
