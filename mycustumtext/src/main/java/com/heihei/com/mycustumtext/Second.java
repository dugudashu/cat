package com.heihei.com.mycustumtext;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

/**
 * Created by wmm on 2017/9/1 0001.
 */

public class Second extends Activity implements MyTextviewa.OnmIconClickListener {
    private MyTextviewa my_action_bar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitysecond);
        my_action_bar = (MyTextviewa) findViewById(R.id.my_action_bar);
        my_action_bar.setOnmIconClickListener(this);
    }

    @Override
    public void iconClick(View v) {

        finish();


    }
}
