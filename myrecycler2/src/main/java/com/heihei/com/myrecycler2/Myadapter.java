package com.heihei.com.myrecycler2;

import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by wmm on 2017/8/30 0030.
 */

class Myadapter extends RecyclerView.Adapter<Myadapter.MyViewHolder> {
    private ArrayList<String> list;

    public Myadapter() {

        list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {

            list.add("这是第" + i + "个");
        }


    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View recyclerView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item,null);

        return new MyViewHolder(recyclerView);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.tv_view.setText(list.get(position));
        if (position % 2 == 1) {

            holder.img_view.setImageResource(R.mipmap.ic_launcher);

        }


    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final ImageView img_view;
        private final TextView tv_view;

        public MyViewHolder(View itemView) {
            super(itemView);
            img_view = (ImageView) itemView.findViewById(R.id.img_view);
            tv_view = (TextView) itemView.findViewById(R.id.tv_view);


        }
    }


}
