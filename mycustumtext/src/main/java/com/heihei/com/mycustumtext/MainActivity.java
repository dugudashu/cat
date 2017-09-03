package com.heihei.com.mycustumtext;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements MyTextviewa.OnmIconClickListener {

    private MyTextviewa my_action_bar;
//wozhenshi
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        my_action_bar = (MyTextviewa) findViewById(R.id.my_action_bar);
        my_action_bar.setOnmIconClickListener(this);
    }


    @Override
    public void iconClick(View v) {

        Intent intent=new Intent(MainActivity.this,Second.class);
        startActivity(intent);

    }
}
