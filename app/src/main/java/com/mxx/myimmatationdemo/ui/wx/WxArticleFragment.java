package com.mxx.myimmatationdemo.ui.wx;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.flyco.tablayout.SlidingTabLayout;
import com.mxx.myimmatationdemo.R;
import com.mxx.myimmatationdemo.app.Constants;
import com.mxx.myimmatationdemo.base.fragment.BaseFragment;
import com.mxx.myimmatationdemo.base.fragment.BaseRootFragment;
import com.mxx.myimmatationdemo.component.RxBus;
import com.mxx.myimmatationdemo.contract.wx.WxContract;
import com.mxx.myimmatationdemo.core.bean.wx.WxAuthor;
import com.mxx.myimmatationdemo.core.event.JumpToTheTopEvent;
import com.mxx.myimmatationdemo.presenter.wx.WxArticlePresenter;
import com.mxx.myimmatationdemo.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class WxArticleFragment extends BaseRootFragment<WxArticlePresenter> implements WxContract.View {
    @BindView(R.id.wx_detail_tab_layout)
    SlidingTabLayout mTabLayout;
    @BindView(R.id.wx_detail_viewpager)
    ViewPager mViewPager;

    private List<BaseFragment> mFragments = new ArrayList<>();

    public static WxArticleFragment getInstance(String param1, String param2) {
        WxArticleFragment fragment = new WxArticleFragment();
        Bundle args = new Bundle();
        args.putString(Constants.ARG_PARAM1, param1);
        args.putString(Constants.ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_wx_article;
    }

    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        mPresenter.getWxAuthorListData();
        if (CommonUtils.isNetworkConnected()) {
            showLoading();
        }
    }


    private void initViewPagerAndTabLayout(List<WxAuthor> wxAuthors) {
        mViewPager.setAdapter(new FragmentStatePagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments == null? 0 : mFragments.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return wxAuthors.get(position).getName();
            }
        });
        mTabLayout.setViewPager(mViewPager);
    }

    @Override
    public void reload() {
        if (mPresenter != null && mTabLayout.getVisibility() == View.INVISIBLE) {
            mPresenter.getWxAuthorListData();
        }
    }
    @Override
    public void showWxAuthorListView(List<WxAuthor> wxAuthors) {
        if (mPresenter.getCurrentPage() == Constants.TYPE_WX_ARTICLE) {
            setChildViewVisibility(View.VISIBLE);
        } else {
            setChildViewVisibility(View.INVISIBLE);
        }
        mFragments.clear();
        for (WxAuthor wxAuthor : wxAuthors) {
            mFragments.add(WxDetailArticleFragment.getInstance(wxAuthor.getId(), wxAuthor.getName()));
        }
        initViewPagerAndTabLayout(wxAuthors);
        showNormal();
    }
    @Override
    public void showError() {
        setChildViewVisibility(View.INVISIBLE);
        super.showError();
    }

    private void setChildViewVisibility(int visible) {
        mTabLayout.setVisibility(visible);
        mViewPager.setVisibility(visible);
    }

    public void jumpToTheTop() {
        if (mFragments != null) {
            RxBus.getDefault().post(new JumpToTheTopEvent());
        }
    }

}
