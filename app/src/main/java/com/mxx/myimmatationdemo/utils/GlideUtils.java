package com.mxx.myimmatationdemo.utils;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.DrawableImageViewTarget;
import com.bumptech.glide.request.transition.Transition;
import com.mxx.myimmatationdemo.R;

import jp.wasabeef.blurry.Blurry;


/**
 * @author mxx
 *
 * @date 2019-04-20 02:00
 */
public class GlideUtils {
    public static void load(Object object, ImageView imageView) {
        if (imageView == null) {
            return;
        }
        load(object, imageView, R.mipmap.test);
    }

    public static void load(Object object, ImageView imageView, @DrawableRes int placeholder) {
        if (imageView == null) {
            return;
        }
        Log.e("GlideUtilsobj",object.toString()+"");
        Log.e("GlideUtilsnetwork",Utils.isNetworkConnected(imageView.getContext())+"");
        if (!Utils.isNetworkConnected(imageView.getContext())) {
            object = ContextCompat.getDrawable(imageView.getContext(), placeholder);
        }
        Glide.with(imageView).load(object).apply(new RequestOptions().placeholder(placeholder)).into(imageView);
    }

    public static void loadBlurry(final ImageView imageView, Object o) {
        if (imageView == null) {
            return;
        }
        Drawable drawable = imageView.getDrawable();
        if (drawable == null) {
            drawable = ContextCompat.getDrawable(imageView.getContext(), R.mipmap.test);
        }
        if (!Utils.isNetworkConnected(imageView.getContext())) {
            o = ContextCompat.getDrawable(imageView.getContext(), R.mipmap.test);
        }
        Glide.with(imageView.getContext())
                .load(o)
                .apply(new RequestOptions().placeholder(drawable).error(drawable).centerCrop())
                .transition(new DrawableTransitionOptions().crossFade())
                .into(new DrawableImageViewTarget(imageView) {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        Bitmap bitmap = ((BitmapDrawable) resource).getBitmap();
                        Blurry.with(imageView.getContext()).from(bitmap).into(imageView);
                    }
                });
    }
}
