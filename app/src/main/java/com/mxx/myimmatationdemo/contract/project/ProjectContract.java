package com.mxx.myimmatationdemo.contract.project;


import com.mxx.myimmatationdemo.base.presenter.AbstractPresenter;
import com.mxx.myimmatationdemo.base.view.AbstractView;
import com.mxx.myimmatationdemo.core.bean.project.ProjectClassifyData;

import java.util.List;

/**
 * @author quchao
 * @date 2018/2/11
 */

public interface ProjectContract {

    interface View extends AbstractView {

        /**
         * Show project classify data
         *
         * @param projectClassifyDataList List<ProjectClassifyData>
         */
        void showProjectClassifyData(List<ProjectClassifyData> projectClassifyDataList);

    }

    interface Presenter extends AbstractPresenter<View> {

        /**
         * Get project classify data
         */
        void getProjectClassifyData();

        /**
         * Get project current page
         *
         * @return project current page
         */
        int getProjectCurrentPage();

        /**
         * Set project current page
         */
        void setProjectCurrentPage(int page);


    }

}
