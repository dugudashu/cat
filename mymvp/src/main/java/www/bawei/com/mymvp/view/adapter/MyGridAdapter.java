package www.bawei.com.mymvp.view.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import www.bawei.com.mymvp.R;
import www.bawei.com.mymvp.model.bean.Fenlei;

/**
 * Created by wmm on 2017/9/21 0021.
 */

public class MyGridAdapter extends RecyclerView.Adapter<MyGridAdapter.ViewHolder> {


    private Context context;
    private List<Fenlei.DatasBean.ClassListBean> list;

    public MyGridAdapter(Context context, List<Fenlei.DatasBean.ClassListBean> class_list) {

        this.context = context;
        this.list = class_list;


    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder viewholder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_grid_item, parent, false));
        return viewholder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv_grid_vh.setText(list.get(position).getText());

//        Glide.with(context).load(list.get(position).getImage()).into(holder.img_grid_vh);


    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        private final ImageView img_grid_vh;
        private final TextView tv_grid_vh;

        public ViewHolder(View itemView) {

            super(itemView);

            img_grid_vh = (ImageView) itemView.findViewById(R.id.img_grid_vh);
            tv_grid_vh = (TextView) itemView.findViewById(R.id.tv_grid_vh);


        }
    }
}
