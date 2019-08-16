package com.mxx.myimmatationdemo.activity;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.gyf.immersionbar.ImmersionBar;
import com.mxx.myimmatationdemo.R;
import com.mxx.myimmatationdemo.utils.ChronometerViewModel;
import com.mxx.myimmatationdemo.utils.Utils;


/**
 * @author gyf
 * @date 2016/10/24
 */
public class PicAndColorActivity extends BaseActivity implements SeekBar.OnSeekBarChangeListener,View.OnClickListener {

    private Toolbar toolbar;
    private SeekBar seekBar;
    private ImageView mIv;
    private Button btn_status_color;
    private Button btn_navigation_color;
    private Button btn_color;


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_pic_color;
    }


    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        ImmersionBar.with(this).statusBarView(R.id.top_view)
                .navigationBarColor(R.color.colorPrimary)
                .fullScreen(true)
                .addTag("PicAndColor")
                .init();
    }

    @Override
    protected void initView() {
        super.initView();

        toolbar = findViewById(R.id.toolbar);
        seekBar = (SeekBar)findViewById(R.id.seek_bar);
        mIv = findViewById(R.id.mIv);
        btn_status_color = findViewById(R.id.btn_status_color);
        btn_navigation_color = findViewById(R.id.btn_navigation_color);
        btn_color = findViewById(R.id.btn_color);

        btn_color.setOnClickListener(this);
        btn_navigation_color.setOnClickListener(this);
        btn_status_color.setOnClickListener(this);

        Glide.with(this).asBitmap().load(Utils.getPic())
                .apply(new RequestOptions().placeholder(R.mipmap.test))
                .into(mIv);
    }

    @Override
    protected void setListener() {
        seekBar.setOnSeekBarChangeListener(this);
    }


    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        float alpha = (float) progress / 100;
        ImmersionBar.with(this)
                .statusBarColorTransform(R.color.orange)
                .navigationBarColorTransform(R.color.tans)
                .addViewSupportTransformColor(toolbar)
                .barAlpha(alpha)
                .init();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_status_color:
                Toast.makeText(PicAndColorActivity.this,"条条目", Toast.LENGTH_SHORT).show();
                ImmersionBar.with(this).statusBarColor(R.color.colorAccent).init();
                break;
            case R.id.btn_navigation_color:
                if (ImmersionBar.hasNavigationBar(this)) {
                    ImmersionBar.with(this).navigationBarColor(R.color.colorAccent).init();
                } else {
                    Toast.makeText(this, "当前设备没有导航栏", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_color:
                ImmersionBar.with(this).getTag("PicAndColor").init();
                break;
            default:
                break;
        }
    }
}
