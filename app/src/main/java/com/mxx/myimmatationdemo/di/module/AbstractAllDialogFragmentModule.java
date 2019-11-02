package com.mxx.myimmatationdemo.di.module;

import com.mxx.myimmatationdemo.di.component.BaseDialogFragmentComponent;
import com.mxx.myimmatationdemo.ui.main.fragment.SearchDialogFragment;
import com.mxx.myimmatationdemo.ui.main.fragment.UsageDialogFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
@Module(subcomponents = BaseDialogFragmentComponent.class)
public abstract class AbstractAllDialogFragmentModule {

    @ContributesAndroidInjector(modules = SearchDialogFragmentModule.class)
    abstract SearchDialogFragment contributesSearchDialogFragmentInject();

    @ContributesAndroidInjector(modules = UsageDialogFragmentModule.class)
    abstract UsageDialogFragment contributesUsageDialogFragmentInject();


}
