package com.mxx.myimmatationdemo.ui.main.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mxx.myimmatationdemo.R;
import com.mxx.myimmatationdemo.core.dao.HistoryData;
import com.mxx.myimmatationdemo.ui.main.viewholder.SearchHistoryViewHolder;
import com.mxx.myimmatationdemo.utils.CommonUtils;

import java.util.List;

public class HistorySearchAdapter extends BaseQuickAdapter<HistoryData,SearchHistoryViewHolder> {

    public HistorySearchAdapter(int layoutResId, @Nullable List<HistoryData> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(SearchHistoryViewHolder helper, HistoryData item) {
        helper.setText(R.id.item_search_history_tv, item.getData());
        helper.setTextColor(R.id.item_search_history_tv, CommonUtils.randomColor());
        helper.addOnClickListener(R.id.item_search_history_tv);
    }
}
