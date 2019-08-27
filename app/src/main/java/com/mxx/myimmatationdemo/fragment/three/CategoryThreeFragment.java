package com.mxx.myimmatationdemo.fragment.three;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.dueeeke.videoplayer.player.IjkVideoView;
import com.dueeeke.videoplayer.player.VideoViewManager;
import com.mxx.myimmatationdemo.R;
import com.mxx.myimmatationdemo.activity.VideoActivity;
import com.mxx.myimmatationdemo.adapter.MyVideoAdapter;
import com.mxx.myimmatationdemo.adapter.VideoAdapter;
import com.mxx.myimmatationdemo.bean.Bean;
import com.mxx.myimmatationdemo.fragment.LazyLoadFragment;
import com.mxx.myimmatationdemo.myinterface.OnViewPagerListener;
import com.mxx.myimmatationdemo.view.PagerLayoutManager;

import java.util.ArrayList;
import java.util.List;
import test.plugin.com.mylibrary.MPermission;
import test.plugin.com.mylibrary.PermissGroup;
import test.plugin.com.mylibrary.listener.PermissListener;

/**
 * @author geyifeng
 * @date 2017/5/12
 */
public class CategoryThreeFragment extends LazyLoadFragment {

    private RecyclerView recyclerView;
    private ArrayList<Bean> mDatas = new ArrayList<>();
    private MyVideoAdapter mAdapter;
    private IjkVideoView mVideoView;
    private PagerLayoutManager mLayoutManager;
    private int page = 0;
    private boolean flag = false;
    private ImageView ivAdd;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_three_category;
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected void initView() {
        super.initView();
        recyclerView = mActivity.findViewById(R.id.recycler_view);
        ivAdd = mActivity.findViewById(R.id.iv_add);
        mLayoutManager = new PagerLayoutManager(mActivity, OrientationHelper.VERTICAL);
        mDatas.addAll(getDatas());
        mAdapter = new MyVideoAdapter(R.layout.item_layout,mDatas);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);

    }

    @Override
    protected void setListener() {
        super.setListener();
        mLayoutManager.setOnViewPagerListener(new OnViewPagerListener() {
            @Override
            public void onInitComplete(View view) {
                playVideo(0, view);
            }

            @Override
            public void onPageSelected(int position, boolean isBottom, View view) {
                page = position;
                playVideo(position, view);
            }

            @Override
            public void onPageRelease(boolean isNext, int position, View view) {
                int index = 0;
                if (isNext) {
                    index = 0;
                } else {
                    index = 1;
                }
                releaseVideo(view);
            }
        });

        ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean b = mActivity.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA);
                Log.e("有无摄像头",b+"");
                if(b){
                    Intent intent = new Intent(mActivity, VideoActivity.class);
                    startActivity(intent);

                }
            }
        });
    }

    /**
     * 播放视频
     */
    private void playVideo(int position, View view) {
        mVideoView = view.findViewById(R.id.video_view);
        if (view != null&&flag) {
            mVideoView.start();
        }
    }

    /**
     * 停止播放
     */
    private void releaseVideo(View view) {
        if (view != null) {
            IjkVideoView videoView = view.findViewById(R.id.video_view);
            videoView.stopPlayback();
        }
    }

    @Override
    protected void loadData() {
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        Log.e("setUserVisibleHint","000"+isVisibleToUser);
        flag = isVisibleToUser;
        if(isVisibleToUser==false && mVideoView!=null){
            mVideoView.stopPlayback();
        }else {
            if (isVisibleToUser==true&&mVideoView != null) {
                mVideoView.start();
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mVideoView != null) {
            Log.e("onResume","000");
            mVideoView.resume();
        }
    }


    @Override
    public void onPause() {
        super.onPause();
        if (mVideoView != null) {
            Log.e("onPause","000");
            mVideoView.pause();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("onDestroy","000");
        VideoViewManager.instance().releaseVideoPlayer();
    }

    public  ArrayList<Bean> getDatas() {
        ArrayList<Bean> videoList = new ArrayList<>();
        videoList.add(new Bean("https://aweme.snssdk.com/aweme/v1/play/?video_id=97022dc18711411ead17e8dcb75bccd2&line=0&ratio=720p&media_type=4&vr_type=0"));
        videoList.add(new Bean("https://aweme.snssdk.com/aweme/v1/play/?video_id=374e166692ee4ebfae030ceae117a9d0&line=0&ratio=720p&media_type=4&vr_type=0"));
        videoList.add(new Bean("https://aweme.snssdk.com/aweme/v1/play/?video_id=8a55161f84cb4b6aab70cf9e84810ad2&line=0&ratio=720p&media_type=4&vr_type=0"));
        videoList.add(new Bean("https://aweme.snssdk.com/aweme/v1/play/?video_id=47a9d69fe7d94280a59e639f39e4b8f4&line=0&ratio=720p&media_type=4&vr_type=0"));
        videoList.add(new Bean("https://aweme.snssdk.com/aweme/v1/play/?video_id=3fdb4876a7f34bad8fa957db4b5ed159&line=0&ratio=720p&media_type=4&vr_type=0"));
        return videoList;
    }



}
