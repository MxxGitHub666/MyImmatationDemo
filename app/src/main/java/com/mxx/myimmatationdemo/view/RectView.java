package com.mxx.myimmatationdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;


/**
 * Created by 98179 on 2019/9/4.
 */

public class RectView extends View {

    //定义画笔
    private Paint mPaint = new Paint();

    public RectView(Context context) {
        super(context);
        init();
    }

    public RectView(Context context,@Nullable AttributeSet attrs) {

        super(context, attrs);
        init();
    }

    public RectView(Context context,  @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mPaint.setColor(Color.BLUE);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        if(widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(300,300);
        }else if(widthMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(300,heightSize);
        }else if(heightMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(widthSize,300);
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //获取各个编剧的padding值
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();

        int width = getWidth() -paddingLeft -paddingRight;
        int height = getHeight() -paddingTop -paddingBottom;
        //绘制View，左上角坐标（0+paddingLeft,0+paddingTop），右下角坐标（width+paddingLeft,height+paddingTop）
        canvas.drawRect(0+paddingLeft,0+paddingTop,width+paddingLeft,height+paddingTop,mPaint);
    }
}
