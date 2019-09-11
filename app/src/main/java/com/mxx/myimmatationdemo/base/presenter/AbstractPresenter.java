package com.mxx.myimmatationdemo.base.presenter;

import com.mxx.myimmatationdemo.base.view.AbstractView;

import io.reactivex.disposables.Disposable;

/**
 * Presenter 基类
 *
 * Created by 98179 on 2019/9/6.
 */

public interface AbstractPresenter<T extends AbstractView> {
    /**
     * 注入View
     *
     * @param view view
     */
    void attachView(T view);

    /**
     * 回收View
     */
    void detachView();

    /**
     * Add rxBing subscribe manager
     *
     * @param disposable Disposable
     */
    void addRxBindingSubscribe(Disposable disposable);

    /**
     * Get night mode state
     *
     * @return if is night mode
     */
    boolean getNightModeState();

    /**
     * Set login status
     *
     * @param loginStatus login status
     */
    void setLoginStatus(boolean loginStatus);

    /**
     * Get login status
     *
     * @return if is login status
     */
    boolean getLoginStatus();

    /**
     * Get login account
     *
     * @return login account
     */
    String getLoginAccount();

    /**
     * Set login status
     *
     * @param account account
     */
    void setLoginAccount(String account);

    /**
     * Set login password
     *
     * @param password password
     */
    void setLoginPassword(String password);

    /**
     * Get current page
     *
     * @return current page
     */
    int getCurrentPage();

}
