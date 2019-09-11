package com.mxx.myimmatationdemo.contract.main;

import com.mxx.myimmatationdemo.base.presenter.AbstractPresenter;
import com.mxx.myimmatationdemo.base.view.AbstractView;

/**
 * Created by 98179 on 2019/9/6.
 */

public interface RegisterContract {

    interface  View extends AbstractView {

        /**
         * Show register data
         */
        void showRegisterSuccess();

    }


    interface Presenter extends AbstractPresenter<View> {

        /**
         * 注册
         * http://www.wanandroid.com/user/register
         *
         * @param username user name
         * @param password password
         * @param rePassword re password
         */
        void getRegisterData(String username, String password, String rePassword);
    }

}
