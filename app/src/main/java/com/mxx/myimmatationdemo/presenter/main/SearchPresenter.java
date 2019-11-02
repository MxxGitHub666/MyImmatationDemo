package com.mxx.myimmatationdemo.presenter.main;

import com.mxx.myimmatationdemo.R;
import com.mxx.myimmatationdemo.app.MyApp;
import com.mxx.myimmatationdemo.base.presenter.BasePresenter;
import com.mxx.myimmatationdemo.contract.main.SearchContract;
import com.mxx.myimmatationdemo.core.DataManager;
import com.mxx.myimmatationdemo.core.bean.search.TopSearchData;
import com.mxx.myimmatationdemo.core.dao.HistoryData;
import com.mxx.myimmatationdemo.utils.RxUtils;
import com.mxx.myimmatationdemo.widget.BaseObserver;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;

/**
 * @author quchao
 * @date 2018/2/12
 */

public class SearchPresenter extends BasePresenter<SearchContract.View> implements SearchContract.Presenter {

    private DataManager mDataManager;

    @Inject
    SearchPresenter(DataManager dataManager) {
        super(dataManager);
        this.mDataManager = dataManager;
    }

    @Override
    public void attachView(SearchContract.View view) {
        super.attachView(view);
    }

    @Override
    public List<HistoryData> loadAllHistoryData() {
        return mDataManager.loadAllHistoryData();
    }

    @Override
    public void addHistoryData(String data) {
        addSubscribe(Observable.create((ObservableOnSubscribe<List<HistoryData>>) e -> {
                List<HistoryData> historyDataList = mDataManager.addHistoryData(data);
                e.onNext(historyDataList);
        })
                .compose(RxUtils.rxSchedulerHelper())
                .subscribe(historyDataList ->
                mView.judgeToTheSearchListActivity()));
    }

    @Override
    public void clearHistoryData() {
        mDataManager.clearHistoryData();
    }

    @Override
    public void getTopSearchData() {
        addSubscribe(mDataManager.getTopSearchData()
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResult())
                .subscribeWith(new BaseObserver<List<TopSearchData>>(mView,
                     MyApp.getInstance().getString(R.string.failed_to_obtain_top_data)) {
                            @Override
                            public void onNext(List<TopSearchData> topSearchDataList) {
                                mView.showTopSearchData(topSearchDataList);
                            }
                        }));
    }

}
