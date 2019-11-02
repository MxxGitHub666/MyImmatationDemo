package com.mxx.myimmatationdemo.presenter.wx;

import com.mxx.myimmatationdemo.R;
import com.mxx.myimmatationdemo.app.MyApp;
import com.mxx.myimmatationdemo.base.presenter.BasePresenter;
import com.mxx.myimmatationdemo.component.RxBus;
import com.mxx.myimmatationdemo.contract.wx.WxDetailContract;
import com.mxx.myimmatationdemo.core.DataManager;
import com.mxx.myimmatationdemo.core.bean.main.collect.FeedArticleData;
import com.mxx.myimmatationdemo.core.bean.main.collect.FeedArticleListData;
import com.mxx.myimmatationdemo.core.event.CollectEvent;
import com.mxx.myimmatationdemo.core.event.JumpToTheTopEvent;
import com.mxx.myimmatationdemo.utils.RxUtils;
import com.mxx.myimmatationdemo.widget.BaseObserver;

import javax.inject.Inject;



/**
 * @author quchao
 * @date 2018/10/31
 */
public class WxDetailArticlePresenter extends BasePresenter<WxDetailContract.View> implements WxDetailContract.Presenter {

    private DataManager mDataManager;

    @Inject
    public WxDetailArticlePresenter(DataManager dataManager) {
        super(dataManager);
        this.mDataManager = dataManager;
    }

    @Override
    public void attachView(WxDetailContract.View view) {
        super.attachView(view);
        registerEvent();
    }

    private void registerEvent() {
        addSubscribe(RxBus.getDefault().toFlowable(CollectEvent.class)
                .filter(collectEvent -> !collectEvent.isCancelCollectSuccess())
                .subscribe(collectEvent -> mView.showCollectSuccess()));

        addSubscribe(RxBus.getDefault().toFlowable(CollectEvent.class)
                .filter(CollectEvent::isCancelCollectSuccess)
                .subscribe(collectEvent -> mView.showCancelCollectSuccess()));

        addSubscribe(RxBus.getDefault().toFlowable(JumpToTheTopEvent.class)
                .subscribe(knowledgeJumpTopEvent -> mView.showJumpTheTop()));
    }

    @Override
    public void getWxSearchSumData(int id, int page, String k) {
        addSubscribe(mDataManager.getWxSearchSumData(id, page, k)
                    .compose(RxUtils.rxSchedulerHelper())
                    .compose(RxUtils.handleResult())
                    .subscribeWith(new BaseObserver<FeedArticleListData>(mView,
                            MyApp.getInstance().getString(R.string.failed_to_obtain_search_data_list)) {
                        @Override
                        public void onNext(FeedArticleListData feedArticleListData) {
                            mView.showWxSearchView(feedArticleListData);
                        }
                    }));
    }

    @Override
    public void getWxDetailData(int id, int page, boolean isShowError) {
        addSubscribe(mDataManager.getWxSumData(id, page)
                    .compose(RxUtils.rxSchedulerHelper())
                    .compose(RxUtils.handleResult())
                    .subscribeWith(new BaseObserver<FeedArticleListData>(mView,
                            MyApp.getInstance().getString(R.string.failed_to_obtain_wx_data),
                            isShowError) {
                        @Override
                        public void onNext(FeedArticleListData wxSumData) {
                            mView.showWxDetailView(wxSumData);
                        }
                    }));
    }

    @Override
    public void addCollectArticle(int position, FeedArticleData feedArticleData) {
        addSubscribe(mDataManager.addCollectArticle(feedArticleData.getId())
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleCollectResult())
                .subscribeWith(new BaseObserver<FeedArticleListData>(mView,
                        MyApp.getInstance().getString(R.string.collect_fail)) {
                    @Override
                    public void onNext(FeedArticleListData feedArticleListData) {
                        feedArticleData.setCollect(true);
                        mView.showCollectArticleData(position, feedArticleData, feedArticleListData);
                    }
                }));
    }

    @Override
    public void cancelCollectArticle(int position, FeedArticleData feedArticleData) {
        addSubscribe(mDataManager.cancelCollectArticle(feedArticleData.getId())
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleCollectResult())
                .subscribeWith(new BaseObserver<FeedArticleListData>(mView,
                        MyApp.getInstance().getString(R.string.cancel_collect_fail)) {
                    @Override
                    public void onNext(FeedArticleListData feedArticleListData) {
                        feedArticleData.setCollect(false);
                        mView.showCancelCollectArticleData(position, feedArticleData, feedArticleListData);
                    }
                }));
    }
}
