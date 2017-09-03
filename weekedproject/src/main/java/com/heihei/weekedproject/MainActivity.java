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
        //找到在MainActivity的自定义布局
        mytitlefit = (MyTitleFit) findViewById(R.id.mytitlefit);
        //调用它的接口,点击左边返回按钮让它Toast一些内容
        mytitlefit.SetleftOnClickListener(new MyTitleFit.leftOnClickListener() {
            @Override
            public void leftClick(View v) {
                Toast.makeText(MainActivity.this, "这是左边的按钮", Toast.LENGTH_SHORT).show();
            }
        });
//调用它的接口,点击右边更多按钮让它Toast一些内容
        mytitlefit.SetrightOnClickListener(new MyTitleFit.rightOnClickListener() {
            @Override
            public void rightClick(View v) {
                Toast.makeText(MainActivity.this, "这是右边的按钮", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
