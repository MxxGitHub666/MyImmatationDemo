package com.mxx.myimmatationdemo.presenter.hierarchy;

import com.mxx.myimmatationdemo.R;
import com.mxx.myimmatationdemo.app.MyApp;
import com.mxx.myimmatationdemo.base.presenter.BasePresenter;
import com.mxx.myimmatationdemo.contract.hierarchy.KnowledgeHierarchyContract;
import com.mxx.myimmatationdemo.core.DataManager;
import com.mxx.myimmatationdemo.core.bean.hierarchy.KnowledgeHierarchyData;
import com.mxx.myimmatationdemo.utils.RxUtils;
import com.mxx.myimmatationdemo.widget.BaseObserver;

import java.util.List;

import javax.inject.Inject;

public class KnowledgeHierarchyPresenter extends BasePresenter<KnowledgeHierarchyContract.View> implements KnowledgeHierarchyContract.Presenter {
    private DataManager mDataManager;

    @Inject
    KnowledgeHierarchyPresenter(DataManager dataManager) {
        super(dataManager);
        this.mDataManager = dataManager;
    }

    @Override
    public void getKnowledgeHierarchyData(boolean isShowError) {
        addSubscribe(mDataManager.getKnowledgeHierarchyData()
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResult())
                .subscribeWith(new BaseObserver<List<KnowledgeHierarchyData>>(mView,
                        MyApp.getInstance().getString(R.string.failed_to_obtain_knowledge_data),
                        isShowError) {
                    @Override
                    public void onNext(List<KnowledgeHierarchyData> knowledgeHierarchyDataList) {
                        mView.showKnowledgeHierarchyData(knowledgeHierarchyDataList);
                    }
                }));
    }

}
