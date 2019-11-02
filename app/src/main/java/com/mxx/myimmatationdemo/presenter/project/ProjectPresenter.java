package com.mxx.myimmatationdemo.presenter.project;

import com.mxx.myimmatationdemo.R;
import com.mxx.myimmatationdemo.app.MyApp;
import com.mxx.myimmatationdemo.base.presenter.BasePresenter;
import com.mxx.myimmatationdemo.contract.project.ProjectContract;
import com.mxx.myimmatationdemo.core.DataManager;
import com.mxx.myimmatationdemo.core.bean.project.ProjectClassifyData;
import com.mxx.myimmatationdemo.utils.RxUtils;
import com.mxx.myimmatationdemo.widget.BaseObserver;

import java.util.List;

import javax.inject.Inject;

public class ProjectPresenter extends BasePresenter<ProjectContract.View> implements ProjectContract.Presenter {

    private DataManager mDataManager;

    @Inject
    ProjectPresenter(DataManager dataManager) {
        super(dataManager);
        this.mDataManager = dataManager;
    }

    @Override
    public void attachView(ProjectContract.View view) {
        super.attachView(view);
    }

    @Override
    public void getProjectClassifyData() {
        addSubscribe(mDataManager.getProjectClassifyData()
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResult())
                .subscribeWith(new BaseObserver<List<ProjectClassifyData>>(mView,
                        MyApp.getInstance().getString(R.string.failed_to_obtain_project_classify_data)) {
                    @Override
                    public void onNext(List<ProjectClassifyData> projectClassifyDataList) {
                        mView.showProjectClassifyData(projectClassifyDataList);
                    }
                }));
    }

    @Override
    public int getProjectCurrentPage() {
        return mDataManager.getProjectCurrentPage();
    }

    @Override
    public void setProjectCurrentPage(int page) {
        mDataManager.setProjectCurrentPage(page);
    }

}

