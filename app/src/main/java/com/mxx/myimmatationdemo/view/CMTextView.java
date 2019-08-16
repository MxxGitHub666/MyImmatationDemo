package com.mxx.myimmatationdemo.view;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;


/**
 * Created by 98179 on 2019/7/3.
 */

public class CMTextView extends View {
    //画笔
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public CMTextView(Context context) {
        super(context);
    }

    public CMTextView(Context context,  @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CMTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int heigthMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);

        int heigth = MeasureSpec.getSize(heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);

        if(MeasureSpec.AT_MOST==heigthMode){
            Rect bounds = new Rect();
        }
    }
}
