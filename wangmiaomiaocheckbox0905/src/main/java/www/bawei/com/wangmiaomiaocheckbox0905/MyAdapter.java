package www.bawei.com.wangmiaomiaocheckbox0905;

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

/**
 * Created by wmm on 2017/9/5 0005.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    ArrayList<String> list;
    HashMap<Integer, Boolean> isCheckedHashMap;

    public MyAdapter() {
        isCheckedHashMap = new HashMap<>();
        list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            list.add("条目 " + i);
            isCheckedHashMap.put(i, false);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        RecyclerView.LayoutManager layoutManager = ((RecyclerView) parent).getLayoutManager();
        View recyclerViewItem = null;
        if (layoutManager instanceof GridLayoutManager) {
            recyclerViewItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_recycler, parent, false);
        } else if (layoutManager instanceof LinearLayoutManager) {
            recyclerViewItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_ckbox, parent, false);
        }
        return new MyViewHolder(recyclerViewItem);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.title.setText(list.get(position));
        holder.icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItemClickListener != null) {
                    mItemClickListener.onItemClick(v, position);
                }
            }
        });

        holder.icon.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                if (mItemLongClickListener != null) {
                    mItemLongClickListener.onItemLongClick(v, position);
                }
                return true;
            }
        });

        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isCheckedHashMap.put(position, !isCheckedHashMap.get(position));
                notifyDataSetChanged();
            }
        });

        holder.checkBox.setChecked(isCheckedHashMap.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void add(int position) {

        list.add(position + 1, "这是新加的");

        notifyItemRangeChanged(position + 1, getItemCount());
    }

    public void remove(int position) {
        list.remove(position);
        notifyItemRangeRemoved(position, getItemCount());
    }


    public void updata(int position, String content) {
        list.remove(position);
        list.add(position, content);
        notifyItemChanged(position);
    }

    public void selectedAll() {
        Set<Map.Entry<Integer, Boolean>> entries = isCheckedHashMap.entrySet();

        boolean shouldSelectedAll = false;

        for (Map.Entry<Integer, Boolean> entry : entries) {
            Boolean value = entry.getValue();

            if (!value) {
                shouldSelectedAll = true;
                break;
            }
        }

        for (Map.Entry<Integer, Boolean> entry : entries) {
            entry.setValue(shouldSelectedAll);
        }
        notifyDataSetChanged();
    }

    public void revertSelected() {
        Set<Map.Entry<Integer, Boolean>> entries = isCheckedHashMap.entrySet();
        for (Map.Entry<Integer, Boolean> entry : entries) {
            entry.setValue(!entry.getValue());
        }
        notifyDataSetChanged();
    }

    public void singleSelected(int postion) {
        Set<Map.Entry<Integer, Boolean>> entries = isCheckedHashMap.entrySet();
        for (Map.Entry<Integer, Boolean> entry : entries) {
            entry.setValue(false);
        }
        isCheckedHashMap.put(postion, true);
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        View itemView;
        TextView title;
        ImageView icon;
        CheckBox checkBox;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            title = (TextView) itemView.findViewById(R.id.title);
            icon = (ImageView) itemView.findViewById(R.id.icon);
            checkBox = (CheckBox) itemView.findViewById(R.id.checkbox);



        }
    }


    private OnItemClickListener mItemClickListener;
    private OnItemLongClickListener mItemLongClickListener;

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener itemLongClickListener) {
        mItemLongClickListener = itemLongClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View v, int position);
    }

    //长按事件的接口
    public interface OnItemLongClickListener {
        void onItemLongClick(View v, int position);
    }
}