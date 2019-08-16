package com.mxx.myimmatationdemo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;


import com.gyf.immersionbar.ImmersionBar;
import com.mxx.myimmatationdemo.R;

/**
 * Created by 98179 on 2019/6/18.
 */

public abstract class BaseActivity extends AppCompatActivity{


    protected Activity mActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
        setContentView(getLayoutId());
        //初始化沉浸式
        initImmersionBar();
        //初始化数据
        initData();
        //view与数据绑定
        initView();
        //设置监听
        setListener();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    /**
     //     * 子类设置布局Id
     //     *
     //     * @return the layout id
     //     */
   protected abstract int getLayoutId();

    /**
     * 初始化沉浸式
     * Init immersion bar.
     */
    protected void initImmersionBar() {
        //设置共同沉浸式样式
        ImmersionBar.with(this).navigationBarColor(R.color.colorPrimary).init();
    }

    protected void initData() {
    }

    protected void initView() {
    }

    protected void setListener() {
    }


}
