package test.plugin.com.mylibrary.request;

import android.content.Context;
import android.content.Intent;

import java.util.Arrays;
import java.util.List;

import test.plugin.com.mylibrary.Utils.IntentUtils;
import test.plugin.com.mylibrary.Utils.PermissUtils;
import test.plugin.com.mylibrary.base.Request;
import test.plugin.com.mylibrary.listener.PermissListener;
import test.plugin.com.mylibrary.result.PermissHelper;

/**
 * Created by 98179 on 2019/8/22.
 */

public class SettingRequest implements Request {

    private Context context;
    private PermissListener<String> listener;
    private List<String> permissions;


    public SettingRequest(Context context) {
        this.context = context;
    }


    @Override
    public Request listener(PermissListener<String> granted) {
        this.listener = granted;
        return this;
    }

    @Override
    public Request permission(String... permissions) {
        this.permissions = Arrays.asList(permissions);
        return this;
    }

    @Override
    public void start() {
        Intent intent = IntentUtils.getSettingIntent(context);
        PermissHelper.init(context).startActivityForResult(intent, new PermissHelper.CallBack() {
            @Override
            public void onActivityResult(int resultCode, Intent data) {
                List<String> permiss = PermissUtils.getDeniedPermissions(context, permissions);
                //没有拒绝的权限
                if (permiss.isEmpty()) {
                    callbackSucceed();
                } else {
                    callbackFailed(permiss);
                }
            }
        });
    }

    private void callbackSucceed() {
        if (listener != null) {
            listener.onGranted(permissions);
        }
    }

    private void callbackFailed(List<String> deniedList) {
        if (listener != null) {
            listener.onDenied(deniedList);
        }
    }
}
