package test.plugin.com.mylibrary.request;

import android.content.Context;
import android.content.Intent;

import test.plugin.com.mylibrary.Utils.IntentUtils;
import test.plugin.com.mylibrary.base.Request;
import test.plugin.com.mylibrary.result.PermissHelper;

/**
 * Created by 98179 on 2019/8/22.
 */

public class InstallORequest extends InstallRequest implements Request {

    public InstallORequest(Context context) {
        super(context);
    }

    @Override
    public void start() {
        if (file == null || !file.exists()) {
            callbackFailed();
            return;
        }
        if (canRequestPackageInstalls()) {
            context.startActivity(IntentUtils.getInstallIntent(context, file));
        } else {
            final Intent intent = IntentUtils.getRequestInstailIntent(context);
            PermissHelper.init(context).startActivityForResult(intent, new PermissHelper.CallBack() {
                @Override
                public void onActivityResult(int resultCode, Intent data) {
                    if (canRequestPackageInstalls()) {
                        callbackSucceed();
                        context.startActivity(IntentUtils.getInstallIntent(context, file));
                    } else {
                        callbackFailed();
                    }
                }
            });
        }
    }
}

