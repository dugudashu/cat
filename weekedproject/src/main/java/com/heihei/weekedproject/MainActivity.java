package com.heihei.weekedproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private MyTitleFit mytitlefit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mytitlefit = (MyTitleFit) findViewById(R.id.mytitlefit);
        mytitlefit.SetleftOnClickListener(new MyTitleFit.leftOnClickListener() {
            @Override
            public void leftClick(View v) {

                Toast.makeText(MainActivity.this, "你想要荒古?", Toast.LENGTH_SHORT).show();


            }
        });

        mytitlefit.SetrightOnClickListener(new MyTitleFit.rightOnClickListener() {
            @Override
            public void rightClick(View v) {


                Toast.makeText(MainActivity.this, "简直活在梦里", Toast.LENGTH_SHORT).show();

            }
        });


    }


}
