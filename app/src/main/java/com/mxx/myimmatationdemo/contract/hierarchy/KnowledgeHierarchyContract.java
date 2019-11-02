package com.mxx.myimmatationdemo.contract.hierarchy;

import com.mxx.myimmatationdemo.base.presenter.AbstractPresenter;
import com.mxx.myimmatationdemo.base.view.AbstractView;
import com.mxx.myimmatationdemo.core.bean.hierarchy.KnowledgeHierarchyData;

import java.util.List;

public interface KnowledgeHierarchyContract {

    interface View extends AbstractView {

        /**
         * Show Knowledge Hierarchy Data
         *
         * @param knowledgeHierarchyDataList (List<KnowledgeHierarchyData>
         */
        void showKnowledgeHierarchyData(List<KnowledgeHierarchyData> knowledgeHierarchyDataList);

    }

    interface Presenter extends AbstractPresenter<View> {

        /**
         * 知识列表
         *
         * @param isShowError If show error
         */
        void getKnowledgeHierarchyData(boolean isShowError);
    }
}
