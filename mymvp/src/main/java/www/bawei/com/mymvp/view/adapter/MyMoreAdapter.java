package www.bawei.com.mymvp.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Callback;
import www.bawei.com.mymvp.R;
import www.bawei.com.mymvp.view.bean.Faxian;
import www.bawei.com.mymvp.view.bean.HomeBean;


/**
 * Created by wmm on 2017/9/11 0011.
 */

public class MyMoreAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<HomeBean.GoodsListBean> list;
    public MyMoreAdapter(Context context, List<HomeBean.GoodsListBean> goods_list) {
        this.context=context;
        this.list=goods_list;
    }
    //创建布局和viewHolder
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        RecyclerView.ViewHolder holder = null;
        switch (viewType) {
            case 0:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler4, parent, false);
                holder = new MyViewHolder(view);
                //inflate的时候,需要传入parent和attachToRoot==false; 使用传入三个参数的方法
                break;
            case 1:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler3, parent, false);
                holder = new MyViewHolder2(view);
                break;
        }

        return holder;
    }

    //绑定数据
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        switch (getItemViewType(position)) {
            case 0:
                MyViewHolder holder1 = (MyViewHolder) holder;
                Glide.with(context).load(list.get(position).getImage_url()).into(holder1.showImage1);
                Glide.with(context).load(list.get(position).getThumb_url()).into(holder1.showImage2);
                Glide.with(context).load(list.get(position).getThumb_url()).into(holder1.showImage3);
                Glide.with(context).load(list.get(position).getImage_url()).into(holder1.showImage4);
                holder1.tv_recy1.setText(list.get(position).getGoods_name());
                holder1.tv_recy2.setText(list.get(position).getShort_name());
                break;
            case 1:
                MyViewHolder2 holder2 = (MyViewHolder2) holder;
                Glide.with(context).load(list.get(position).getImage_url()).into(holder2.item2_iv1);
                Glide.with(context).load(list.get(position).getThumb_url()).into(holder2.item2_iv2);
                holder2.cy_tv_1.setText(list.get(position).getGoods_name());
                holder2.cy_tv_2.setText(list.get(position).getShort_name());
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
        return list.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        View itemView;
        private final ImageView showImage1;
        private final ImageView showImage2;
        private final ImageView showImage3;
        private final ImageView showImage4;
        private final TextView tv_recy1;
        private final TextView tv_recy2;


        public MyViewHolder(View itemView) {
            super(itemView);
            showImage1 = (ImageView) itemView.findViewById(R.id.img_recy1);
            showImage2 = (ImageView) itemView.findViewById(R.id.img_recy2);
            showImage3 = (ImageView) itemView.findViewById(R.id.img_recy3);
            showImage4 = (ImageView) itemView.findViewById(R.id.img_recy4);
            tv_recy1 = (TextView) itemView.findViewById(R.id.tv_recy1);
            tv_recy2 = (TextView) itemView.findViewById(R.id.tv_recy2);
            this.itemView = itemView;
        }
    }
    public class MyViewHolder2 extends RecyclerView.ViewHolder {
        private final ImageView item2_iv1;
        private final ImageView item2_iv2;
        private final TextView cy_tv_1;
        private final TextView cy_tv_2;
        public MyViewHolder2(View itemView) {
            super(itemView);
            item2_iv1 = (ImageView) itemView.findViewById(R.id.img_ry_01);
            item2_iv2 = (ImageView) itemView.findViewById(R.id.img_ry_02);
            cy_tv_1 = (TextView) itemView.findViewById(R.id.cy_tv_1);
            cy_tv_2 = (TextView) itemView.findViewById(R.id.cy_tv_2);
        }
    }
}
