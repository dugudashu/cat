package www.bawei.com.mycustumview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
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
        textSize = typedarray.getDimensionPixelSize(R.styleable.MyTextview_textSize, 16);


    }

    public MyTextview(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        initAttrs(attrs);


    }

//    //测量
//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//    }

    //绘画
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(textColor);
        paint.setTextSize(textSize);
        canvas.drawText(text, 10, 100, paint);


    }

//    //设置子view位置
//    @Override
//    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
//        super.onLayout(changed, left, top, right, bottom);
//    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        return super.onTouchEvent(event);
//    }


}
