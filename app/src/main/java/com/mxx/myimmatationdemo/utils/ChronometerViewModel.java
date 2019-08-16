package com.mxx.myimmatationdemo.utils;

import android.arch.lifecycle.ViewModel;
import android.support.annotation.Nullable;

/**
 * Created by 98179 on 2019/6/26.
 */

public class ChronometerViewModel extends ViewModel {

    @Nullable
    private Long mStartTime;

    public void setStartTime(@Nullable Long mStartTime) {
        this.mStartTime = mStartTime;
    }

    @Nullable
    public Long getStartTime() {
        return mStartTime;
    }

}
