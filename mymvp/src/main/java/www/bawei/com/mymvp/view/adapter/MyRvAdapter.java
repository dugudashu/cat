package www.bawei.com.mymvp.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nostra13.universalimageloader.core.DisplayImageOptions;

import java.util.ArrayList;
import java.util.List;

import www.bawei.com.mymvp.R;
import www.bawei.com.mymvp.view.bean.Faxian;

/**
 * Created by wmm on 2017/9/8 0008.
 */

public class MyRvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Faxian.ResultBean.ListBean> data;
    private  Context context;
    public MyRvAdapter(Context context, List<Faxian.ResultBean.ListBean> mlist) {
        data = mlist;
        this.context=context;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view = null;
        RecyclerView.ViewHolder holder = null;
        switch (viewType) {
            case 0:
                //inflate的时候,需要传入parent和attachToRoot==false; 使用传入三个参数的方法
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view, parent, false);
                holder = new MyViewHolder(view);
                break;
            case 1:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view2, parent, false);
                holder = new MyViewHolder2(view);
                break;
        }

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        switch (getItemViewType(position)) {
            case 0:
                MyViewHolder holder1 = (MyViewHolder) holder;
//                holder1.showImage.setImageResource(R.drawable.binggan);
                Glide.with(context).load(data.get(position).getPic()).into(holder1.showImage);
                holder1.showText.setText(data.get(position).getContent());
                break;
            case 1:
                MyViewHolder2 holder2 = (MyViewHolder2) holder;
                Glide.with(context).load(data.get(position).getPic()).into(holder2.item2_iv);
                holder2.item2_tv.setText(data.get(position).getContent());
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position % 2 == 0) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final ImageView showImage;
        private final TextView showText;
        View itemView;

        public MyViewHolder(View itemView) {
            super(itemView);
            showImage = (ImageView) itemView.findViewById(R.id.showImage);
            showText = (TextView) itemView.findViewById(R.id.showText);
            this.itemView = itemView;
        }
    }

    public class MyViewHolder2 extends RecyclerView.ViewHolder {
        private final ImageView item2_iv;
        private final TextView item2_tv;
        public MyViewHolder2(View itemView) {
            super(itemView);
            item2_iv = (ImageView) itemView.findViewById(R.id.item2_Iv);
            item2_tv = (TextView) itemView.findViewById(R.id.item2_Tv);
        }

    }



}
