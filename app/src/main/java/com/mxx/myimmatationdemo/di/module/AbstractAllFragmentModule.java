package com.mxx.myimmatationdemo.di.module;


import com.mxx.myimmatationdemo.di.component.BaseFragmentComponent;
import com.mxx.myimmatationdemo.ui.hierarchy.fragment.KnowledgeHierarchyFragment;
import com.mxx.myimmatationdemo.ui.hierarchy.fragment.KnowledgeHierarchyListFragment;
import com.mxx.myimmatationdemo.ui.main.fragment.CollectFragment;
import com.mxx.myimmatationdemo.ui.main.fragment.SettingFragment;
import com.mxx.myimmatationdemo.ui.mainpager.fragment.MainPagerFragment;
import com.mxx.myimmatationdemo.ui.navigation.NavigationFragment;
import com.mxx.myimmatationdemo.ui.project.fragment.ProjectFragment;
import com.mxx.myimmatationdemo.ui.project.fragment.ProjectListFragment;
import com.mxx.myimmatationdemo.ui.wx.WxArticleFragment;
import com.mxx.myimmatationdemo.ui.wx.WxDetailArticleFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module(subcomponents = BaseFragmentComponent.class)
public abstract class AbstractAllFragmentModule {

    @ContributesAndroidInjector(modules = MainPagerFragmentModule.class)
    abstract MainPagerFragment contributesMainPagerFragmentInject();

    @ContributesAndroidInjector(modules = KnowledgeFragmentModule.class)
    abstract KnowledgeHierarchyFragment contributesKnowledgeHierarchyFragmentInject();
    @ContributesAndroidInjector(modules = WxArticleFragmentModule.class)
    abstract WxArticleFragment contributesWxArticleFragmentInject();
    @ContributesAndroidInjector(modules = NavigationFragmentModule.class)
    abstract NavigationFragment contributesNavigationFragmentInject();
    @ContributesAndroidInjector(modules = ProjectFragmentModule.class)
    abstract ProjectFragment contributesProjectFragmentInject();
    @ContributesAndroidInjector(modules = CollectFragmentModule.class)
    abstract CollectFragment contributesCollectFragmentInject();
    @ContributesAndroidInjector(modules = SettingFragmentModule.class)
    abstract SettingFragment contributesSettingFragmentInject();
    @ContributesAndroidInjector(modules = KnowledgeListFragmentModule.class)
    abstract KnowledgeHierarchyListFragment contributesKnowledgeHierarchyListFragmentInject();
    @ContributesAndroidInjector(modules = ProjectListFragmentModule.class)
    abstract ProjectListFragment contributesProjectListFragmentInject();
    @ContributesAndroidInjector(modules = WxDetailArticleFragmentModule.class)
    abstract WxDetailArticleFragment WxDetailArticleFragmentInject();




}
