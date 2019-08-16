package com.mxx.myimmatationdemo.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mxx.myimmatationdemo.R;
import com.mxx.myimmatationdemo.bean.News;

import java.util.List;

/**
 * Created by 98179 on 2019/6/17.
 */

public class MainAdapter extends BaseQuickAdapter<News,BaseViewHolder> {

    public MainAdapter(@LayoutRes int layoutResId, @Nullable List<News> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, News item) {
        helper.setText(R.id.tvName,item.getNewsTitle()).addOnClickListener(R.id.tvName);
        helper.setText(R.id.tvTime,item.getNewsTime());
        helper.setText(R.id.tvDes,item.getDesc());
//        helper.setText(R.id.tvTitle,item.getNewsTitle());


    }
}
