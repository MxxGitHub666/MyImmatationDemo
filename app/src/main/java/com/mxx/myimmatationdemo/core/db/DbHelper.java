package com.mxx.myimmatationdemo.core.db;

import com.mxx.myimmatationdemo.core.dao.HistoryData;

import java.util.List;

/**
 * Created by 98179 on 2019/9/6.
 */

public  interface DbHelper {


    /**
     * 增加历史数据
     *
     * @param data  added string
     * @return  List<HistoryData>
     */
    List<HistoryData> addHistoryData(String data);

    /**
     * Clear search history data
     */
    void clearHistoryData();

    /**
     * Load all history data
     *
     * @return List<HistoryData>
     */
    List<HistoryData> loadAllHistoryData();
}
