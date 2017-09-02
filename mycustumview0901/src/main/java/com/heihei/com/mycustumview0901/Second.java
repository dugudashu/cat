package com.heihei.com.mycustumview0901;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by wmm on 2017/9/1 0001.
 */

public class Second extends Activity {

    private Button btn_return2;
    private ImageView img_jump2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        img_jump2 = (ImageView) findViewById(R.id.img_jump2);
        img_jump2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(Second.this,MainActivity.class);
                startActivity(intent);

            }
        });




    }
}
