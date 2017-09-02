package com.heihei.com.myrecycler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerview;
    private LinearLayoutManager manager;
    private GridLayoutManager gridLayoutManager;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;
    private Myadapter adapter;
    private Button button_all;
    private Button button_revert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //找到控件
        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        button_all = (Button) findViewById(R.id.button_all);
        button_revert = (Button) findViewById(R.id.button_revert);


        //分别创建 LinearLayoutManager  GridLayoutManager,
        // 在这里 相当于一个展示的是ListView一个是GridView
        manager = new LinearLayoutManager(this);
        //给它创建3列
        gridLayoutManager = new GridLayoutManager(this, 3);
        //  可以通过setSpanSizeLookup 来自定义每个item占的列数
//        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//            @Override
//            public int getSpanSize(int position) {
//                return 3 - position;
//            }
//        });

        //瀑布流的形式
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(manager);
        //添加
        adapter = new Myadapter();
        recyclerview.setAdapter(adapter);
//        adapter.setOnClickListener(new Myadapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(View v, int position) {
//
////                Toast.makeText(MainActivity.this, "这是单击的事件", Toast.LENGTH_SHORT).show();
//                adapter.add(position);
//
//
//
//            }
//        });
        button_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                adapter.selectAll();


            }
        });

        button_revert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                adapter.revertSelected();


            }
        });


        adapter.setOnItemLongClickListener(new Myadapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(View v, int position) {


//                Toast.makeText(MainActivity.this, "这是长按的事件", Toast.LENGTH_SHORT).show();

                adapter.update(position, "这是我更新的内容");


            }
        });

        recyclerview.addItemDecoration(new MyDecoration(this, LinearLayoutManager.VERTICAL));


    }

    //创建一个按钮来实现点击改变,要变换界面的样子
    public void btn_change(View v) {
        //切换布局
        RecyclerView.LayoutManager layoutmanager = recyclerview.getLayoutManager();
        if (layoutmanager == null) {
            return;
        }
        //if和elseif是有先后顺序的,先判断范围小的,然后再判断范围大的,
        //因为GridLayoutManager 是继承 LinearLayoutManager ,所以他本质上也是LinearLayoutManager,
        //所以不能先判断是否是LinearLayoutManager (LinearLayoutManager范围大)
        if (layoutmanager instanceof GridLayoutManager) {
            recyclerview.setLayoutManager(manager);
            recyclerview.setAdapter(adapter);
        } else if (layoutmanager instanceof LinearLayoutManager) {
            recyclerview.setLayoutManager(gridLayoutManager);
            recyclerview.setAdapter(adapter);
        }
    }
}
