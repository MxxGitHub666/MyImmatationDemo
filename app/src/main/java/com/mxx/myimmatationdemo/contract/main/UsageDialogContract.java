package com.mxx.myimmatationdemo.contract.main;


import com.mxx.myimmatationdemo.base.presenter.AbstractPresenter;
import com.mxx.myimmatationdemo.base.view.AbstractView;
import com.mxx.myimmatationdemo.core.bean.search.UsefulSiteData;

import java.util.List;

/**
 * @author quchao
 * @date 2018/4/2
 */

public interface UsageDialogContract {

    interface View extends AbstractView {

        /**
         * Show useful sites
         *
         * @param usefulSiteDataList List<UsefulSiteData>
         */
        void showUsefulSites(List<UsefulSiteData> usefulSiteDataList);
    }

    interface Presenter extends AbstractPresenter<View> {

        /**
         * 常用网站
         */
        void getUsefulSites();
    }

}
