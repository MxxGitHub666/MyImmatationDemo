package com.mxx.myimmatationdemo.di.component;

import com.mxx.myimmatationdemo.base.activity.Base2Activity;

import dagger.Subcomponent;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;

/**
 * @author quchao
 * @date 2018/5/3
 */

@Subcomponent(modules = {AndroidInjectionModule.class})
public interface BaseActivityComponent extends AndroidInjector<Base2Activity> {

    /**
     * 每一个继承于BaseActivity的Activity都继承于同一个子组件
     */
    @Subcomponent.Builder
    abstract class BaseBuilder extends Builder<Base2Activity>{

    }
}
