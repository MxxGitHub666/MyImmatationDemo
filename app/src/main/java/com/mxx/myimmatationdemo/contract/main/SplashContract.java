package com.mxx.myimmatationdemo.contract.main;

import com.mxx.myimmatationdemo.base.presenter.AbstractPresenter;
import com.mxx.myimmatationdemo.base.view.AbstractView;

public interface SplashContract {
    interface View extends AbstractView {
        /**
         * after some time jump to main page
         */
        void jumpToMain();
    }

    interface Presenter extends AbstractPresenter<View> {

    }

}
