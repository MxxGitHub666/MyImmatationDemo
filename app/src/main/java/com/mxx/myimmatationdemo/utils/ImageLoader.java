package com.mxx.myimmatationdemo.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.mxx.myimmatationdemo.app.MyApp;

/**
 * @author quchao
 * @date 2017/11/27
 */

public class ImageLoader {

    /**
     * 使用Glide加载圆形ImageView(如头像)时，不要使用占位图
     *
     * @param context context
     * @param url image url
     * @param iv imageView
     */
    public static void load(Context context, String url, ImageView iv) {
        if (!MyApp.getAppComponent().getDataManager().getNoImageState()) {
            Glide.with(context).load(url).diskCacheStrategy(DiskCacheStrategy.DATA).into(iv);
        }
    }
}
