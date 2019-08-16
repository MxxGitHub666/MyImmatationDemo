package com.mxx.myimmatationdemo.fragment.three;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.ColorUtils;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.gyf.immersionbar.ImmersionBar;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.mxx.myimmatationdemo.R;
import com.mxx.myimmatationdemo.activity.MainActivity;
import com.mxx.myimmatationdemo.activity.WebActivity;
import com.mxx.myimmatationdemo.adapter.OneAdapter;
import com.mxx.myimmatationdemo.bean.TechnologyBean;
import com.mxx.myimmatationdemo.fragment.BaseFragment;
import com.mxx.myimmatationdemo.fragment.LazyLoadFragment;
import com.mxx.myimmatationdemo.myinterface.RequestCallBack;
import com.mxx.myimmatationdemo.utils.GlideImageLoader;
import com.mxx.myimmatationdemo.utils.RequestMethod;
import com.mxx.myimmatationdemo.utils.Utils;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;


/**
 * @author geyifeng
 * @date 2017/5/12
 */
public class HomeThreeFragment extends LazyLoadFragment implements View.OnClickListener {

    private static final String DATA = "data";
    private Toolbar mToolbar;
    private RecyclerView mRv;
    private TwinklingRefreshLayout refreshLayout;
    private LinearLayout llScan;
    private OneAdapter mOneAdapter;
    private List<TechnologyBean> mItemList = new ArrayList<>();
    private List<String> mImages = new ArrayList<>();
    private int bannerHeight;
    private int page = 1;
    private Handler handler;
    private List<TechnologyBean> technologyBeanArrayList = new ArrayList<>();


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_one_home;
    }

    @Override
    protected void initData() {
        mImages = Utils.getPics();

//        RequestMethod.getData(page, new RequestCallBack() {
//            @Override
//            public void success(List<TechnologyBean> data) {
//                mOneAdapter.setNewData(data);
//            }
//
//            @Override
//            public void success(String data) {
//
//            }
//
//            @Override
//            public void fail(Exception e) {
//
//            }
//        });

    }

    @Override
    protected void initView() {
        mToolbar = mActivity.findViewById(R.id.toolbar);
        mRv = mActivity.findViewById(R.id.rv);
        refreshLayout = mActivity.findViewById(R.id.refreshLayout);
        llScan = mActivity.findViewById(R.id.llScan);
        refreshLayout.setEnableLoadmore(false);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);
        mRv.setLayoutManager(linearLayoutManager);
        mRv.addItemDecoration(new DividerItemDecoration(mActivity,DividerItemDecoration.VERTICAL));
        mOneAdapter = new OneAdapter();
        mOneAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        mRv.setAdapter(mOneAdapter);
        addHeaderView();
        mOneAdapter.setNewData(technologyBeanArrayList);
    }

    private void addHeaderView() {
        if (mImages != null && mImages.size() > 0) {
            View headView = LayoutInflater.from(mActivity).inflate(R.layout.item_banner, (ViewGroup) mRv.getParent(), false);
            Banner banner = headView.findViewById(R.id.banner);
            banner.setImages(mImages)
                    .setImageLoader(new GlideImageLoader())
                    .setDelayTime(5000)
                    .start();
            mOneAdapter.addHeaderView(headView);
            ViewGroup.LayoutParams bannerParams = banner.getLayoutParams();
            ViewGroup.LayoutParams titleBarParams = mToolbar.getLayoutParams();
            bannerHeight = bannerParams.height - titleBarParams.height - ImmersionBar.getStatusBarHeight(mActivity);
        }
    }

    @Override
    protected void setListener() {
        llScan.setOnClickListener(this);
        mRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            private int totalDy = 0;

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalDy += dy;
                if (totalDy <= bannerHeight) {
                    float alpha = (float) totalDy / bannerHeight;
                    mToolbar.setBackgroundColor(ColorUtils.blendARGB(Color.TRANSPARENT
                            , ContextCompat.getColor(mActivity, R.color.colorPrimary), alpha));
                } else {
                    mToolbar.setBackgroundColor(ColorUtils.blendARGB(Color.TRANSPARENT
                            , ContextCompat.getColor(mActivity, R.color.colorPrimary), 1));
                }
            }
        });



//        mOneAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener(){
//            @Override
//            public void onLoadMoreRequested() {
//
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        page ++;
//                        RequestMethod.getData(page, new RequestCallBack() {
//                            @Override
//                            public void success(List<TechnologyBean> data) {
//                                mOneAdapter.addData(data);
//                                if(mOneAdapter.getItemCount()>=200){
//                                    mOneAdapter.loadMoreEnd(true);
//                                }else {
//                                    mOneAdapter.loadMoreComplete();
//                                }
//                            }
//
//                            @Override
//                            public void success(String data) {
//
//                            }
//
//
//                            @Override
//                            public void fail(Exception e) {
//                                Toast.makeText(mActivity, "load fail", Toast.LENGTH_LONG).show();
//                            }
//                        });
//                    }
//                },1000);
//            }
//        });
//        refreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
//            @Override
//            public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        page = 1;
//                        RequestMethod.getData(page, new RequestCallBack() {
//                            @Override
//                            public void success(List<TechnologyBean> data) {
//                                mOneAdapter.setNewData(data);
//                                refreshLayout.finishRefreshing();
//                                if (mToolbar != null) {
//                                    mToolbar.setVisibility(View.VISIBLE);
//                                }
//                            }
//
//                            @Override
//                            public void success(String data) {
//
//                            }
//
//                            @Override
//                            public void fail(Exception e) {
//                                Toast.makeText(mActivity, "refresh fail", Toast.LENGTH_LONG).show();
//                            }
//                        });
//                    }
//                },1000);
//
//            }
//
//            @Override
//            public void onPullingDown(TwinklingRefreshLayout refreshLayout, float fraction) {
//                if (mToolbar != null) {
//                    mToolbar.setVisibility(View.GONE);
//                }
//            }
//
//            @Override
//            public void onPullDownReleasing(TwinklingRefreshLayout refreshLayout, float fraction) {
//                if (mToolbar != null) {
//                    if (Math.abs(fraction - 1.0f) > 0) {
//                        mToolbar.setVisibility(View.VISIBLE);
//                    } else {
//                        mToolbar.setVisibility(View.GONE);
//                    }
//                }
//            }
//        });


        mOneAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                TechnologyBean technologyBean = (TechnologyBean) adapter.getData().get(position);
                Log.e("adapter",technologyBean.toString());
                Intent intent = new Intent(mActivity, WebActivity.class);
                intent.putExtra("url", technologyBean.getUrl());
                mActivity.startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.llScan:
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.drawer.openDrawer(Gravity.START);
                break;
        }
    }

    @Override
    protected void loadData() {
        RequestMethod.getHomeData("https://blog.csdn.net/nav/career", new RequestCallBack() {
            @Override
            public void success(List data) {
                Log.e("data",data.size()+"");
                mOneAdapter.setNewData(data);
            }

            @Override
            public void success(String data) {

            }

            @Override


            public void fail(Exception e) {

            }
        });
    }
}
