package com.heihei.weekedproject;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by wmm on 2017/9/2 0002.
 */
//创建MyTitleFit并继承LinearLayout
public class MyTitleFit extends LinearLayout {

    private Button btn_return;
    private Button btn_more;


    private String title;
    private float titleSize;
    private int titleColor;
    private int bgColor;
    private LinearLayout titlefit;


    private String btnlefttext;
    private float btnleftSize;
    private int btnleftColor;


    private String btnrighttext;
    private float btnrightSize;
    private int btnrightColor;


    private TextView tv_title;
    private Drawable btnrightbgcolor;
    private Drawable btnleftbgcolor;


    public MyTitleFit(Context context) {
        super(context);
        //创建方法
        initView(context, null);

    }


    public MyTitleFit(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //创建方法
        initView(context, attrs);

    }
    //初始化控件的方法
    private void initView(Context context, AttributeSet attrs) {
        //找到关于标题的xml
        View inflate = inflate(context, R.layout.activity_title, this);
       //依次找到控件,左右两边都是按钮中间是TextView
        btn_return = (Button) inflate.findViewById(R.id.btn_return);
        btn_more = (Button) inflate.findViewById(R.id.btn_more);
        tv_title = (TextView) inflate.findViewById(R.id.tv_title);
        titlefit = (LinearLayout) inflate.findViewById(R.id.titlefit);
        //点击左边返回的按钮的监听,并调用接口
        btn_return.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                if (leftOnClickListener != null) {

                    leftOnClickListener.leftClick(v);
                }
            }
        });

    //点击右边按钮的监听
        btn_more.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                if (rightOnClickListener != null) {

                    rightOnClickListener.rightClick(v);
                }
            }
        });

        if (attrs == null) {
            return;
        }
        //初始化自定义属性的方法
        initattrs(context, attrs);
        //把控件设置定义属性的方法
        setViewContent();
    }

    private void initattrs(Context context, AttributeSet attrs) {

        if (attrs == null) {
            return;
        }
        //找到自己定义的属性
        TypedArray typedarrat = context.obtainStyledAttributes(attrs, R.styleable.MyTitleFit);
      //获得给标题定义的属性 文本  文字大小 文字颜色 背景颜色
        title = typedarrat.getString(R.styleable.MyTitleFit_title);
        titleSize = typedarrat.getDimensionPixelSize(R.styleable.MyTitleFit_titleSize, 16);
        titleColor = typedarrat.getColor(R.styleable.MyTitleFit_titleColor, Color.RED);
        bgColor = typedarrat.getColor(R.styleable.MyTitleFit_bgcolor, Color.GREEN);
        //获得给左边返回按钮定义的属性 文本  文字大小 文字颜色 背景颜色
        btnleftSize = typedarrat.getDimensionPixelSize(R.styleable.MyTitleFit_btnleftSize, 12);
        btnlefttext = typedarrat.getString(R.styleable.MyTitleFit_btnlefttext);
        btnleftColor = typedarrat.getColor(R.styleable.MyTitleFit_btnleftcolor, Color.YELLOW);
        btnleftbgcolor = typedarrat.getDrawable(R.styleable.MyTitleFit_btnleftbgcolor);
        //获得给右边更多按钮定义的属性 文本  文字大小 文字颜色 背景颜色
        btnrightSize = typedarrat.getDimensionPixelSize(R.styleable.MyTitleFit_btnrightSize, 14);
        btnrighttext = typedarrat.getString(R.styleable.MyTitleFit_btnrighttext);
        btnrightColor = typedarrat.getColor(R.styleable.MyTitleFit_btnrightcolor, Color.BLUE);
        btnrightbgcolor = typedarrat.getDrawable(R.styleable.MyTitleFit_btnrightbgcolor);
    }

    private void setViewContent() {

        //给左边按钮附上默认属性  文本  文字大小 文字颜色 背景颜色
        btn_return.setText(btnlefttext);
        btn_return.setTextSize(btnleftSize);
        btn_return.setTextColor(btnleftColor);
        btn_return.setBackground(btnleftbgcolor);

        //给右边按钮附上默认属性  文本  文字大小 文字颜色 背景颜色
        btn_more.setText(btnrighttext);
        btn_more.setTextSize(btnrightSize);
        btn_more.setTextColor(btnrightColor);
        btn_more.setBackground(btnrightbgcolor);
        //给标题附上默认属性  文本  文字大小 文字颜色 背景颜色
        tv_title.setText(title);
        tv_title.setTextSize(titleSize);
        tv_title.setTextColor(titleColor);
        tv_title.setBackgroundColor(bgColor);


    }

    //定义一个接口,实现点击左边返回按钮为了实现Toast等一些操作
    public interface leftOnClickListener {

        void leftClick(View v);

    }

    private leftOnClickListener leftOnClickListener;

    public void SetleftOnClickListener(leftOnClickListener mleftOnClickListener) {

        this.leftOnClickListener = mleftOnClickListener;


    }

    //定义一个接口,实现点击右边更多按钮为了实现Toast等一些操作
    public interface rightOnClickListener {


        void rightClick(View v);

    }

    private rightOnClickListener rightOnClickListener;

    public void SetrightOnClickListener(rightOnClickListener mrightOnClickListener) {

        this.rightOnClickListener = mrightOnClickListener;

    }


}
