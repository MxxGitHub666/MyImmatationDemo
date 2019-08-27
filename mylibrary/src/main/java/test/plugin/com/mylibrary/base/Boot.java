package test.plugin.com.mylibrary.base;


import android.app.VoiceInteractor;
import android.content.Context;
import android.os.Build;

import test.plugin.com.mylibrary.request.InstallLRequest;
import test.plugin.com.mylibrary.request.InstallORequest;
import test.plugin.com.mylibrary.request.PermissionRequest;
import test.plugin.com.mylibrary.request.SettingRequest;

/**
 * Created by 98179 on 2019/8/21.
 */

public class Boot implements Option {

    private Context context;

    public Boot(Context context){
        this.context = context;
    }
    @Override
    public Request permiss() {
        return new PermissionRequest(context);
    }

    @Override
    public Request setting() {
        return new SettingRequest(context);
    }

    @Override
    public Request install() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return new InstallORequest(context);
        } else {
            return new InstallLRequest(context);
        }
    }
}
