package com.mxx.myimmatationdemo.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by 98179 on 2019/9/4.
 */

@SuppressLint("AppCompatCustomView")
public class LineTextView extends TextView{

    private Paint mPaint;


    public LineTextView(Context context) {
        super(context);
        init();
    }

    public LineTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LineTextView(Context context,  @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        mPaint.setColor(Color.BLUE);

        RectF rectF = new RectF(0,0,width,height);

        canvas.drawRect(rectF,mPaint);
        mPaint.setColor(Color.BLACK);

        canvas.drawLine(0,height/2,width,height/2,mPaint);
    }
}
