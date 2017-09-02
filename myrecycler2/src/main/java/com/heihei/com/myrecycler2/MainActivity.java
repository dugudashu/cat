package com.heihei.com.myrecycler2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recycler;
    private Myadapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private GridLayoutManager gridLayoutManager;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recycler = (RecyclerView) findViewById(R.id.recycler);
//        recycler.setAdapter(new Myadapter());


        linearLayoutManager = new LinearLayoutManager(this);
        gridLayoutManager = new GridLayoutManager(this, 3);
         gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
          @Override
          public int getSpanSize(int position) {
              return 3-position%3;
          }
      });


        staggeredGridLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recycler.setLayoutManager(linearLayoutManager);
        adapter=new Myadapter();
        recycler.setAdapter(adapter);



    }
    public  void btn_change(View v){

        //切换布局
        RecyclerView.LayoutManager layoutManager = recycler.getLayoutManager();
        if (layoutManager == null) {
            return;
        }

        //if和elseif是有先后顺序的,先判断范围小的,然后再判断范围大的,
        //因为GridLayoutManager 是继承 LinearLayoutManager ,所以他本质上也是LinearLayoutManager,
        //所以不能先判断是否是LinearLayoutManager (LinearLayoutManager范围大)
        if (layoutManager instanceof GridLayoutManager) {
            recycler.setLayoutManager(linearLayoutManager);
        } else if (layoutManager instanceof LinearLayoutManager) {
            recycler.setLayoutManager(gridLayoutManager);
        }



    }


}
