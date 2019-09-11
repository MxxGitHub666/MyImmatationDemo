package com.mxx.myimmatationdemo.utils;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.mxx.myimmatationdemo.R;
import com.mxx.myimmatationdemo.app.MyApp;


public class CommonUtils {

    /**
     * Show message
     *
     * @param activity Activity
     * @param msg message
     */
    public static void showMessage(Activity activity, String msg) {
        Log.e("showMessage ：" ,msg);
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show();
    }


    public static void showSnackMessage(final Activity activity, String msg) {
        Log.e("showSnackMessage ：" , msg);
        activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE);
        final Snackbar snackbar = Snackbar.make(activity.getWindow().getDecorView(),msg,Snackbar.LENGTH_SHORT);
        View view = snackbar.getView();
        ((TextView) view.findViewById(R.id.snackbar_text)).setTextColor(ContextCompat.getColor(activity, R.color.white));
        snackbar.setAction("知道了", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackbar.dismiss();
            //隐藏SnackBar时记得恢复隐藏虚拟按键栏,不然屏幕底部会多出一块空白布局出来,很难看
            activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
            }
        }).show();

        snackbar.addCallback(new BaseTransientBottomBar.BaseCallback<Snackbar>() {
            @Override
            public void onDismissed(Snackbar transientBottomBar, int event) {
                super.onDismissed(transientBottomBar, event);
                activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
            }
        });
    }

    /**
     * 检查是否有可用网络
     */
    public static boolean isNetworkConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) MyApp.getInstance().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        assert connectivityManager != null;
        return connectivityManager.getActiveNetworkInfo() != null;
    }
}
