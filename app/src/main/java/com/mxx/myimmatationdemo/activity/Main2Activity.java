//package com.mxx.myimmatationdemo.activity;
//
//import android.support.v4.widget.SwipeRefreshLayout;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//
//import com.mxx.myimmatationdemo.R;
//
//
///**
// * Created by 98179 on 2019/8/1.
// */
//
//public class Main2Activity extends BaseActivity implements MainView {
//
//    private RecyclerView recyclerView;
//    private SwipeRefreshLayout swipeRefreshLayout;
//
//
//
//    @Override
//    protected int getLayoutId() {
//        return R.layout.activity_main2;
//    }
//
//    @Override
//    protected void initData() {
//        super.initData();
////
////        ActivityMain2Binding binding = DataBindingUtil.setContentView(this,R.layout.activity_main2);
////        mainPresenter = new MainPresenter(this);
////
////        recyclerView = binding.recyclerView;
////        swipeRefreshLayout = binding.swipeRefreshLayout;
////
////        initView();
//    }
//
//    @Override
//    protected void initView() {
//        super.initView();
//
//        recyclerView.setHasFixedSize(true);
//        final LinearLayoutManager llm = new LinearLayoutManager(recyclerView.getContext());
//        recyclerView.setLayoutManager(llm);
//
////        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
////            @Override
////            public void onScrollStateChanged( RecyclerView recyclerView, int newState) {
////                super.onScrollStateChanged(recyclerView, newState);
////                if(newState == RecyclerView.SCROLL_STATE_IDLE && llm.findLastCompletelyVisibleItemPosition()>=mainPresenter.getAdapter().getItemCount()-1){
////                    mainPresenter.loadMore();
////                }
////            }
////        });
////
////        recyclerView.setAdapter(mainPresenter.getAdapter());
////        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
////            @Override
////            public void onRefresh() {
//                mainPresenter.refreshData();
//            }
//        });
//    }
//
//    @Override
//    public void showRefreshing() {
//        swipeRefreshLayout.setRefreshing(true);
//    }
//
//    @Override
//    public void hideRefreshing() {
//        swipeRefreshLayout.setRefreshing(false);
//    }
//
//}
