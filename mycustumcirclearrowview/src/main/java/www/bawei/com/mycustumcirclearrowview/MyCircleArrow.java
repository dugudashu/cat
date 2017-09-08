package www.bawei.com.mycustumcirclearrowview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

/**
 * Created by wmm on 2017/9/4 0004.
 */

public class MyCircleArrow extends View {
    //从xml中获取颜色
    private int circleBoudColor;
    private float circleBoundWidth;

    private TypedArray typedArray;
    //当前画笔画圆的颜色
    private int CurrentCircleBoundColor;
    private Paint paint;
    private int currentSpeed = 1;
    private boolean isPause = false;


    public MyCircleArrow(Context context) {
        super(context);
        initView(context);
    }

    public MyCircleArrow(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
        typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyCircleArrow);
        for (int i = 0; i < typedArray.getIndexCount(); i++) {
            //找到我们自定义的属性的资源id
            int attr = typedArray.getIndex(i);
            switch (attr) {
                case R.styleable.MyCircleArrow_circlr_bound_color:
                    circleBoudColor = typedArray.getColor(attr, Color.RED);
                    CurrentCircleBoundColor = circleBoudColor;
                    break;
                case R.styleable.MyCircleArrow_circlr_bound_width:
                    circleBoundWidth = typedArray.getDimension(attr, 3);
                    break;
            }
        }
    }

    private void initView(Context context) {
        paint = new Paint();
    }

    public void setColor(int color) {
        if (CurrentCircleBoundColor != color) {
            CurrentCircleBoundColor = color;
        } else {
            CurrentCircleBoundColor = circleBoudColor;
        }
    }

    //圆心
    private float pivoteX;
    private float pivoteY;
    private float radius = 130;
    private float currentDegree = 0;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setAntiAlias(true);
        paint.setColor(CurrentCircleBoundColor);
        paint.setStrokeWidth(circleBoundWidth);
        paint.setStyle(Paint.Style.STROKE);
        pivoteX = getWidth() / 2;
        pivoteY = getHeight() / 2;
        canvas.drawCircle(pivoteX, pivoteY, radius, paint);
        canvas.save();
        //旋转画布 , 如果旋转的的度数大的话,视觉上看着是旋转快的
        canvas.rotate(currentDegree, pivoteX, pivoteY);
        //提供了一些api可以用来画线(画路径)
        Path path = new Path();
        //从哪开始画 从A开始画
        path.moveTo(pivoteX + radius, pivoteY);
        //从A点画一个直线到D点
        path.lineTo(pivoteX + radius - 20, pivoteY - 20);
        //从D点画一个直线到B点
        path.lineTo(pivoteX + radius, pivoteY + 20);
        //从B点画一个直线到C点
        path.lineTo(pivoteX + radius + 20, pivoteY - 20);
        //闭合  --  从C点画一个直线到A点
        path.close();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLACK);
        canvas.drawPath(path, paint);
        canvas.restore();
        //旋转的度数一个一个度数增加,  如果乘以一个速度的话,按一个速度速度增加
        currentDegree += 1 * currentSpeed;
        if (!isPause) {
            invalidate();
        }
    }

    public void speedup() {
        ++currentSpeed;
        if (currentSpeed >= 10) {
            currentSpeed = 10;
            Toast.makeText(getContext(), "疾如风……", Toast.LENGTH_SHORT).show();
        }

    }

    public void slowdown() {
        --currentSpeed;
        if (currentSpeed <= 1) {
            currentSpeed = 1;
        }
    }

    public void repaint() {
        //如果是开始状态的话去重新绘制
        if (isPause) {
            isPause = !isPause;
            invalidate();
        } else {
            isPause = !isPause;
        }


    }


}
