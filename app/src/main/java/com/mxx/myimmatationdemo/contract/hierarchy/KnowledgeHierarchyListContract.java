package com.mxx.myimmatationdemo.contract.hierarchy;

import com.mxx.myimmatationdemo.base.presenter.AbstractPresenter;
import com.mxx.myimmatationdemo.base.view.AbstractView;
import com.mxx.myimmatationdemo.core.bean.main.collect.FeedArticleData;
import com.mxx.myimmatationdemo.core.bean.main.collect.FeedArticleListData;

public interface KnowledgeHierarchyListContract {

    interface View extends AbstractView{
        /**
         * Show Knowledge Hierarchy Detail Data
         *
         * @param feedArticleListData FeedArticleListData
         */
        void showKnowledgeHierarchyDetailData(FeedArticleListData feedArticleListData);

        /**
         * Show collect article data
         *
         * @param position Position
         * @param feedArticleData FeedArticleData
         * @param feedArticleListData FeedArticleListData
         */
        void showCollectArticleData(int position, FeedArticleData feedArticleData, FeedArticleListData feedArticleListData);

        /**
         * Show cancel collect article data
         *
         * @param position Position
         * @param feedArticleData FeedArticleData
         * @param feedArticleListData FeedArticleListData
         */
        void showCancelCollectArticleData(int position, FeedArticleData feedArticleData, FeedArticleListData feedArticleListData);

        /**
         * Show jump the top
         */
        void showJumpTheTop();

        /**
         * Show reload detail event
         */
        void showReloadDetailEvent();
    }

    interface Presenter extends AbstractPresenter<View> {

        /**
         * 知识列表
         *
         * @param page page num
         * @param cid second page id
         * @param isShowError If show error
         */
        void getKnowledgeHierarchyDetailData(int page, int cid, boolean isShowError);

        /**
         * Add collect article
         *
         * @param position Position
         * @param feedArticleData FeedArticleData
         */
        void addCollectArticle(int position, FeedArticleData feedArticleData);

        /**
         * Cancel collect article
         *
         * @param position Position
         * @param feedArticleData FeedArticleData
         */
        void cancelCollectArticle(int position, FeedArticleData feedArticleData);
    }
}
