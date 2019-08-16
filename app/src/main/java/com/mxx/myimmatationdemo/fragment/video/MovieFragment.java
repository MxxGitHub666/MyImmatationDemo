package com.mxx.myimmatationdemo.fragment.video;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mxx.myimmatationdemo.R;
import com.mxx.myimmatationdemo.activity.DetailsActivity;
import com.mxx.myimmatationdemo.adapter.MovieAdapter;
import com.mxx.myimmatationdemo.adapter.VideoAdapter;
import com.mxx.myimmatationdemo.bean.TvTaiModel;
import com.mxx.myimmatationdemo.fragment.BaseFragment;
import com.mxx.myimmatationdemo.fragment.LazyLoadFragment;
import com.mxx.myimmatationdemo.myinterface.RequestCallBack;
import com.mxx.myimmatationdemo.utils.RequestMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 98179 on 2019/7/4.
 */

public class MovieFragment extends LazyLoadFragment {
    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;
    private List<TvTaiModel> movieList = new ArrayList<>();


    @Override
    protected void initView() {
        super.initView();
        recyclerView = mActivity.findViewById(R.id.rv_moviecontent);

        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        // 绑定布局管理器
        recyclerView.setLayoutManager(layoutManager);
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        recyclerView.setItemAnimator(null);
        movieAdapter = new MovieAdapter();
        recyclerView.setAdapter(movieAdapter);

    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_movie;
    }


    @Override
    protected void loadData() {
        RequestMethod.getMovieData("http://www.27k.cc/?m=vod-type-id-31.html", new RequestCallBack() {
            @Override
            public void success(List data) {
                movieList.addAll(data);
                movieAdapter.setNewData(data);
            }

            @Override
            public void success(String data) {

            }

            @Override
            public void fail(Exception e) {
                Toast.makeText(getContext(), "网络不稳定!加载失败!请稍后重试!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void setListener() {
        super.setListener();
        movieAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(mActivity, DetailsActivity.class);
                intent.putExtra("title", movieList.get(position).getTitle());
                if(movieList.get(position).getImg().contains("http")){
                    intent.putExtra("img_url", movieList.get(position).getImg());
                }else {
                    intent.putExtra("img_url", "http://www.27k.cc"+movieList.get(position).getImg());
                }

                intent.putExtra("requestUrl", movieList.get(position).getHref());
                intent.putExtra("type","电影");
                startActivity(intent);
            }
        });
    }


}
