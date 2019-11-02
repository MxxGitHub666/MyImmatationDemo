package com.mxx.myimmatationdemo.contract.wx;


import com.mxx.myimmatationdemo.base.presenter.AbstractPresenter;
import com.mxx.myimmatationdemo.base.view.AbstractView;
import com.mxx.myimmatationdemo.core.bean.wx.WxAuthor;

import java.util.List;

/**
 * @author quchao
 * @date 2018/10/31
 */
public interface WxContract {

    interface View extends AbstractView {

        /**
         * 显示公众号作者列表
         *
         * @param wxAuthors 公众号作者列表
         */
        void showWxAuthorListView(List<WxAuthor> wxAuthors);


    }

    interface Presenter extends AbstractPresenter<View> {

        /**
         * 获取公众号作者列表
         */
        void getWxAuthorListData();

    }

}
