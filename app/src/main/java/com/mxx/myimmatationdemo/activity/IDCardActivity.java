package com.mxx.myimmatationdemo.activity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.mxx.myimmatationdemo.R;

import test.plugin.com.cameralibrary.camera.IDCardCamera;


/**
 * Created by 98179 on 2019/8/27.
 */

public class IDCardActivity extends BaseActivity {
    private ImageView mIvFront;
    private ImageView mIvBack;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_idcard;
    }

    @Override
    protected void initView() {
        super.initView();
        mIvFront = (ImageView) findViewById(R.id.iv_front);
        mIvBack = (ImageView) findViewById(R.id.iv_back);
    }

    @Override
    protected void initData() {
        super.initData();
    }

    /**
     * 身份证正面
     */
    public void front(View view) {
        IDCardCamera.create(this).openCamera(IDCardCamera.TYPE_IDCARD_FRONT);
    }

    /**
     * 身份证反面
     */
    public void back(View view) {
        IDCardCamera.create(this).openCamera(IDCardCamera.TYPE_IDCARD_BACK);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == IDCardCamera.RESULT_CODE) {
            //获取图片路径，显示图片
            final String path = IDCardCamera.getImagePath(data);
            if (!TextUtils.isEmpty(path)) {
                if (requestCode == IDCardCamera.TYPE_IDCARD_FRONT) { //身份证正面
                    mIvFront.setImageBitmap(BitmapFactory.decodeFile(path));
                } else if (requestCode == IDCardCamera.TYPE_IDCARD_BACK) {  //身份证反面
                    mIvBack.setImageBitmap(BitmapFactory.decodeFile(path));
                }
            }
        }
    }

}
