package com.mxx.myimmatationdemo.presenter.hierarchy;

import com.mxx.myimmatationdemo.R;
import com.mxx.myimmatationdemo.app.MyApp;
import com.mxx.myimmatationdemo.base.presenter.BasePresenter;
import com.mxx.myimmatationdemo.component.RxBus;
import com.mxx.myimmatationdemo.contract.hierarchy.KnowledgeHierarchyListContract;
import com.mxx.myimmatationdemo.core.DataManager;
import com.mxx.myimmatationdemo.core.bean.main.collect.FeedArticleData;
import com.mxx.myimmatationdemo.core.bean.main.collect.FeedArticleListData;
import com.mxx.myimmatationdemo.core.event.CollectEvent;
import com.mxx.myimmatationdemo.core.event.KnowledgeJumpTopEvent;
import com.mxx.myimmatationdemo.core.event.ReloadDetailEvent;
import com.mxx.myimmatationdemo.utils.RxUtils;
import com.mxx.myimmatationdemo.widget.BaseObserver;

import javax.inject.Inject;

public class KnowledgeHierarchyListPresenter extends BasePresenter<KnowledgeHierarchyListContract.View> implements KnowledgeHierarchyListContract.Presenter {

    private DataManager mDataManager;

    @Inject
     KnowledgeHierarchyListPresenter(DataManager dataManager) {
        super(dataManager);
        this.mDataManager = dataManager;
    }

    @Override
    public void attachView(KnowledgeHierarchyListContract.View view) {
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

        addSubscribe(RxBus.getDefault().toFlowable(KnowledgeJumpTopEvent.class)
                .subscribe(knowledgeJumpTopEvent -> mView.showJumpTheTop()));

        addSubscribe(RxBus.getDefault().toFlowable(ReloadDetailEvent.class)
                .subscribe(reloadEvent -> mView.showReloadDetailEvent()));
    }
    @Override
    public void getKnowledgeHierarchyDetailData(int page, int cid, boolean isShowError) {
        addSubscribe(mDataManager.getKnowledgeHierarchyDetailData(page,cid)
            .compose(RxUtils.rxSchedulerHelper())
            .compose(RxUtils.handleResult())
            .subscribeWith(new BaseObserver<FeedArticleListData>(mView, MyApp.getInstance().getString(R.string.failed_to_obtain_knowledge_data),
                    isShowError){
                @Override
                public void onNext(FeedArticleListData feedArticleListData) {
                    mView.showKnowledgeHierarchyDetailData(feedArticleListData);
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
