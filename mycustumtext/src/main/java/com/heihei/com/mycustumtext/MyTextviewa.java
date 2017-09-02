package com.heihei.com.mycustumtext;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by wmm on 2017/9/1 0001.
 */

public class MyTextviewa extends LinearLayout {


    private ImageView img_left;
    private TextView tv_title;
    private TypedArray typedarray;

    private String titletext;
    private int bgColor;
    private float titleSize;
    private Drawable icondrawable;
    private int textColor;
    private RelativeLayout container;


    public MyTextviewa(Context context) {
        super(context);
        initView(context, null);
    }

    public MyTextviewa(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }
    private void initView(Context context, AttributeSet attrs) {
        View view = inflate(context, R.layout.actionbar, this);
        container = (RelativeLayout) view.findViewById(R.id.container);
        img_left = (ImageView) view.findViewById(R.id.img_left);
        tv_title = (TextView) view.findViewById(R.id.tv_title);
        img_left.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onmIconClickListener != null) {
                    onmIconClickListener.iconClick(v);
                }
            }
        });
        if (attrs == null) {
            return;
        }
        initAtrrs(context, attrs);
        setViewContent();
    }
    private void initAtrrs(Context context, AttributeSet attrs) {
        if (attrs == null) {
            return;
        }
        typedarray = context.obtainStyledAttributes(attrs, R.styleable.MyTextviewa);
        titletext = typedarray.getString(R.styleable.MyTextviewa_text);
        titleSize = typedarray.getDimensionPixelSize(R.styleable.MyTextviewa_textSize, 16);
        bgColor = typedarray.getColor(R.styleable.MyTextviewa_baColor, Color.RED);
        textColor = typedarray.getColor(R.styleable.MyTextviewa_textColor, Color.BLUE);
        icondrawable = typedarray.getDrawable(R.styleable.MyTextviewa_icon);
    }
    public void setViewContent() {
        container.setBackgroundColor(bgColor);
        img_left.setImageDrawable(icondrawable);
        tv_title.setText(titletext);
        tv_title.setTextSize(titleSize);
        tv_title.setTextColor(textColor);
    }
    public interface OnmIconClickListener {
        void iconClick(View v);
    }
    private OnmIconClickListener onmIconClickListener;

    public void setOnmIconClickListener(OnmIconClickListener onmIconClickListener) {
        this.onmIconClickListener = onmIconClickListener;
    }

}








