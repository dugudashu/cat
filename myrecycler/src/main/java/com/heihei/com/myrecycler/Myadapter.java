package com.heihei.com.myrecycler;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Myadapter extends RecyclerView.Adapter<Myadapter.MyViewHolder> {
    //创建一个Arraylist集合用来显示条目
    private ArrayList<String> list;
    private HashMap<Integer, Boolean> isCheckedHashMap;


    public Myadapter() {
        isCheckedHashMap= new HashMap<>();
        list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            list.add("条目" + i);
            isCheckedHashMap.put(i, false);

        }
    }

    //创建ViewHolder的每条item
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        RecyclerView.LayoutManager layoutManager = ((RecyclerView) parent).getLayoutManager();
        View recyclerViewItem = null;
        if (layoutManager instanceof GridLayoutManager) {
            //inflate的时候,需要传入parent和attachToRoot==false; 使用传入三个参数的方法
            recyclerViewItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item2, parent, false);
        } else if (layoutManager instanceof LinearLayoutManager) {
            //inflate的时候,需要传入parent和attachToRoot==false; 使用传入三个参数的方法
            recyclerViewItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item, parent, false);
        }
        return new MyViewHolder(recyclerViewItem);
    }
    public interface OnItemClickListener {

        void onItemClick(View v, int position);
    }
    public interface OnItemLongClickListener {

        void onItemLongClick(View v, int position);

    }
    private OnItemClickListener mItemClickListener;
    private OnItemLongClickListener mItemLongClickListener;
    public void setOnClickListener(OnItemClickListener itemClickListener) {

        this.mItemClickListener = itemClickListener;

    }
    public void setOnItemLongClickListener(OnItemLongClickListener itemLongClickListener) {

        this.mItemLongClickListener = itemLongClickListener;


    }
    public void add(int position) {
        list.add(position + 1, "这是新加的");
        notifyItemRangeChanged(position + 1, getItemCount());

    }
    public void remove(int position) {

        list.remove(position);
        notifyItemRangeChanged(position + 1, getItemCount());
    }
    public void update(int position, String content) {

        list.remove(position);
        list.add(position, content);
        notifyItemChanged(position);
    }
    //绑定数据
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.tv_title.setText(list.get(position));

        //整个item条目的点击
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //// TODO: 2017/8/31 暴露一个单击回调接口
//                if (mItemClickListener != null) {
//                    mItemClickListener.onItemClick(v, position);
//                }
//            }
//        });
        holder.img_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItemClickListener != null) {
                    mItemClickListener.onItemClick(v, position);
                }
            }
        });
        holder.img_view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mItemLongClickListener != null) {
                    mItemLongClickListener.onItemLongClick(v, position);
                }
                return true;
            }
        });
        holder.ck_box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isCheckedHashMap.put(position, !isCheckedHashMap.get(position));
                notifyDataSetChanged();
            }
        });
        holder.ck_box.setChecked(isCheckedHashMap.get(position));
//        if (position % 2 == 0) {
//            holder.img_view.setImageResource(R.mipmap.ic_launcher);
//        }
    }
    public void selectAll() {
        Set<Map.Entry<Integer, Boolean>> entries = isCheckedHashMap.entrySet();
        boolean shouldSelectedAll = false;
        for (Map.Entry<Integer, Boolean> bean : entries) {
            Boolean value = bean.getValue();
            if (!value) {
                shouldSelectedAll = true;
                break;
            }
        }
        for (Map.Entry<Integer, Boolean> bean : entries) {

            bean.setValue(shouldSelectedAll);
        }
        notifyDataSetChanged();
    }
    public void revertSelected() {
        Set<Map.Entry<Integer, Boolean>> entries = isCheckedHashMap.entrySet();
        for (Map.Entry<Integer, Boolean> bean : entries) {
            bean.setValue(!bean.getValue());
        }
        notifyDataSetChanged();
    }
    public void singleSelected(int position) {
        Set<Map.Entry<Integer, Boolean>> entries = isCheckedHashMap.entrySet();
        for (Map.Entry<Integer, Boolean> bean : entries) {
            bean.setValue(false);
        }
        isCheckedHashMap.put(position, true);
        notifyDataSetChanged();
    }
    //得到条目数
    @Override
    public int getItemCount() {
        return list.size();
    }

    //创建一个MyViewHolder类在这里找到控件
    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_title;
        ImageView img_view;
        CheckBox ck_box;
        View itemView;

        public MyViewHolder(View itemView) {
            super(itemView);
            //findviewById 给控件绑定id
            this.itemView = itemView;
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            img_view = (ImageView) itemView.findViewById(R.id.img_view);
            ck_box = (CheckBox) itemView.findViewById(R.id.ck_box);
        }
    }
}
