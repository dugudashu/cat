package www.bawei.com.wangmiamiaorecycler0907;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by wmm on 2017/9/7 0007.
 */

public class MyItemDecoration extends RecyclerView.ItemDecoration {


    public static final int LINEAR_LAYOUT_ORIENTATION_VERTICAL = 0;
    public static final int LINEAR_LAYOUT_ORIENTATION_HORIZONTAL = 1;
    private int orientation = -1; // 当前的布局方向
    private Drawable myDivider = null;



    public MyItemDecoration(Context context, int orientation) {
        /* 这个构造方法用于处理线性布局传入的情况，我们要对myDivider对象进行初始化
        * （绘制的颜色和宽度等等）
        * R.drawable.my_list_divider 是我们自定义的一个drawable资源文件,我们通过
        * myContext来获取它
        */
        myDivider = context.getResources().getDrawable(R.drawable.my_list_divider);

        if(orientation == LinearLayoutManager.HORIZONTAL) {
            this.orientation = LINEAR_LAYOUT_ORIENTATION_HORIZONTAL;
        }else if(orientation == LinearLayoutManager.VERTICAL) {
            this.orientation = LINEAR_LAYOUT_ORIENTATION_VERTICAL;
        }

    }


    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);

        if(orientation == LINEAR_LAYOUT_ORIENTATION_HORIZONTAL ||
                orientation == LINEAR_LAYOUT_ORIENTATION_VERTICAL) {
            linearLayoutDrawItemDecoration(c, parent);
        }



    }

    private void linearLayoutDrawItemDecoration(Canvas c, RecyclerView parent) {
        int childCount = parent.getChildCount(); // 获取RecyclerView控件中的子控件总数
        int left, top, right, bottom;
        View child = parent.getChildAt(0);
        // 获取分割线的高度（把分割线看成一个小矩形）
        int drawableHeight = myDivider.getIntrinsicHeight();
        // 如果是竖直排布，那么分割线为横线
        if(orientation == LINEAR_LAYOUT_ORIENTATION_VERTICAL) {
            left = parent.getLeft();
            right = parent.getRight(); // 获取子控件开始 x 坐标和结束 x 坐标
            for (int i = 1; i < childCount; i++) {
                top = child.getBottom() - drawableHeight/2; // 获取开始点y坐标
                bottom = child.getBottom()  + drawableHeight/2; // 获取结束点y坐标
                myDivider.setBounds(left, top, right, bottom); // 设置绘制区域，下同
                myDivider.draw(c); // 在Canvas对象上绘制区域
                child = parent.getChildAt(i);
            }
            // 如果是水平排布，那么分割线为竖线
        } else if(orientation == LINEAR_LAYOUT_ORIENTATION_HORIZONTAL) {
            top = child.getTop();
            bottom = child.getBottom(); // 获取子控件的开始 y 坐标和结束 y 坐标
            for(int i = 1; i < childCount; i++) {
                left = child.getRight() - drawableHeight/2; // 获取开始点 x 坐标
                right = child.getRight() + drawableHeight/2; // 获取结束点 x 坐标
                myDivider.setBounds(left, top, right, bottom); // 设置绘制区域
                myDivider.draw(c);
                child = parent.getChildAt(i);
            }
        }
    }












}
