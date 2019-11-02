package com.mxx.myimmatationdemo.contract.main;


import com.mxx.myimmatationdemo.base.presenter.AbstractPresenter;
import com.mxx.myimmatationdemo.base.view.AbstractView;
import com.mxx.myimmatationdemo.core.bean.main.collect.FeedArticleData;
import com.mxx.myimmatationdemo.core.bean.main.collect.FeedArticleListData;

/**
 * @author quchao
 * @date 2018/3/13
 */

public interface SearchListContract {

    interface View extends AbstractView {

        /**
         * Show search list
         *
         * @param feedArticleListData FeedArticleListData
         */
        void showSearchList(FeedArticleListData feedArticleListData);

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

    }

    interface Presenter extends AbstractPresenter<View> {

        /**
         * 搜索
         * @param page page
         * @param k POST search key
         * @param isShowError If show error
         */
        void getSearchList(int page, String k, boolean isShowError);

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
