package com.mxx.myimmatationdemo.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.gyf.immersionbar.ImmersionBar;
import com.mxx.myimmatationdemo.OnSplashListener;
import com.mxx.myimmatationdemo.R;
import com.mxx.myimmatationdemo.utils.GlideUtils;
import com.mxx.myimmatationdemo.utils.Utils;


import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


/**
 * @author geyifeng
 * @date 2019-04-22 15:27
 */
public class SplashFragment extends BaseFragment{

//    @BindView(R.id.iv_splash)
    private ImageView ivSplash;
//    @BindView(R.id.tv_time)
    private TextView tvTime;

    private static String mKey = "TotalTime";

    private long mTotalTime = 10;

    private Disposable mSubscribe;
    private OnSplashListener mOnSplashListener;

    private boolean mIsFinish = false;

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopCountDown();
    }

    public static SplashFragment newInstance() {
        return new SplashFragment();
    }

    public static SplashFragment newInstance(long totalTime) {
        SplashFragment splashFragment = new SplashFragment();
        Bundle bundle = new Bundle();
        bundle.putLong(mKey, totalTime);
        splashFragment.setArguments(bundle);
        return splashFragment;
    }

    @Override
    protected void initDataBeforeView(Bundle savedInstanceState) {
        super.initDataBeforeView(savedInstanceState);

        Bundle arguments = getArguments();
        if (arguments != null) {
            mTotalTime = arguments.getLong(mKey);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_splash;
    }

    @Override
    protected void initData() {
        super.initData();

    }

    @Override
    protected void initView() {
        super.initView();
        tvTime = mActivity.findViewById(R.id.tv_time);
        ivSplash = mActivity.findViewById(R.id.iv_splash);

        sendVerfi(tvTime);
        ImmersionBar.setTitleBar(mActivity, tvTime);
        GlideUtils.load(Utils.getFullPic(), ivSplash, R.drawable.pic_all);
    }

    @Override
    protected void setListener() {
        tvTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("setListener","setListener");
                if (mOnSplashListener != null) {
                     mOnSplashListener.onTime(0, mTotalTime);
                }
                finish();
            }
        });
    }

    /**
     * 关闭当前页面
     */
    private void finish() {
        if (getFragmentManager() != null) {
            Fragment fragment = getFragmentManager().findFragmentByTag(mTag);
            if (fragment != null) {
                getFragmentManager().beginTransaction()
                        .setCustomAnimations(R.anim.slide_right_in, R.anim.slide_left_out)
                        .remove(fragment)
                        .commitAllowingStateLoss();
            }
            mOnSplashListener = null;
        }
        mIsFinish = true;
    }

    /**
     * 开启倒计时
     */
    public void sendVerfi(final View view) {

        Observable.interval(0, 1, TimeUnit.SECONDS)
                .take(mTotalTime+1)
                .map(new Function<Long, Long>() {
                    @Override
                    public Long apply(Long aLong) throws Exception {
                        return mTotalTime - aLong;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                    }
                })
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mSubscribe = d;
                    }

                    @Override
                    public void onNext(Long aLong) {
                        tvTime.setText("我是" + aLong + "s欢迎页，点我可以关闭");
                        if (mOnSplashListener != null) {
                            mOnSplashListener.onTime(aLong, mTotalTime);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        finish();
                    }

                    @Override
                    public void onComplete() {
                        finish();
                    }
                });
    }

    /**
     * 关闭倒计时
     */
    private void stopCountDown() {
        if (mSubscribe != null && !mSubscribe.isDisposed()) {
            mSubscribe.dispose();
            mSubscribe = null;
        }
    }


    public void setOnSplashListener(OnSplashListener onSplashListener) {
        this.mOnSplashListener = onSplashListener;
    }

    public boolean isFinish() {
        return mIsFinish;
    }

}
