package com.mxx.myimmatationdemo.activity;

import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.gyf.immersionbar.ImmersionBar;
import com.mxx.myimmatationdemo.R;
import com.mxx.myimmatationdemo.bean.TVBean;
import com.mxx.myimmatationdemo.myinterface.RequestCallBack;
import com.mxx.myimmatationdemo.utils.GlideUtils;
import com.mxx.myimmatationdemo.utils.RequestMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 98179 on 2019/7/9.
 */

public class DetailsActivity extends BaseActivity {

    private static final String TAG = "DetailsActivity";
    private ImageView mIvDaImg;
    private TextView mTvTitle;
    private TextView mTvUpdata;
    private TextView mTvTime;
    private TextView mTvType;
    private RecyclerView mDaRecyView;
    private Toolbar toolbar;
    private String mRequestUrl;
    private  String title;
    private String img_url;
    private String type;
    private MyTvAdapter myTvAdapter;
    private List<TVBean> tvBeanList = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_details;
    }

    @Override
    protected void initView() {
        super.initView();
        mIvDaImg = findViewById(R.id.iv_da_img);
        mTvTitle = findViewById(R.id.tv_title);
        mTvUpdata = findViewById(R.id.tv_updata);
        mTvTime = findViewById(R.id.tv_time);
        mTvType = findViewById(R.id.tv_type);
        mDaRecyView = findViewById(R.id.da_recyView);
        toolbar = findViewById(R.id.toolbar);

        mTvTitle.setText(title);
        toolbar.setTitle(type);
        GlideUtils.load(img_url, mIvDaImg);

        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(6, StaggeredGridLayoutManager.VERTICAL);
        // 绑定布局管理器
        mDaRecyView.setLayoutManager(layoutManager);
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        mDaRecyView.setItemAnimator(null);
        myTvAdapter = new MyTvAdapter(R.layout.item_tv,tvBeanList);
        mDaRecyView.setAdapter(myTvAdapter);
    }

    @Override
    protected void initData() {
        super.initData();
        Intent intent = getIntent();
        mRequestUrl = intent.getStringExtra("requestUrl");
        title = intent.getStringExtra("title");
        img_url = intent.getStringExtra("img_url");
        type = intent.getStringExtra("type");

        RequestMethod.getTvData(mRequestUrl, new RequestCallBack() {
            @Override
            public void success(List data) {
                Log.e("data:",data.size()+"");
                mTvUpdata.setText("更新至: "+data.size());
                myTvAdapter.setNewData(data);
            }

            @Override
            public void success(String data) {

            }

            @Override
            public void fail(Exception e) {

            }
        });
    }


    @Override
    protected void setListener() {
        super.setListener();
        myTvAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Log.e("onItemClick","DetailonItemClick");
                TVBean tvBean= (TVBean)adapter.getData().get(position);
                Log.e("onItemClick",tvBean.getUrl());
                Intent intent = new Intent(DetailsActivity.this, WebActivity.class);
                intent.putExtra("url", tvBean.getUrl());
                startActivity(intent);

            }
        });
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        ImmersionBar.with(this).titleBar(R.id.toolbar)
                //解决软键盘与底部输入框冲突问题
                .keyboardEnable(true)
                .init();
    }

    class MyTvAdapter extends BaseQuickAdapter<TVBean, BaseViewHolder> {

        public MyTvAdapter(@LayoutRes int layoutResId, @Nullable List<TVBean> data) {
            super(layoutResId, data);
        }
        @Override
        protected void convert(BaseViewHolder helper, TVBean item) {
            helper.setText(R.id.tv_series,item.getTitle());

        }
    }
}
