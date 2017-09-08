package www.bawei.com.wangmiamiaorecycler0907;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by wmm on 2017/9/7 0007.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private ArrayList<String>list;
    public MyAdapter(){
        list=new ArrayList<>();
        for (int i = 0; i <30 ; i++) {
            list.add("条目"+i);
        }
    }




    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_vh, null);

        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.tv_vh.setText(list.get(position));
        holder.tv_vh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (onmyClickListener!=null){
                    onmyClickListener.mOnClick(v,position);

                }
            }
        });



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder{


        private final TextView tv_vh;

        public MyViewHolder(View itemView) {
            super(itemView);

            tv_vh = (TextView) itemView.findViewById(R.id.tv_vh);

        }
    }



    public  interface   OnMyClickListener{
        void  mOnClick(View v, int position);
    }
    private OnMyClickListener onmyClickListener;

    public  void  SetOnMyClickListener(OnMyClickListener isonmyClickListener){
        this.onmyClickListener=isonmyClickListener;
    }





}
