package com.mxx.myimmatationdemo.base.presenter;

import com.mxx.myimmatationdemo.base.view.AbstractView;
import com.mxx.myimmatationdemo.core.DataManager;

import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.CompositeDisposable;
/**
 * Created by 98179 on 2019/9/6.
 */

public class BasePresenter<T extends AbstractView> implements AbstractPresenter<T>{

    protected T mView;
    private CompositeDisposable compositeDisposable;
    private DataManager mDataManager;

    public BasePresenter(DataManager dataManager) {
        this.mDataManager = dataManager;
    }


    @Override
    public void attachView(T view) {
        this.mView = view;
    }

    @Override
    public void detachView() {
        this.mView = null;
        if (compositeDisposable != null) {
            compositeDisposable.clear();
        }
    }

    @Override
    public void addRxBindingSubscribe(Disposable disposable) {
        addSubscribe(disposable);
    }

    @Override
    public boolean getNightModeState() {
        return false;
    }

    @Override
    public void setLoginStatus(boolean loginStatus) {

    }

    @Override
    public boolean getLoginStatus() {
        return false;
    }

    @Override
    public String getLoginAccount() {
        return null;
    }

    @Override
    public void setLoginAccount(String account) {

    }

    @Override
    public void setLoginPassword(String password) {

    }

    @Override
    public int getCurrentPage() {
        return 0;
    }

    protected void addSubscribe(Disposable disposable){
        if(compositeDisposable ==null){
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(disposable);
    }
}
