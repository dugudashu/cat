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
        initView(context, null);

    }


    public MyTitleFit(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);

    }

    private void initView(Context context, AttributeSet attrs) {

        View inflate = inflate(context, R.layout.activity_title, this);
        btn_return = (Button) inflate.findViewById(R.id.btn_return);
        btn_more = (Button) inflate.findViewById(R.id.btn_more);
        tv_title = (TextView) inflate.findViewById(R.id.tv_title);
        titlefit = (LinearLayout) inflate.findViewById(R.id.titlefit);

        btn_return.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                if (leftOnClickListener != null) {

                    leftOnClickListener.leftClick(v);


                }


            }
        });


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
        initattrs(context, attrs);
        setViewContent();
    }

    private void initattrs(Context context, AttributeSet attrs) {

        if (attrs == null) {
            return;
        }

        TypedArray typedarrat = context.obtainStyledAttributes(attrs, R.styleable.MyTitleFit);
        title = typedarrat.getString(R.styleable.MyTitleFit_title);
        titleSize = typedarrat.getDimensionPixelSize(R.styleable.MyTitleFit_titleSize, 16);
        titleColor = typedarrat.getColor(R.styleable.MyTitleFit_titleColor, Color.RED);
        bgColor = typedarrat.getColor(R.styleable.MyTitleFit_bgcolor, Color.GREEN);

        btnleftSize = typedarrat.getDimensionPixelSize(R.styleable.MyTitleFit_btnleftSize, 12);
        btnlefttext = typedarrat.getString(R.styleable.MyTitleFit_btnlefttext);
        btnleftColor = typedarrat.getColor(R.styleable.MyTitleFit_btnleftcolor, Color.YELLOW);
        btnleftbgcolor = typedarrat.getDrawable(R.styleable.MyTitleFit_btnleftbgcolor);

        btnrightSize = typedarrat.getDimensionPixelSize(R.styleable.MyTitleFit_btnrightSize, 14);
        btnrighttext = typedarrat.getString(R.styleable.MyTitleFit_btnrighttext);
        btnrightColor = typedarrat.getColor(R.styleable.MyTitleFit_btnrightcolor, Color.BLUE);
        btnrightbgcolor = typedarrat.getDrawable(R.styleable.MyTitleFit_btnrightbgcolor);


    }

    private void setViewContent() {


        btn_return.setText(btnlefttext);
        btn_return.setTextSize(btnleftSize);
        btn_return.setTextColor(btnleftColor);
        btn_return.setBackground(btnleftbgcolor);

//tuolaji
        btn_more.setText(btnrighttext);
        btn_more.setTextSize(btnrightSize);
        btn_more.setTextColor(btnrightColor);
        btn_more.setBackground(btnrightbgcolor);

        tv_title.setText(title);
        tv_title.setTextSize(titleSize);
        tv_title.setTextColor(titleColor);
        tv_title.setBackgroundColor(bgColor);


    }


    public interface leftOnClickListener {

        void leftClick(View v);

    }

    private leftOnClickListener leftOnClickListener;

    public void SetleftOnClickListener(leftOnClickListener mleftOnClickListener) {

        this.leftOnClickListener = mleftOnClickListener;


    }


    public interface rightOnClickListener {


        void rightClick(View v);

    }

    private rightOnClickListener rightOnClickListener;

    public void SetrightOnClickListener(rightOnClickListener mrightOnClickListener) {

        this.rightOnClickListener = mrightOnClickListener;

    }


}
