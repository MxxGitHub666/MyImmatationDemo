package com.mxx.myimmatationdemo.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mxx.myimmatationdemo.myinterface.OnViewPagerListener;

/**
 * Created by 98179 on 2019/8/22.
 */

public class PagerLayoutManager extends LinearLayoutManager {

    private PagerSnapHelper mPagerSnapHelper;
    private OnViewPagerListener mOnViewPagerListener;
    private RecyclerView mRecyclerView;
    private int mDrift;//位移，用来判断移动方向


    public PagerLayoutManager(Context context, int orientation) {
        super(context, orientation, false);
        init();
    }

    public PagerLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    @Override
    public void onAttachedToWindow(RecyclerView view) {
        super.onAttachedToWindow(view);
        mPagerSnapHelper.attachToRecyclerView(view);
        this.mRecyclerView = view;
        mRecyclerView.addOnChildAttachStateChangeListener(mChildAttachStateChangeListener);

    }

    private void init(){
        mPagerSnapHelper = new PagerSnapHelper();
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        super.onLayoutChildren(recycler, state);
    }


    /**
     * 滑动状态的改变
     * 缓慢拖拽-> SCROLL_STATE_DRAGGING
     * 快速滚动-> SCROLL_STATE_SETTLING
     * 空闲状态-> SCROLL_STATE_IDLE
     *
     * @param state
     */
    @Override
    public void onScrollStateChanged(int state) {
        switch (state){
            case RecyclerView.SCROLL_STATE_IDLE:
                View viewIdle = mPagerSnapHelper.findSnapView(this);
                if(viewIdle!=null){
                    int positionIdle = getPosition(viewIdle);
                    if(mOnViewPagerListener != null && getChildCount()==1){
                        mOnViewPagerListener.onPageSelected(positionIdle, positionIdle == getItemCount() - 1, viewIdle);
                    }
                }
                break;
            case RecyclerView.SCROLL_STATE_DRAGGING:
                View viewDrag = mPagerSnapHelper.findSnapView(this);
                if (viewDrag != null) {
                    int positionDrag = getPosition(viewDrag);
                }
                break;
            case RecyclerView.SCROLL_STATE_SETTLING:
                View viewSettling = mPagerSnapHelper.findSnapView(this);
                if (viewSettling != null) {
                    int positionSettling = getPosition(viewSettling);
                }
                break;
        }
    }

    /**
     * 监听竖直方向的相对偏移量
     *
     * @param dy
     * @param recycler
     * @param state
     * @return
     */
    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
        this.mDrift = dy;
        return super.scrollVerticallyBy(dy, recycler, state);
    }

    /**
     * 监听水平方向的相对偏移量
     *
     * @param dx
     * @param recycler
     * @param state
     * @return
     */
    @Override
    public int scrollHorizontallyBy(int dx, RecyclerView.Recycler recycler, RecyclerView.State state) {
        this.mDrift = dx;
        return super.scrollHorizontallyBy(dx, recycler, state);
    }

    /**
     * 设置监听
     *
     * @param listener
     */
    public void setOnViewPagerListener(OnViewPagerListener listener){
        this.mOnViewPagerListener = listener;
    }

    private RecyclerView.OnChildAttachStateChangeListener mChildAttachStateChangeListener = new RecyclerView.OnChildAttachStateChangeListener() {
        /**
         * itemView依赖Window
         */
        @Override
        public void onChildViewAttachedToWindow(@NonNull View view) {
            if(mOnViewPagerListener != null && getChildCount() ==1){
                mOnViewPagerListener.onInitComplete(view);
            }
        }

        /**
         *itemView脱离Window
         */
        @Override
        public void onChildViewDetachedFromWindow(@NonNull View view) {
            if(mDrift >= 0){
                if(mOnViewPagerListener != null){
                    mOnViewPagerListener.onPageRelease(true,getPosition(view),view);
                }
            }else {
                if(mOnViewPagerListener!=null){
                    mOnViewPagerListener.onPageRelease(false,getPosition(view),view);
                }
            }

        }
    };



}
