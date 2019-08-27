package com.mxx.myimmatationdemo.myinterface;

import android.view.View;

/**
 * Created by 98179 on 2019/8/22.
 */

public interface OnViewPagerListener {

    /**
     * 初始化
     */
    void onInitComplete(View view);

    /**
     * 释放
     */
    void onPageRelease(boolean isNext, int position, View view);

    /**
     * 选中
     */
    void onPageSelected(int position, boolean isBottom, View view);

}
