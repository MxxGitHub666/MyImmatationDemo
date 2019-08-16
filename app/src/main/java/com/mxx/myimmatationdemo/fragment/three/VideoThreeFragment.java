package com.mxx.myimmatationdemo.fragment.three;


import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;


import com.mxx.myimmatationdemo.R;
import com.mxx.myimmatationdemo.adapter.SubPagerAdapter;
import com.mxx.myimmatationdemo.fragment.BaseFragment;
import com.mxx.myimmatationdemo.fragment.LazyLoadFragment;
import com.mxx.myimmatationdemo.fragment.video.CartoonFragment;
import com.mxx.myimmatationdemo.fragment.video.MovieFragment;
import com.mxx.myimmatationdemo.fragment.video.TVFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author geyifeng
 * @date 2017/5/12
 */
public class VideoThreeFragment extends LazyLoadFragment {
    private String[] mTabName = {"电影","电视剧","动漫"};
    private List<BaseFragment> fragmentList = new ArrayList<>();
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private List<String> titles = new ArrayList<>();


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_one_service;

    }

    @Override
    protected void initData() {
        super.initData();
        fragmentList.add(new TVFragment());
        fragmentList.add(new MovieFragment());
        fragmentList.add(new CartoonFragment());
        titles.add("电视剧");
        titles.add("电影");
        titles.add("动漫");


    }

    @Override
    protected void initView() {
        super.initView();

        tabLayout = getActivity().findViewById(R.id.tl_list);
        viewPager = getActivity().findViewById(R.id.vp_video);


        SubPagerAdapter pagerAdapter = new SubPagerAdapter(getChildFragmentManager());
        pagerAdapter.setData(fragmentList,titles);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOffscreenPageLimit(3);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                toolbar.setTitle(tab.getText());
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    @Override
    protected void loadData() {

    }
}
