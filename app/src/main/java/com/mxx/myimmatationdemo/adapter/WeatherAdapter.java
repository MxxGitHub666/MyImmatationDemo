package com.mxx.myimmatationdemo.adapter;

import android.os.Build;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mxx.myimmatationdemo.R;
import com.mxx.myimmatationdemo.bean.WeatherBean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.mxx.myimmatationdemo.R.layout.a;


/**
 * Created by 98179 on 2019/6/29.
 */

public class WeatherAdapter extends BaseQuickAdapter<WeatherBean, BaseViewHolder> {


    public WeatherAdapter(@LayoutRes int layoutResId, @Nullable List<WeatherBean> data) {
        super(layoutResId, data);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void convert(BaseViewHolder helper, WeatherBean item) {

//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        String today = simpleDateFormat.format(new Date());
        ImageView imageView =  helper.getView(R.id.iv_temperatureType);

        helper.setText(R.id.tv_timeAndtype,item.getData()+"·"+item.getType());
        helper.setText(R.id.tv_temperature,item.getHigh()+"/"+item.getLow());

       if(item.getType().contains("中雨")){
           Glide.with(mContext).load(R.mipmap.weather_zhongyu).into(imageView);
       }else if(item.getType().contains("小雨")){
           Glide.with(mContext).load(R.mipmap.weather_xiaoyu).into(imageView);
       }else if(item.getType().contains("阵雨")||item.getType().contains("大")){
           Glide.with(mContext).load(R.mipmap.weather_zhengyu).into(imageView);
       }else if(item.getType().contains("阴")){
           Glide.with(mContext).load(R.mipmap.weather_yintian).into(imageView);
       }else if(item.getType().contains("晴")){
           Glide.with(mContext).load(R.mipmap.weather_sun).into(imageView);
       }else if(item.getType().contains("雪")){
           Glide.with(mContext).load(R.mipmap.weather_xiaxue).into(imageView);
       }else if(item.getType().contains("雷")){
           Glide.with(mContext).load(R.mipmap.weather_dalei).into(imageView);
       }else if(item.getType().contains("云")){
           Glide.with(mContext).load(R.mipmap.weather_duoyun).into(imageView);
       }else if(item.getType().contains("雾")){
           Glide.with(mContext).load(R.mipmap.weather_wu).into(imageView);
       }

    }
}
