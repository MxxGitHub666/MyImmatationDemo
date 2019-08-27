package com.mxx.myimmatationdemo.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import com.dueeeke.videoplayer.player.IjkVideoView;
import com.dueeeke.videoplayer.player.PlayerConfig;
import com.mxx.myimmatationdemo.R;
import com.mxx.myimmatationdemo.bean.Bean;

import java.util.List;

/**
 * Created by 98179 on 2019/8/23.
 */

public class MyVideoAdapter extends BaseQuickAdapter<Bean,BaseViewHolder> {

    private  PlayerConfig playerConfig;

    public MyVideoAdapter(@LayoutRes int layoutResId, @Nullable List<Bean> data) {
        super(layoutResId, data);
        init();
    }

    @Override
    protected void convert(BaseViewHolder helper, Bean item) {
        IjkVideoView videoView = helper.getView(R.id.video_view);
        videoView.setUrl(item.getUrl());
        videoView.setPlayerConfig(playerConfig);
        videoView.setScreenScale(IjkVideoView.SCREEN_SCALE_CENTER_CROP);

    }

    public void init(){
        playerConfig = new PlayerConfig.Builder()
                .enableCache()
                .usingSurfaceView()
                .savingProgress()
                .disableAudioFocus()
                .setLooping()
                .addToPlayerManager()
                .build();

    }
}
