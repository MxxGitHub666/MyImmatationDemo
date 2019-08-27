package test.plugin.com.cameralibrary.utils;

import android.content.Context;

/**
 * Created by 98179 on 2019/8/27.
 *    ${屏幕相关工具类}
 */

public class ScreenUtils {
    /**
     * 获取屏幕宽度（px）
     *
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }


    /**
     * 获取屏幕高度（px）
     *
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }
}
