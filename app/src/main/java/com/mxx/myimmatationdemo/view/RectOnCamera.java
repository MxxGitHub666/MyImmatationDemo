package com.mxx.myimmatationdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import com.mxx.myimmatationdemo.myinterface.IAutoFocus;

/**
 * Created by 98179 on 2019/8/26.
 */

public class RectOnCamera extends View {

    private static final String TAG = "CameraPreview";
    private int mScreenWidth;
    private int mScreenHeight;
    private Paint mPaint;
    private RectF mRectF;
    private IAutoFocus mIAutoFocus;


    // 圆
    private Point centerPoint;
    private int radio;

    public RectOnCamera(Context context) {
        this(context, null);
    }

    public RectOnCamera(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RectOnCamera(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        getScreenMetrix(context);
        initView(context);
    }

    private void getScreenMetrix(Context context){

        WindowManager WM = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        WM.getDefaultDisplay().getMetrics(outMetrics);
        mScreenWidth = outMetrics.widthPixels;
        mScreenHeight = outMetrics.heightPixels;
    }

    private void initView(Context context){
        mPaint  = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(5);
        mPaint.setStyle(Paint.Style.STROKE);
        int marginLeft = (int)(mScreenWidth*0.15);
        int marginTop = (int)(mScreenHeight*0.25);
        mRectF = new RectF(marginLeft,marginTop,mScreenWidth-marginLeft,mScreenHeight - marginTop);

        centerPoint = new Point(mScreenWidth/2,mScreenHeight/2);
        radio = (int)(mScreenWidth*0.1);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setColor(Color.RED);
        canvas.drawRect(mRectF,mPaint);
        mPaint.setColor(Color.WHITE);
        canvas.drawCircle(centerPoint.x,centerPoint.y,radio,mPaint);
        canvas.drawCircle(centerPoint.x,centerPoint.y,radio-20,mPaint);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_UP:
                int x = (int) event.getX();
                int y = (int) event.getY();
                centerPoint = new Point(x,y);
                invalidate();
                return true;
        }

        if (mIAutoFocus != null){
            mIAutoFocus.autoFocus();
        }
        return true;
    }

    public void setIAutoFocus(IAutoFocus mIAutoFocus){
        this.mIAutoFocus = mIAutoFocus;
    }
}
