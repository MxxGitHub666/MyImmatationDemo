package com.mxx.myimmatationdemo.utils;

// http://stackoverflow.com/questions/40176244/how-to-disable-bottomnavigationview-shift-mode

import android.annotation.SuppressLint;
import android.support.design.bottomnavigation.LabelVisibilityMode;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;

public class BottomNavigationViewHelper {

    @SuppressLint("RestrictedApi")
    public static void disableShiftMode(BottomNavigationView view) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
        menuView.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);
        menuView.setItemHorizontalTranslationEnabled(false);
        menuView.buildMenuView();
    }
}
