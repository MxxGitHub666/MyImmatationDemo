package test.plugin.com.mylibrary.result;


import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;

import java.util.List;

/**
 * Created by 98179 on 2019/8/22.
 */

public class PermissHelper {

    private static final String TAG = "PermissHelper";
    private Context context;

    public static PermissHelper init(Context context) {
        return new PermissHelper(context);
    }

    private PermissHelper(Context context) {
        this.context = context;
    }

    private PermissFragment getRouterFragment() {
        if (context instanceof Activity) {
            PermissFragment routerFragment = findRouterFragment((Activity) context);
            if (routerFragment == null) {
                routerFragment = PermissFragment.newInstance();

                FragmentManager fragmentManager = ((Activity) context).getFragmentManager();

                fragmentManager
                        .beginTransaction()
                        .add(routerFragment, TAG)
                        .commitAllowingStateLoss();
                fragmentManager.executePendingTransactions();
            }
            return routerFragment;
        }


        return null;
    }


    private PermissSupportFragment getSupportRouterFragment() {
        if (context instanceof FragmentActivity) {
            PermissSupportFragment routerFragment = findRouterFragment((FragmentActivity) context);
            if (routerFragment == null) {
                routerFragment = PermissSupportFragment.newInstance();
                android.support.v4.app.FragmentManager fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
                fragmentManager
                        .beginTransaction()
                        .add(routerFragment, TAG)
                        .commitAllowingStateLoss();
                fragmentManager.executePendingTransactions();
            }
            return routerFragment;

        }

        return null;

    }

    public void requestPermissions(String[] permissions, PermissHelper.PermissCallBack callback) {
        if (context instanceof FragmentActivity) {
            getSupportRouterFragment().requestPermissions(permissions, callback);
        } else {
            getRouterFragment().requestPermissions(permissions, callback);
        }

    }

    public void requestPermissions(List<String> permissions, PermissHelper.PermissCallBack callback) {
        requestPermissions(permissions.toArray(new String[permissions.size()]), callback);
    }


    public void startActivityForResult(Intent intent, PermissHelper.CallBack callback) {
        if (context instanceof FragmentActivity) {
            getSupportRouterFragment().startActivityForResult(intent, callback);
        } else {
            getRouterFragment().startActivityForResult(intent, callback);
        }
    }


    private PermissSupportFragment findRouterFragment(FragmentActivity activity) {
        return (PermissSupportFragment) activity.getSupportFragmentManager().findFragmentByTag(TAG);
    }

    private PermissFragment findRouterFragment(Activity activity) {
        return (PermissFragment) activity.getFragmentManager().findFragmentByTag(TAG);
    }


    public interface CallBack {
        void onActivityResult(int resultCode, Intent data);
    }

    public interface PermissCallBack {
        void onPermissResult(int resultCode);
    }


}
