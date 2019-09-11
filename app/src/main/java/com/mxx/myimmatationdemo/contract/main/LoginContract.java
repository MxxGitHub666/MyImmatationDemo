package com.mxx.myimmatationdemo.contract.main;

import com.mxx.myimmatationdemo.base.presenter.AbstractPresenter;
import com.mxx.myimmatationdemo.base.view.AbstractView;

public interface LoginContract {

    interface View extends AbstractView {

        /**
         * Show login data
         *
         */
        void showLoginSuccess();
    }

    interface Presenter extends AbstractPresenter<View> {

        /**
         * Get Login data
         *
         * @param username user name
         * @param password password
         */
        void getLoginData(String username, String password);
    }
}
