package com.mxx.myimmatationdemo.activity;

import android.support.annotation.Px;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;

import com.gyf.immersionbar.ImmersionBar;
import com.mxx.myimmatationdemo.R;
import com.mxx.myimmatationdemo.fragment.three.CategoryThreeFragment;
import com.mxx.myimmatationdemo.fragment.three.HomeThreeFragment;
import com.mxx.myimmatationdemo.fragment.three.MineThreeFragment;
import com.mxx.myimmatationdemo.fragment.three.VideoThreeFragment;
import com.mxx.myimmatationdemo.view.CustomViewPager;

import java.util.ArrayList;

/**
 * Created by 98179 on 2019/6/18.
 */

public class FragmentThreeActivity extends BaseActivity implements View.OnClickListener, ViewPager.OnPageChangeListener{
    CustomViewPager viewPager;
    LinearLayout llHome;
    LinearLayout llCategory;
    LinearLayout llService;
    LinearLayout llMine;
    private ArrayList<Fragment> mFragments;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_fragment_one;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        ImmersionBar.with(this).navigationBarColor(R.color.colorPrimary).init();
    }

    @Override
    protected void initView() {
        super.initView();
        viewPager = findViewById(R.id.viewPager);
        llHome = findViewById(R.id.ll_home);
        llCategory = findViewById(R.id.ll_contacts);
        llMine = findViewById(R.id.ll_mine);
        llService = findViewById(R.id.ll_video);

        viewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
        viewPager.setOffscreenPageLimit(3);
        llHome.setSelected(true);

    }

    @Override
    protected void initData() {
        mFragments = new ArrayList<>();
        HomeThreeFragment homeThreeFragment = new HomeThreeFragment();
        CategoryThreeFragment categoryThreeFragment = new CategoryThreeFragment();
        VideoThreeFragment serviceThreeFragment = new VideoThreeFragment();
        MineThreeFragment mineThreeFragment = new MineThreeFragment();
        mFragments.add(homeThreeFragment);
        mFragments.add(categoryThreeFragment);
        mFragments.add(serviceThreeFragment);
        mFragments.add(mineThreeFragment);
    }
    @Override
    protected void setListener() {
        llHome.setOnClickListener(this);
        llCategory.setOnClickListener(this);
        llService.setOnClickListener(this);
        llMine.setOnClickListener(this);
        viewPager.addOnPageChangeListener(this);
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
                tabSelected(llCategory);
                ImmersionBar.with(this).keyboardEnable(false).statusBarDarkFont(true, 0.2f).navigationBarColor(R.color.btn3).init();
                break;
            case 2:
                tabSelected(llService);
                ImmersionBar.with(this).keyboardEnable(false).statusBarDarkFont(false).navigationBarColor(R.color.btn13).init();
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
                tabSelected(llCategory);
                break;
            case R.id.ll_video:
                viewPager.setCurrentItem(2);
                tabSelected(llService);
                break;
            case R.id.ll_mine:
                viewPager.setCurrentItem(3);
                tabSelected(llMine);
                break;
            default:
                break;
        }

    }
    private void tabSelected(LinearLayout linearLayout) {
        llHome.setSelected(false);
        llCategory.setSelected(false);
        llService.setSelected(false);
        llMine.setSelected(false);
        linearLayout.setSelected(true);
    }


    private class MyAdapter extends FragmentPagerAdapter {
        MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }
    }


}
