package com.mxx.myimmatationdemo.base.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatDelegate;

import com.mxx.myimmatationdemo.base.presenter.AbstractPresenter;
import com.mxx.myimmatationdemo.base.view.AbstractView;
import com.mxx.myimmatationdemo.utils.CommonUtils;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

/**
 * Created by 98179 on 2019/9/7.
 */

public class Base2Activity<T extends AbstractPresenter> extends AbstractSimpleActivity implements HasSupportFragmentInjector,AbstractView {


    @Inject
    DispatchingAndroidInjector<Fragment> mFragmentDispatchingAndroidInjector;
    @Inject
    protected T mPresenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onViewCreated() {
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter = null;
        }
        super.onDestroy();
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void initToolbar() {

    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    public void useNightMode(boolean isNightMode) {
        if(isNightMode){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        recreate();
    }

    @Override
    public void showErrorMsg(String errorMsg) {
        CommonUtils.showSnackMessage(this,errorMsg);
    }

    @Override
    public void showNormal() {

    }

    @Override
    public void showError() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void reload() {

    }

    @Override
    public void showLoginView() {

    }

    @Override
    public void showLogoutView() {

    }

    @Override
    public void showCollectSuccess() {

    }

    @Override
    public void showCancelCollectSuccess() {

    }

    @Override
    public void showToast(String message) {
        CommonUtils.showMessage(this,message);
    }

    @Override
    public void showSnackBar(String message) {
        CommonUtils.showSnackMessage(this,message);
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return mFragmentDispatchingAndroidInjector;
    }
}
