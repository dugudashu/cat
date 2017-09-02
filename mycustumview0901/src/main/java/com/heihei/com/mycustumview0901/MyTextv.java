package com.heihei.com.mycustumview0901;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by wmm on 2017/9/1 0001.
 */

public class MyTextv extends View {
    private String text;
    private float textSize;
    private int textColor;
    /**
     * 绘制时控制文本绘制的范围
     */
    private Rect mBound;
    private Paint mPaint;

    public MyTextv(Context context) {

        this(context, null);

    }

    public MyTextv(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyTextv(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.MyTextv, defStyleAttr, 0);
        text = a.getString(R.styleable.MyTextv_text);
        textColor = a.getColor(R.styleable.MyTextv_textColor, Color.BLUE);
        textSize = a.getDimension(R.styleable.MyTextv_textSize, 24);
        a.recycle();
        mPaint = new Paint();
        mPaint.setTextSize(textSize);
        mPaint.setColor(textColor);
        //获得绘制文本的宽和高
        mBound = new Rect();
        mPaint.getTextBounds(text, 0, text.length(), mBound);
    }

//        初始化
//        text = "今天好烦*";
//        textColor = Color.BLACK;
//        textSize = 100;
//
//        mPaint = new Paint();
//        mPaint.setTextSize(textSize);
//        mPaint.setColor(textColor);
//        //获得绘制文本的宽和高
//        mBound = new Rect();
//        mPaint.getTextBounds(text, 0, text.length(), mBound);




//    public MyTextv(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawText(text, getWidth() / 2 - mBound.width() / 2, getHeight() / 2 + mBound.height() / 2, mPaint);
    }
}
