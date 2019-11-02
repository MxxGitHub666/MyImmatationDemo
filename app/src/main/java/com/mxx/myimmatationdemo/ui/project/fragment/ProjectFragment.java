package com.mxx.myimmatationdemo.ui.project.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

import com.flyco.tablayout.SlidingTabLayout;
import com.mxx.myimmatationdemo.R;
import com.mxx.myimmatationdemo.app.Constants;
import com.mxx.myimmatationdemo.base.fragment.BaseFragment;
import com.mxx.myimmatationdemo.base.fragment.BaseRootFragment;
import com.mxx.myimmatationdemo.component.RxBus;
import com.mxx.myimmatationdemo.contract.project.ProjectContract;
import com.mxx.myimmatationdemo.core.bean.project.ProjectClassifyData;
import com.mxx.myimmatationdemo.core.event.JumpToTheTopEvent;
import com.mxx.myimmatationdemo.presenter.project.ProjectPresenter;
import com.mxx.myimmatationdemo.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ProjectFragment extends BaseRootFragment<ProjectPresenter> implements ProjectContract.View  {


    @BindView(R.id.project_tab_layout)
    SlidingTabLayout mTabLayout;
    @BindView(R.id.project_divider)
    View mDivider;
    @BindView(R.id.project_viewpager)
    ViewPager mViewPager;

    private List<ProjectClassifyData> mData;
    private List<BaseFragment> mFragments = new ArrayList<>();
    private int currentPage;

    public static ProjectFragment getInstance(String param1, String param2) {
        ProjectFragment fragment = new ProjectFragment();
        Bundle args = new Bundle();
        args.putString(Constants.ARG_PARAM1, param1);
        args.putString(Constants.ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onDestroyView() {
        if(mPresenter != null){
            mPresenter.setProjectCurrentPage(currentPage);
        }
        super.onDestroyView();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_project;
    }

    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        mPresenter.getProjectClassifyData();
        currentPage = mPresenter.getProjectCurrentPage();
        if(CommonUtils.isNetworkConnected()){
            showLoading();
        }
    }

    @Override
    public void showProjectClassifyData(List<ProjectClassifyData> projectClassifyDataList) {
        if(mPresenter.getCurrentPage()==Constants.TYPE_PROJECT){
            setChildViewVisibility(View.VISIBLE);
        }else {
            setChildViewVisibility(View.INVISIBLE);
        }

        mData = projectClassifyDataList;
        for (ProjectClassifyData projectClassifyData:mData){
            Log.e("ProjectClassifyData :  ",projectClassifyData.toString());
        }
        initViewPagerAndTabLayout();
        showNormal();

    }

    private void initViewPagerAndTabLayout() {
        for(ProjectClassifyData data : mData){
            ProjectListFragment projectListFragment = ProjectListFragment.getInstance(data.getId(),null);
            mFragments.add(projectListFragment);
        }

        mViewPager.setAdapter(new FragmentStatePagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mData == null? 0 : mData.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return mData.get(position).getName();
            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                currentPage = i;
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        mTabLayout .setViewPager(mViewPager);
        mViewPager.setCurrentItem(Constants.TAB_ONE);
    }

    private void setChildViewVisibility(int visibility) {
        mTabLayout.setVisibility(visibility);
        mDivider.setVisibility(visibility);
        mViewPager.setVisibility(visibility);
    }


    public void jumpToTheTop() {
        if (mFragments != null) {
            RxBus.getDefault().post(new JumpToTheTopEvent());
        }
    }

}
