package com.mxx.myimmatationdemo.contract.navigation;


import com.mxx.myimmatationdemo.base.presenter.AbstractPresenter;
import com.mxx.myimmatationdemo.base.view.AbstractView;
import com.mxx.myimmatationdemo.core.bean.navigation.NavigationListData;

import java.util.List;

/**
 * @author quchao
 * @date 2018/2/11
 */

public interface NavigationContract {

    interface View extends AbstractView {

        /**
         * Show navigation list data
         *
         * @param navigationDataList List<NavigationListData>
         */
        void showNavigationListData(List<NavigationListData> navigationDataList);

    }

    interface Presenter extends AbstractPresenter<View> {

        /**
         * Get navigation list data
         *
         * @param isShowError If show error
         */
        void getNavigationListData(boolean isShowError);
    }

}
