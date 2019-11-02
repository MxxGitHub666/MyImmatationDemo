package com.mxx.myimmatationdemo.ui.main.viewholder;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.mxx.myimmatationdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchHistoryViewHolder extends BaseViewHolder {

    @BindView(R.id.item_search_history_tv)
    TextView mSearchHistoryTv;

    public SearchHistoryViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}
