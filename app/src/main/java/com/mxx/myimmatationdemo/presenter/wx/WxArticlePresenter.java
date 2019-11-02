package com.mxx.myimmatationdemo.presenter.wx;


import com.mxx.myimmatationdemo.R;
import com.mxx.myimmatationdemo.app.MyApp;
import com.mxx.myimmatationdemo.base.presenter.BasePresenter;
import com.mxx.myimmatationdemo.contract.wx.WxContract;
import com.mxx.myimmatationdemo.core.DataManager;
import com.mxx.myimmatationdemo.core.bean.wx.WxAuthor;
import com.mxx.myimmatationdemo.utils.RxUtils;
import com.mxx.myimmatationdemo.widget.BaseObserver;

import java.util.List;

import javax.inject.Inject;



/**
 * @author mxx
 * @date 2019/10/29
 */
public class WxArticlePresenter extends BasePresenter<WxContract.View> implements WxContract.Presenter {

    private DataManager mDataManager;

    @Inject
    public WxArticlePresenter(DataManager dataManager) {
        super(dataManager);
        this.mDataManager = dataManager;
    }

    @Override
    public void getWxAuthorListData() {
        addSubscribe(mDataManager.getWxAuthorListData()
                    .compose(RxUtils.rxSchedulerHelper())
                    .compose(RxUtils.handleResult())
                    .subscribeWith(new BaseObserver<List<WxAuthor>>(mView,
                            MyApp.getInstance().getString(R.string.fail_to_obtain_wx_author)) {
                        @Override
                        public void onNext(List<WxAuthor> wxAuthors) {
                            mView.showWxAuthorListView(wxAuthors);
                        }
                    }));
    }


}
