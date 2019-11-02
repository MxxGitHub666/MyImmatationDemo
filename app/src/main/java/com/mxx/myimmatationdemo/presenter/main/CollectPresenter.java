package com.mxx.myimmatationdemo.presenter.main;

import com.mxx.myimmatationdemo.R;
import com.mxx.myimmatationdemo.app.MyApp;
import com.mxx.myimmatationdemo.base.presenter.BasePresenter;
import com.mxx.myimmatationdemo.component.RxBus;
import com.mxx.myimmatationdemo.contract.main.CollectContract;
import com.mxx.myimmatationdemo.core.DataManager;
import com.mxx.myimmatationdemo.core.bean.main.collect.FeedArticleData;
import com.mxx.myimmatationdemo.core.bean.main.collect.FeedArticleListData;
import com.mxx.myimmatationdemo.core.event.CollectEvent;
import com.mxx.myimmatationdemo.utils.RxUtils;
import com.mxx.myimmatationdemo.widget.BaseObserver;

import javax.inject.Inject;

public class CollectPresenter extends BasePresenter<CollectContract.View> implements CollectContract.Presenter {

    private DataManager mDataManager;

    @Inject
      CollectPresenter(DataManager dataManager) {
        super(dataManager);
        mDataManager = dataManager;
    }

    @Override
    public void attachView(CollectContract.View view) {
        super.attachView(view);
        registerEvent();
    }

    private void registerEvent(){
        addSubscribe(RxBus.getDefault().toFlowable(CollectEvent.class)
        .subscribe(collectEvent -> mView.showRefreshEvent()));
    }

    @Override
    public void getCollectList(int page, boolean isShowError) {

        addSubscribe(mDataManager.getCollectList(page)
        .compose(RxUtils.rxSchedulerHelper())
        .compose(RxUtils.handleResult())
        .subscribeWith(new BaseObserver<FeedArticleListData>(mView,
                MyApp.getInstance().getString(R.string.failed_to_obtain_collection_data),
                isShowError){
            @Override
            public void onNext(FeedArticleListData feedArticleListData) {
                super.onNext(feedArticleListData);
                mView.showCollectList(feedArticleListData);
            }
        }));
    }

    @Override
    public void cancelCollectPageArticle(int position, FeedArticleData feedArticleData) {
        addSubscribe(mDataManager.cancelCollectPageArticle(feedArticleData.getId())
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleCollectResult())
                .subscribeWith(new BaseObserver<FeedArticleListData>(mView,
                        MyApp.getInstance().getString(R.string.cancel_collect_fail)) {
                    @Override
                    public void onNext(FeedArticleListData feedArticleListData) {
                        feedArticleData.setCollect(false);
                        mView.showCancelCollectPageArticleData(position, feedArticleData, feedArticleListData);
                    }
                }));
    }
}
