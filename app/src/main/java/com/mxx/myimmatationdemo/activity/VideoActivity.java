package com.mxx.myimmatationdemo.activity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mxx.myimmatationdemo.R;
import com.mxx.myimmatationdemo.myinterface.IAutoFocus;

import com.mxx.myimmatationdemo.view.CameraPreview2;
import com.mxx.myimmatationdemo.view.RectOnCamera;


/**
 * Created by 98179 on 2019/8/23.
 */

public class VideoActivity extends BaseActivity implements IAutoFocus {
    private Button btnTakePicture;
    private CameraPreview2 mPreview;
    private TextView tv_switch;
    private TextView tv_back;
    private int currentCameraType = 1;
    private RectOnCamera recton_camera;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_video;
    }



    @Override
    protected void initData() {
        super.initData();

//        // 全屏显示
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


    }

    @Override
    protected void initView() {
        super.initView();
        mPreview = findViewById(R.id.cp_camera);
        recton_camera = findViewById(R.id.recton_camera);
        btnTakePicture = findViewById(R.id.btn_take_picture);
        tv_back = findViewById(R.id.tv_back);
        tv_switch = findViewById(R.id.tv_switch);

        recton_camera.setIAutoFocus(this);
    }

    @Override
    protected void setListener() {
        super.setListener();
        btnTakePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPreview.takePicture();
            }
        });

        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    @Override
    public void autoFocus() {
        if(mPreview!= null)
         mPreview.setAutoFocus();
    }
}
