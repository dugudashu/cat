package com.heihei.com.mycustumview3;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by wmm on 2017/8/30 0030.
 */

public class MyTextview extends LinearLayout implements View.OnClickListener {


    private TextView tv_view;
    private ImageView img_view;

    public MyTextview(Context context) {
        super(context);
    }

    public MyTextview(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initattrs(context);

    }

    private void initattrs(Context context) {
        View view = inflate(context, R.layout.activity_item, this);
        tv_view = (TextView) view.findViewById(R.id.tv_view);
        img_view = (ImageView)view.findViewById(R.id.img_view);
        img_view.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v.equals(img_view)) {

            Toast.makeText(getContext(), "我喜欢啤酒", Toast.LENGTH_SHORT).show();

        }

    }
}
