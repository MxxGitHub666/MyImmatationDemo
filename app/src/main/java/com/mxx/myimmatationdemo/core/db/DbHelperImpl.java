package com.mxx.myimmatationdemo.core.db;

import com.mxx.myimmatationdemo.app.MyApp;
import com.mxx.myimmatationdemo.core.dao.DaoSession;
import com.mxx.myimmatationdemo.core.dao.HistoryData;
import com.mxx.myimmatationdemo.core.dao.HistoryDataDao;

import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by 98179 on 2019/9/6.
 */

public class DbHelperImpl implements DbHelper {

    private static final int HISTORY_LIST_SIZE = 10;

    private DaoSession daoSession;
    private List<HistoryData> historyDataList;
    private String data;
    private HistoryData historyData;

    @Inject
    DbHelperImpl() {
        daoSession = MyApp.getInstance().getDaoSession();
    }

    @Override
    public List<HistoryData> addHistoryData(String data) {
        this.data = data;
        getHistoryDataList();
        createHistoryData();
        if (historyDataForward()) {
            return historyDataList;
        }

        if (historyDataList.size() < HISTORY_LIST_SIZE) {
            getHistoryDataDao().insert(historyData);
        } else {
            historyDataList.remove(0);
            historyDataList.add(historyData);
            getHistoryDataDao().deleteAll();
            getHistoryDataDao().insertInTx(historyDataList);
        }
        return historyDataList;
    }

    @Override
    public void clearHistoryData() {
        daoSession.getHistoryDataDao().deleteAll();
    }

    @Override
    public List<HistoryData> loadAllHistoryData() {
        return daoSession.getHistoryDataDao().loadAll();
    }

    /**
     * 历史数据前移
     *
     * @return 返回true表示查询的数据已存在，只需将其前移到第一项历史记录，否则需要增加新的历史记录
     */
    private boolean historyDataForward() {
        //重复搜索时进行历史记录前移
        Iterator<HistoryData> iterator = historyDataList.iterator();
        //不要在foreach循环中进行元素的remove、add操作，使用Iterator模式
        while (iterator.hasNext()) {
            HistoryData historyData1 = iterator.next();
            if (historyData1.getData().equals(data)) {
                historyDataList.remove(historyData1);
                historyDataList.add(historyData);
                getHistoryDataDao().deleteAll();
                getHistoryDataDao().insertInTx(historyDataList);
                return true;
            }
        }
        return false;
    }

    private  void getHistoryDataList(){
        historyDataList = getHistoryDataDao().loadAll();
    }

    private HistoryDataDao getHistoryDataDao(){
        return daoSession.getHistoryDataDao();
    }

    private void createHistoryData(){
        historyData = new HistoryData();
        historyData.setDate(System.currentTimeMillis());
        historyData.setData(data);
    }
}
