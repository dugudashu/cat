package www.bawei.com.mymvp.view.adapter;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import www.bawei.com.mymvp.R;
import www.bawei.com.mymvp.model.bean.Fenlei;
import www.bawei.com.mymvp.model.bean.PersionInfo;

/**
 * Created by wmm on 2017/9/8 0008.
 */

public class MyAdapter extends BaseAdapter {

    private Context context;
    private List<Fenlei.DatasBean.ClassListBean> list;
    private ViewHolder viewholder;

    public MyAdapter(Context context, List<Fenlei.DatasBean.ClassListBean> lista) {
        this.context = context;
        this.list = lista;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = convertView.inflate(context, R.layout.activity_fenlei_item, null);
            viewholder = new ViewHolder();
            viewholder.textview = (TextView) convertView.findViewById(R.id.tv_fenlei_vh);
            convertView.setTag(viewholder);
        } else {
            viewholder = (ViewHolder) convertView.getTag();
        }

//       String gc_id = list.get(position).getGc_id();
//        Bundle bundle = new Bundle();
//        bundle.putString("id",gc_id);
//        f1.setArguments(bundle);





        viewholder.textview.setText(list.get(position).getGc_name());
        return convertView;
    }

    class ViewHolder {
        TextView textview;
    }
}