package test.plugin.com.cameralibrary.cropper;

import android.graphics.Bitmap;

/**
 * Created by 98179 on 2019/8/27.
 * Desc	        ${裁剪监听接口}
 */

public interface CropListener {
    void onFinish(Bitmap bitmap);
}

