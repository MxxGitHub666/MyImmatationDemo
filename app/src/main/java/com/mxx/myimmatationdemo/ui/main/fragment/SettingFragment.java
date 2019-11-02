package com.mxx.myimmatationdemo.ui.main.fragment;

import android.os.Bundle;
import android.support.v7.widget.AppCompatCheckBox;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mxx.myimmatationdemo.R;
import com.mxx.myimmatationdemo.app.Constants;
import com.mxx.myimmatationdemo.base.fragment.BaseRootFragment;
import com.mxx.myimmatationdemo.component.ACache;
import com.mxx.myimmatationdemo.component.RxBus;
import com.mxx.myimmatationdemo.contract.main.SettingContract;
import com.mxx.myimmatationdemo.core.event.NightModeEvent;
import com.mxx.myimmatationdemo.presenter.main.SettingPresenter;
import com.mxx.myimmatationdemo.utils.ShareUtil;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;

public class SettingFragment extends BaseRootFragment<SettingPresenter> implements SettingContract.View,CompoundButton.OnCheckedChangeListener {

    @BindView(R.id.cb_setting_cache)
    AppCompatCheckBox mCbSettingCache;
    @BindView(R.id.cb_setting_image)
    AppCompatCheckBox mCbSettingImage;
    @BindView(R.id.cb_setting_night)
    AppCompatCheckBox mCbSettingNight;
    @BindView(R.id.ll_setting_feedback)
    TextView mLlSettingFeedback;
    @BindView(R.id.ll_setting_clear)
    LinearLayout mLlSettingClear;
    @BindView(R.id.tv_setting_clear)
    TextView mTvSettingClear;

    private File cacheFile;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_setting;
    }

    public static SettingFragment getInstance(String param1, String param2) {
        SettingFragment fragment = new SettingFragment();
        Bundle args = new Bundle();
        args.putString(Constants.ARG_PARAM1, param1);
        args.putString(Constants.ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initEventAndData() {
      cacheFile = new File(Constants.PATH_CACHE);
        mTvSettingClear.setText(ACache.getCacheSize(cacheFile));
        mCbSettingCache.setChecked(mPresenter.getAutoCacheState());
        mCbSettingImage.setChecked(mPresenter.getNoImageState());
        mCbSettingNight.setChecked(mPresenter.getNightModeState());
        mCbSettingCache.setOnCheckedChangeListener(this);
        mCbSettingImage.setOnCheckedChangeListener(this);
        mCbSettingNight.setOnCheckedChangeListener(this);

    }


    @OnClick({R.id.ll_setting_feedback, R.id.ll_setting_clear})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_setting_feedback:
                ShareUtil.sendEmail(_mActivity, getString(R.string.send_email));
                break;
            case R.id.ll_setting_clear:
                clearCache();
                break;
            default:
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        switch (compoundButton.getId()) {
            case R.id.cb_setting_night:
                mPresenter.setNightModeState(b);
                RxBus.getDefault().post(new NightModeEvent(b));
                break;
            case R.id.cb_setting_image:
                mPresenter.setNoImageState(b);
                break;
            case R.id.cb_setting_cache:
                mPresenter.setAutoCacheState(b);
                break;
            default:
                break;
        }
    }

    private void clearCache() {
        ACache.deleteDir(cacheFile);
        mTvSettingClear.setText(ACache.getCacheSize(cacheFile));
    }
}
