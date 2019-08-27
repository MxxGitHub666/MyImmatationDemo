package test.plugin.com.mylibrary.request;

import android.content.Context;

import test.plugin.com.mylibrary.Utils.IntentUtils;
import test.plugin.com.mylibrary.base.Request;

/**
 * Created by 98179 on 2019/8/22.
 */

public class InstallLRequest extends InstallRequest implements Request {


    public InstallLRequest(Context context) {
        super(context);
    }

    @Override
    public void start() {
        if (file == null || !file.exists()) {
            callbackFailed();
            return;
        }
        if (canRequestPackageInstalls()) {
            callbackSucceed();
            context.startActivity(IntentUtils.getInstallIntent(context, file));
        } else {
            callbackFailed();
        }

    }

}