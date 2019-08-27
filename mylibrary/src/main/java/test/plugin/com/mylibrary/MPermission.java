package test.plugin.com.mylibrary;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

import java.util.Arrays;
import java.util.List;

import test.plugin.com.mylibrary.base.Boot;

/**
 * Created by 98179 on 2019/8/21.
 */

public class MPermission {

    public static Boot with(Context context) {
        return new Boot(context);
    }


    public static boolean hasPermission(Context context, String... permissions) {
        return hasPermission(context, Arrays.asList(permissions));
    }


    public static boolean hasPermission(Context context, List<String> permissions){
        if(Build.VERSION.SDK_INT<Build.VERSION_CODES.M){
            return true;
        }
        for(String permission:permissions){
            int result = context.checkPermission(permission, android.os.Process.myPid(), android.os.Process.myUid());
            if(result == PackageManager.PERMISSION_DENIED){
                return  false;
            }
        }

        return true;
    }
}
