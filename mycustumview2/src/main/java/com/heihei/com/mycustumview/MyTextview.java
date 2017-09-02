package com.heihei.com.mycustumview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by wmm on 2017/8/30 0030.
 */

public class MyTextview extends View {

    private String text;
    private int textColor;
    private float textSize;


    public MyTextview(Context context) {
        super(context);
        initAttrs(null);

    }

    private void initAttrs(@Nullable AttributeSet attrs) {
        TypedArray typedarray = getContext().obtainStyledAttributes(attrs, R.styleable.MyTextview);
        text = typedarray.getString(R.styleable.MyTextview_text);
        textColor = typedarray.getColor(R.styleable.MyTextview_textColor, 0xffff);
        textSize = typedarray.getDimensionPixelSize(R.styleable.MyTextview_textSize, 18);
    }

    public MyTextview(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        initAttrs(attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(textColor);
        paint.setTextSize(textSize);
        canvas.drawText(text, 10, 100, paint);

    }
}
