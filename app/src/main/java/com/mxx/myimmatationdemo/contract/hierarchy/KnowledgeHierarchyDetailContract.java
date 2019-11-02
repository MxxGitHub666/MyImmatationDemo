package com.mxx.myimmatationdemo.contract.hierarchy;

import com.mxx.myimmatationdemo.base.presenter.AbstractPresenter;
import com.mxx.myimmatationdemo.base.view.AbstractView;

public interface KnowledgeHierarchyDetailContract {
    interface View extends AbstractView{
        /**
         * Show switch project
         */
        void showSwitchProject();

        /**
         * Show switch navigation
         */
        void showSwitchNavigation();
    }

    interface Presenter extends AbstractPresenter<View> {


    }

}
