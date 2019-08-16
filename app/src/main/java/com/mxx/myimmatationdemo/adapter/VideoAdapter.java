package com.mxx.myimmatationdemo.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mxx.myimmatationdemo.R;
import com.mxx.myimmatationdemo.bean.TvTaiModel;
import com.mxx.myimmatationdemo.utils.GlideUtils;

/**
 * Created by 98179 on 2019/7/4.
 */

public class VideoAdapter extends BaseQuickAdapter<TvTaiModel, BaseViewHolder> {


    public VideoAdapter() {
        super(R.layout.item_video);
    }

    @Override
    protected void convert(BaseViewHolder helper, TvTaiModel item) {
        ImageView imageView =  helper.getView(R.id.iv_video);
        GlideUtils.load(item.getImg(),imageView);
        helper.setText(R.id.tv_title,item.getTitle());
        helper.setText(R.id.tv_grade,item.getGrade());
        helper.setText(R.id.tv_series,item.getSeries());



    }
}
