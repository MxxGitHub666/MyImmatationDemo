package com.mxx.myimmatationdemo.presenter.project;


import com.mxx.myimmatationdemo.R;
import com.mxx.myimmatationdemo.app.MyApp;
import com.mxx.myimmatationdemo.base.presenter.BasePresenter;
import com.mxx.myimmatationdemo.component.RxBus;
import com.mxx.myimmatationdemo.contract.project.ProjectListContract;
import com.mxx.myimmatationdemo.core.DataManager;
import com.mxx.myimmatationdemo.core.bean.main.collect.FeedArticleData;
import com.mxx.myimmatationdemo.core.bean.main.collect.FeedArticleListData;
import com.mxx.myimmatationdemo.core.bean.project.ProjectListData;
import com.mxx.myimmatationdemo.core.event.JumpToTheTopEvent;
import com.mxx.myimmatationdemo.utils.RxUtils;
import com.mxx.myimmatationdemo.widget.BaseObserver;

import javax.inject.Inject;


/**
 * @author quchao
 * @date 2018/2/24
 */

public class ProjectListPresenter extends BasePresenter<ProjectListContract.View> implements ProjectListContract.Presenter {

    private DataManager mDataManager;

    @Inject
    ProjectListPresenter(DataManager dataManager) {
        super(dataManager);
        this.mDataManager = dataManager;
    }

    @Override
    public void attachView(ProjectListContract.View view) {
        super.attachView(view);
        registerEvent();
    }

    private void registerEvent() {
        addSubscribe(RxBus.getDefault().toFlowable(JumpToTheTopEvent.class)
                .subscribe(jumpToTheTopEvent -> mView.showJumpToTheTop()));
    }

    @Override
    public void getProjectListData(int page, int cid, boolean isShowError) {
        addSubscribe(mDataManager.getProjectListData(page, cid)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResult())
                .subscribeWith(new BaseObserver<ProjectListData>(mView,
                        MyApp.getInstance().getString(R.string.failed_to_obtain_project_list),
                        isShowError) {
                            @Override
                            public void onNext(ProjectListData projectListData) {
                                mView.showProjectListData(projectListData);
                            }
                        }));
    }

    @Override
    public void addCollectOutsideArticle(int position, FeedArticleData feedArticleData) {
        addSubscribe(mDataManager.addCollectOutsideArticle(feedArticleData.getTitle(),
                feedArticleData.getAuthor(), feedArticleData.getLink())
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleCollectResult())
                .subscribeWith(new BaseObserver<FeedArticleListData>(mView,
                        MyApp.getInstance().getString(R.string.collect_fail)) {
                            @Override
                            public void onNext(FeedArticleListData feedArticleListData) {
                                feedArticleData.setCollect(true);
                                mView.showCollectOutsideArticle(position, feedArticleData, feedArticleListData);
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
