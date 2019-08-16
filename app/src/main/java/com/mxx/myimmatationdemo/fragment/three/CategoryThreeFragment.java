package com.mxx.myimmatationdemo.fragment.three;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;


import com.mxx.myimmatationdemo.R;
import com.mxx.myimmatationdemo.adapter.NewsAdapter;
import com.mxx.myimmatationdemo.fragment.BaseFragment;
import com.mxx.myimmatationdemo.fragment.LazyLoadFragment;


/**
 * @author geyifeng
 * @date 2017/5/12
 */
public class CategoryThreeFragment extends LazyLoadFragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    private String[] mTabName = {"关注", "头条", "视频", "娱乐", "体育", "新时代", "要闻", "知否", "段子", "本地", "公开课", "财经", "科技", "汽车"};

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_three_category;
    }

    @Override
    protected void initView() {
        super.initView();
        tabLayout = (TabLayout) mActivity.findViewById(R.id.tabLayout);
        viewPager =(ViewPager) mActivity.findViewById(R.id.viewPager);
        for (String s : mTabName) {
            tabLayout.addTab(tabLayout.newTab().setText(s));
        }

//        NewsAdapter newsAdapter = new NewsAdapter(getChildFragmentManager(), mTabName);
//        viewPager.setAdapter(newsAdapter);
//        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected void loadData() {

    }
}
