package com.mxx.myimmatationdemo.base.activity;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;

import com.mxx.myimmatationdemo.component.ActivityCollector;
import com.mxx.myimmatationdemo.test.EspressoIdlingResource;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportActivity;

/**
 * Created by 98179 on 2019/9/7.
 */

public abstract class AbstractSimpleActivity extends SupportActivity {

    private Unbinder unBinder;
    protected AbstractSimpleActivity mActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        unBinder = ButterKnife.bind(this);
        mActivity = this;
        ActivityCollector.getInstance().addActivity(this);
        onViewCreated();
        initToolbar();
        initEventAndData();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.getInstance().removeActivity(this);
        if(unBinder!=null && unBinder != Unbinder.EMPTY){
            unBinder.unbind();
            unBinder= null;
        }
    }

    @VisibleForTesting
    public IdlingResource getCountingIdlingResource() {
        return EspressoIdlingResource.getIdlingResource();
    }

    /**
     * 在initEventAndData()之前执行
     */
    protected abstract void onViewCreated();

    /**
     * 获取当前Activity的UI布局
     *
     * @return 布局id
     */
    protected abstract int getLayoutId();

    /**
     * 初始化ToolBar
     */
    protected abstract void initToolbar();

    /**
     * 初始化数据
     */
    protected abstract void initEventAndData();
}
