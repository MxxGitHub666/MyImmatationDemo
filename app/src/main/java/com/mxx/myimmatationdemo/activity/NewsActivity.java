package com.mxx.myimmatationdemo.activity;


import android.annotation.SuppressLint;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gyf.immersionbar.BarProperties;
import com.gyf.immersionbar.ImmersionBar;
import com.gyf.immersionbar.OnBarListener;
import com.mxx.myimmatationdemo.AppManager;
import com.mxx.myimmatationdemo.OnSplashListener;

import com.mxx.myimmatationdemo.R;
import com.mxx.myimmatationdemo.adapter.BannerAdapter;
import com.mxx.myimmatationdemo.adapter.MainAdapter;

import com.mxx.myimmatationdemo.bean.News;
import com.mxx.myimmatationdemo.event.NetworkEvent;
import com.mxx.myimmatationdemo.fragment.SplashFragment;
import com.mxx.myimmatationdemo.utils.DensityUtil;
import com.mxx.myimmatationdemo.utils.GlideUtils;
import com.mxx.myimmatationdemo.utils.Utils;
import com.mxx.myimmatationdemo.utils.ViewUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class NewsActivity extends BaseActivity  implements DrawerLayout.DrawerListener {

    private  MainAdapter mainAdapter;
    private  RecyclerView recyclerView;
    private ImageView mIvBanner;
    private Toolbar toolbar;
    private ImageView ivBg;
    private int mBannerHeight;
    private LinearLayoutManager mLayoutManager;
    private BannerAdapter mBannerAdapter;
    private List list;
    private LinearLayout ll_01;
    private DrawerLayout drawer;
    private Handler handler;
    private long mFirstPressedTime;
    private List<News> newsList = new ArrayList<>();
    private View mNetworkView;
    /**
     * splash页面
     */
    private SplashFragment mSplashFragment;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void initView() {
        super.initView();

        recyclerView = (RecyclerView)findViewById(R.id.rl);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        status_bar_view = findViewById(R.id.status_bar_view);
        drawer = findViewById(R.id.drawer);
        ivBg = findViewById(R.id.iv_bg);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

//        showSplash();
        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        mainAdapter = new MainAdapter(R.layout.item_fun,newsList);
        mainAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        mainAdapter.isFirstOnly(false);
        recyclerView.setAdapter(mainAdapter);
        addHeaderView();
        mBannerHeight = DensityUtil.dip2px(this,180) - ImmersionBar.getStatusBarHeight(this);

    }

    @Override
    protected void initData() {
        super.initData();

        getNews();

        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {

                if(msg.what ==1){
                    mainAdapter.notifyDataSetChanged();
                }

            }
        };


    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        ImmersionBar.with(this).titleBar(R.id.toolbar).setOnBarListener(new OnBarListener() {
            @Override
            public void onBarChange(BarProperties barProperties) {
                adjustView(barProperties);
            }
        }).init();
    }

//    @OnClick({R.id.ll_github, R.id.ll_jianshu})
//    public void onClick(View view) {
//        Intent intent = null;
//        switch (view.getId()) {
//            case R.id.ll_github:
//                intent = new Intent(this, BlogActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putString("blog", "github");
//                intent.putExtra("bundle", bundle);
//                break;
//            case R.id.ll_jianshu:
//                intent = new Intent(this, BlogActivity.class);
//                Bundle bundle2 = new Bundle();
//                bundle2.putString("blog", "jianshu");
//                intent.putExtra("bundle", bundle2);
//                break;
//            default:
//                break;
//        }
//        if (intent != null) {
//            startActivity(intent);
//        }
//    }
    @Override
    protected void setListener() {
        super.setListener();
        drawer.addDrawerListener(this);
        mainAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(NewsActivity.this, "点击了第" + (position + 1) + "条条目", Toast.LENGTH_SHORT).show();
                Intent intent;
                switch (position){
                    case 0:
                         intent = new Intent(NewsActivity.this,SlipActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                         intent = new Intent(NewsActivity.this,PicAndColorActivity.class);
                        startActivity(intent);
                        break;
                    case 2:
                        intent = new Intent(NewsActivity.this,PicActivity.class);
                        startActivity(intent);
                        break;
                    case 3:
                        intent = new Intent(NewsActivity.this,FragmentThreeActivity.class);
                        startActivity(intent);
                        break;

                    case 4:
                        intent = new Intent(NewsActivity.this,UserActivity.class);
                        startActivity(intent);
                        break;
                    case 5:
                        intent = new Intent(NewsActivity.this,ConstraintActivity.class);
                        startActivity(intent);
                        break;
                    case 6:
//                        intent = new Intent(NewsActivity.this,Main2Activity.class);
//                        startActivity(intent);
                        break;

                    default:
                        intent = new Intent(NewsActivity.this,WebActivity.class);
                        intent.putExtra("url",newsList.get(position).getNewsUrl());
                        startActivity(intent);
                        break;
                }


            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            private int totalDy = 0;
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState == RecyclerView.SCROLL_STATE_IDLE){
                    Glide.with(mActivity).resumeRequests();
                }else {
                    Glide.with(mActivity).pauseAllRequests();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalDy +=dy;
                if(totalDy< 0){
                    totalDy = 0;
                }
                if(totalDy< mBannerHeight){
                    float alpha =(float) totalDy/ mBannerHeight;
                    toolbar.setAlpha(alpha);
                }else {
                    toolbar.setAlpha(1);
                }
            }
        });

    }
    private void addHeaderView() {
        addBannerView();
        addNetworkView();
    }

    private int mBannerPosition = -1;

    private void addBannerView(){
        View bannerView = LayoutInflater.from(this).inflate(R.layout.item_main_banner,recyclerView,false);
        mIvBanner = bannerView.findViewById(R.id.iv_banner);
         RecyclerView recyclerView = bannerView.findViewById(R.id.rv_content);
        ViewUtils.increaseViewHeightByStatusBarHeight(this,mIvBanner);
        ImmersionBar.setTitleBarMarginTop(this,recyclerView);

        mLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);
        mBannerAdapter = new BannerAdapter(Utils.getPics());
        recyclerView.setAdapter(mBannerAdapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (mBannerPosition != mLayoutManager.findFirstVisibleItemPosition()) {
                    mBannerPosition = mLayoutManager.findFirstVisibleItemPosition();
                    ArrayList<String> data = mBannerAdapter.getData();
                    String s = data.get(mBannerPosition % data.size());
                    GlideUtils.loadBlurry(mIvBanner, s);
                }
            }
        });
        mainAdapter.addHeaderView(bannerView);
    }



    private void getNews(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for(int i = 1;i<=2;i++) {
                        Document doc = Jsoup.connect("https://voice.hupu.com/nba/"+Integer.toString(i)).get();
                        Log.e("====","-----------");
                        Elements titleLinks = doc.select("div.list-hd");    //解析来获取每条新闻的标题与链接地址
                        Elements timeLinks = doc.select("div.otherInfo");   //解析来获取条新闻的时间与来源

                        for(int j = 0;j < titleLinks.size();j++){
                            String title = titleLinks.get(j).select("a").text();
                            String uri = titleLinks.get(j).select("a").attr("href");
//                            String desc = descLinks.get(j).select("span").text();
                            String time = timeLinks.get(j).select("span.other-left").select("a").text();
                            News news = new News(title,uri,"nihaoua",time);
                            Log.e("newPrint->",news.toString());
                            newsList.add(news);
                        }
                    }



                    Message msg = new Message();
                    msg.what = 1;
                    handler.sendMessage(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
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


    /**
            * 适配刘海屏遮挡数据问题
     * Adjust view.
            *
            * @param barProperties the bar properties,ImmersionBar#setOnBarListener
     */
    private void adjustView(BarProperties barProperties) {
        if (barProperties.isNotchScreen()) {
            if (newsList != null) {
                for (News news : newsList) {
                    if (barProperties.isPortrait()) {
                        news.setMarginStart(DensityUtil.dip2px(this, 8));
                        news.setMarginEnd(DensityUtil.dip2px(this, 8));
                    } else {
                        if (barProperties.isLandscapeLeft()) {
                            news.setMarginStart(DensityUtil.dip2px(this, 8) + barProperties.getNotchHeight());
                            news.setMarginEnd(DensityUtil.dip2px(this, 8));
                        } else {
                            news.setMarginStart(DensityUtil.dip2px(this, 8));
                            news.setMarginEnd(DensityUtil.dip2px(this, 8) + barProperties.getNotchHeight());
                        }
                    }
                }
            }
            if (mainAdapter != null) {
                mainAdapter.notifyDataSetChanged();
            }
        }
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
    private void addNetworkView() {
        mNetworkView = LayoutInflater.from(this).inflate(R.layout.item_network, recyclerView, false);
        if (!Utils.isNetworkConnected(this)) {
            mainAdapter.addHeaderView(mNetworkView);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onNetworkEvent(NetworkEvent networkEvent) {
        if (mNetworkView != null) {
            if (networkEvent.isAvailable()) {
                if (mNetworkView.getParent() != null) {
                    mainAdapter.removeHeaderView(mNetworkView);
                }
                if (mBannerAdapter != null && mBannerPosition != -1) {
                    mBannerAdapter.notifyDataSetChanged();
                    ArrayList<String> data = mBannerAdapter.getData();
                    String s = data.get(mBannerPosition % data.size());
                    GlideUtils.loadBlurry(mIvBanner, s);
                }
            } else {
                if (mNetworkView.getParent() == null) {
                    mainAdapter.addHeaderView(mNetworkView);
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        drawer.removeDrawerListener(this);
        EventBus.getDefault().unregister(this);
    }
}
