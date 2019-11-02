package com.mxx.myimmatationdemo.presenter.hierarchy;

import com.mxx.myimmatationdemo.base.presenter.BasePresenter;
import com.mxx.myimmatationdemo.component.RxBus;
import com.mxx.myimmatationdemo.contract.hierarchy.KnowledgeHierarchyDetailContract;
import com.mxx.myimmatationdemo.core.DataManager;
import com.mxx.myimmatationdemo.core.event.SwitchNavigationEvent;
import com.mxx.myimmatationdemo.core.event.SwitchProjectEvent;

import javax.inject.Inject;

public class KnowledgeHierarchyDetailPresenter extends BasePresenter<KnowledgeHierarchyDetailContract.View> implements KnowledgeHierarchyDetailContract.Presenter {

    @Inject
    public KnowledgeHierarchyDetailPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void attachView(KnowledgeHierarchyDetailContract.View view) {
        super.attachView(view);
        registerEvent();
    }

    private void registerEvent(){
        addSubscribe(RxBus.getDefault().toFlowable(SwitchProjectEvent.class)
                    .subscribe(switchProjectEvent -> mView.showSwitchProject()));

        addSubscribe(RxBus.getDefault().toFlowable(SwitchNavigationEvent.class)
                .subscribe(switchNavigationEvent -> mView.showSwitchNavigation()));
    }
}
