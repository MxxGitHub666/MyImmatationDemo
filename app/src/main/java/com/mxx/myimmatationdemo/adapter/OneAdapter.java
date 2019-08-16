package com.mxx.myimmatationdemo.adapter;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mxx.myimmatationdemo.R;

import com.mxx.myimmatationdemo.activity.WebActivity;
import com.mxx.myimmatationdemo.bean.TechnologyBean;
import com.mxx.myimmatationdemo.utils.GlideUtils;

import java.util.HashMap;


/**
 * @author geyifeng
 * @date 2017/6/3
 */
public class OneAdapter extends BaseQuickAdapter<TechnologyBean, BaseViewHolder> {
    public OneAdapter() {
        super(R.layout.item_home2);
    }

    @Override
    protected void convert(BaseViewHolder helper, final TechnologyBean item) {

        ImageView imageView =  helper.getView(R.id.iv_title);
        TextView textView_name = helper.getView(R.id.tv_name);
        helper.setText(R.id.tv_title, item.getTitle());
        helper.setText(R.id.tv_name, item.getName());
        helper.setText(R.id.tv_readnum, "阅读数："+item.getReadNum());
        helper.setText(R.id.tv_pinglun, "评论数"+item.getAppraiseNum());
        helper.setText(R.id.tv_time, item.getTime());
        helper.setText(R.id.tv_content, item.getContent());
        GlideUtils.load(item.getImgUrl(),imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, WebActivity.class);
                intent.putExtra("url",item.getNameUrl());
                mContext.startActivity(intent);
            }
        });

        textView_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, WebActivity.class);
                intent.putExtra("url",item.getNameUrl());
                mContext.startActivity(intent);
            }
        });


    }

}
