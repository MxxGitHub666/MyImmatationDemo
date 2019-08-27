package com.mxx.myimmatationdemo.activity;


import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.annotation.Px;

import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.gyf.immersionbar.ImmersionBar;
import com.mxx.myimmatationdemo.AppManager;
import com.mxx.myimmatationdemo.OnSplashListener;
import com.mxx.myimmatationdemo.R;
import com.mxx.myimmatationdemo.adapter.SubPagerAdapter;
import com.mxx.myimmatationdemo.fragment.BaseFragment;
import com.mxx.myimmatationdemo.fragment.SplashFragment;
import com.mxx.myimmatationdemo.fragment.three.CategoryThreeFragment;
import com.mxx.myimmatationdemo.fragment.three.HomeThreeFragment;
import com.mxx.myimmatationdemo.fragment.three.MineThreeFragment;
import com.mxx.myimmatationdemo.fragment.three.VideoThreeFragment;
import com.mxx.myimmatationdemo.utils.GlideUtils;
import com.mxx.myimmatationdemo.utils.Utils;
import com.mxx.myimmatationdemo.view.CustomViewPager;

import java.util.ArrayList;
import java.util.List;

import test.plugin.com.mylibrary.MPermission;
import test.plugin.com.mylibrary.PermissGroup;
import test.plugin.com.mylibrary.listener.PermissListener;
import test.plugin.com.mylibrary.request.PermissionRequest;

import static android.os.Build.VERSION_CODES.M;
import static android.os.Build.VERSION_CODES.P;
import static com.mxx.myimmatationdemo.R.id.iv_bg;


/**
 * Created by 98179 on 2019/6/24.
 */

public class MainActivity extends BaseActivity implements DrawerLayout.DrawerListener, View.OnClickListener, ViewPager.OnPageChangeListener {

    public DrawerLayout drawer;
    private ImageView ivBg;
    private CustomViewPager viewPager;
    private LinearLayout llHome;
    private LinearLayout llContacts;
    private LinearLayout llVideo;
    private LinearLayout llMine;
    private ArrayList<BaseFragment> mFragments;
    private long mFirstPressedTime;
    private List<String> titles = new ArrayList<>();
    private Context context;
    private RelativeLayout rl_idCard;

//    String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE,
//            Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO};

//    // 声明一个集合，在后面的代码中用来存储用户拒绝授权的权
//    List<String> mPermissionList = new ArrayList<>();
    /**
     * splash页面
     */
    private SplashFragment mSplashFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_fragment_one;
    }

    @Override
    protected void initView() {
        super.initView();
        drawer = findViewById(R.id.drawerLayout);
        ivBg = findViewById(iv_bg);
        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

        showSplash();
        viewPager = findViewById(R.id.viewPager);
        llHome = findViewById(R.id.ll_home);
        llContacts = findViewById(R.id.ll_contacts);
        llMine = findViewById(R.id.ll_mine);
        llVideo = findViewById(R.id.ll_video);
        rl_idCard = findViewById(R.id.rl_idCard);

        SubPagerAdapter pagerAdapter = new SubPagerAdapter(getSupportFragmentManager());
        pagerAdapter.setData(mFragments, titles);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOffscreenPageLimit(4);
        llHome.setSelected(true);
    }

    @Override
    protected void initData() {
        super.initData();
        mFragments = new ArrayList<>();
        HomeThreeFragment homeThreeFragment = new HomeThreeFragment();
        CategoryThreeFragment categoryThreeFragment = new CategoryThreeFragment();
        VideoThreeFragment serviceThreeFragment = new VideoThreeFragment();
        MineThreeFragment mineThreeFragment = new MineThreeFragment();
        mFragments.add(homeThreeFragment);
        mFragments.add(categoryThreeFragment);
        mFragments.add(serviceThreeFragment);
        mFragments.add(mineThreeFragment);

        titles.add("首页");
        titles.add("通讯录");
        titles.add("视频");
        titles.add("我的");

//        MPermission.with(mActivity).permiss().permission(PermissGroup.CAMERA,PermissGroup.READ_EXTERNAL_STORAGE,PermissGroup.WRITE_EXTERNAL_STORAGE,PermissGroup.RECORD_AUDIO).listener(new PermissListener<String>() {
//            @Override
//            public void onGranted(List<String> list) {
//                Toast.makeText(mActivity,"权限申请成功",Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onDenied(List<String> list) {
//                Toast.makeText(mActivity,"权限被拒绝",Toast.LENGTH_SHORT).show();
//            }
//        }).start();


//        for (int i = 0; i < permissions.length; i++) {
////            if (Build.VERSION.SDK_INT >= 23) {
//                if (ContextCompat.checkSelfPermission(this, permissions[i]) != PackageManager.PERMISSION_GRANTED) {
//                    mPermissionList.add(permissions[i]);
////                }
//            }
//
//            //未授予的权限为空，表示都授予了
//            if (mPermissionList.isEmpty()) {
//
//            } else {
//                String[] permissions = mPermissionList.toArray(new String[mPermissionList.size()]);
//                ActivityCompat.requestPermissions(MainActivity.this, permissions, 100);
//            }
//        }

    }


//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        if (requestCode == 100) {
//            for (int i = 0; i < grantResults.length; i++) {
//                if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
//                    boolean showRequestPermission = ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[i]);
//                    if (showRequestPermission) {
//                        showPermissionDialog();
//                    }
//                }
//            }
//        }
//
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        ImmersionBar.with(this).navigationBarColor(R.color.colorPrimary).init();
    }


    @Override
    protected void setListener() {
        super.setListener();
        drawer.addDrawerListener(this);
        llHome.setOnClickListener(this);
        llContacts.setOnClickListener(this);
        llVideo.setOnClickListener(this);
        llMine.setOnClickListener(this);
        viewPager.addOnPageChangeListener(this);
        ivBg.setOnClickListener(this);
        rl_idCard.setOnClickListener(this);
    }


    /**
     * 展示Splash
     */
    private void showSplash() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        mSplashFragment = (SplashFragment) getSupportFragmentManager().findFragmentByTag(SplashFragment.class.getSimpleName());
        if (mSplashFragment != null) {
            if (mSplashFragment.isAdded()) {
                transaction.show(mSplashFragment).commitAllowingStateLoss();
            } else {
                transaction.remove(mSplashFragment).commitAllowingStateLoss();
                mSplashFragment = SplashFragment.newInstance();
                transaction.add(R.id.fl_content, mSplashFragment, SplashFragment.class.getSimpleName()).commitAllowingStateLoss();
            }
        } else {
            mSplashFragment = SplashFragment.newInstance();
            transaction.add(R.id.fl_content, mSplashFragment, SplashFragment.class.getSimpleName()).commitAllowingStateLoss();
        }
        mSplashFragment.setOnSplashListener(new OnSplashListener() {
            @Override
            public void onTime(long time, long totalTime) {
                if (time != 0) {
                    drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                } else {
                    drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
                }
            }
        });
    }

    @Override
    public void onDrawerSlide(@NonNull View view, float v) {

    }

    @Override
    public void onDrawerOpened(@NonNull View view) {

    }

    @Override
    public void onDrawerClosed(@NonNull View view) {
        GlideUtils.loadBlurry(ivBg, Utils.getPic());

    }

    @Override
    public void onDrawerStateChanged(int i) {

    }

    @Override
    public void onPageScrolled(int i, float v, @Px int i1) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                tabSelected(llHome);
                ImmersionBar.with(this).keyboardEnable(false).statusBarDarkFont(false).navigationBarColor(R.color.colorPrimary).init();
                break;
            case 1:
                tabSelected(llContacts);
                ImmersionBar.with(this).keyboardEnable(false).statusBarDarkFont(true, 0.2f).navigationBarColor(R.color.btn3).init();
                break;
            case 2:
                tabSelected(llVideo);
                ImmersionBar.with(this).keyboardEnable(false).statusBarDarkFont(true, 0.2f).navigationBarColor(R.color.btn13).init();
                break;
            case 3:
                tabSelected(llMine);
                ImmersionBar.with(this).keyboardEnable(true).statusBarDarkFont(true).navigationBarColor(R.color.btn1).init();
                break;
            default:
                break;
        }


    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_home:
                viewPager.setCurrentItem(0);
                tabSelected(llHome);
                break;
            case R.id.ll_contacts:
                viewPager.setCurrentItem(1);
                tabSelected(llContacts);
                break;
            case R.id.ll_video:
                viewPager.setCurrentItem(2);
                tabSelected(llVideo);
                break;
            case R.id.ll_mine:
                viewPager.setCurrentItem(3);
                tabSelected(llMine);
                break;
            case R.id.iv_bg:
                Intent intent = new Intent(this, NewsActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_idCard:
                Intent intent2 = new Intent(this, IDCardActivity.class);
                startActivity(intent2);
                break;
            default:
                break;
        }
    }

    private void tabSelected(LinearLayout linearLayout) {
        llHome.setSelected(false);
        llContacts.setSelected(false);
        llVideo.setSelected(false);
        llMine.setSelected(false);
        linearLayout.setSelected(true);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (mSplashFragment != null) {
                if (mSplashFragment.isFinish()) {
                    if (System.currentTimeMillis() - mFirstPressedTime < 2000) {
                        super.onBackPressed();
                        AppManager.getInstance().removeAllActivity();
                    } else {
                        Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
                        mFirstPressedTime = System.currentTimeMillis();
                    }
                } else {
                    super.onBackPressed();
                    AppManager.getInstance().removeAllActivity();
                }
            } else {
                super.onBackPressed();
                AppManager.getInstance().removeAllActivity();
            }
        }
    }

//    AlertDialog mPermissionDialog;
//    String mPackName = "com.mxx.myimmatationdemo";
//
//    public void showPermissionDialog() {
//        if (mPermissionDialog == null) {
//            mPermissionDialog = new AlertDialog.Builder(this)
//                    .setMessage("已禁用权限，请手动授予")
//                    .setPositiveButton("设置", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            cancelPermissionDialog();
//
//                            Intent localIntent = new Intent("miui.intent.action.APP_PERM_EDITOR");
//                            localIntent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.PermissionsEditorActivity");
//                            localIntent.putExtra("extra_pkgname", mPackName);
//                            startActivity(localIntent);
//
//                        }
//                    })
//                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            //关闭页面或者做其他操作
//                            cancelPermissionDialog();
//                        }
//                    }).create();
//
//        }
//        mPermissionDialog.show();
//    }
//
//    //关闭对话框
//    private void cancelPermissionDialog() {
//        mPermissionDialog.cancel();
//    }


}
