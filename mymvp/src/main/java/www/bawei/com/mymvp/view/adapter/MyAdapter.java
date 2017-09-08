package www.bawei.com.mymvp.view.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import www.bawei.com.mymvp.R;
import www.bawei.com.mymvp.model.bean.PersionInfo;

/**
 * Created by wmm on 2017/9/8 0008.
 */

public class MyAdapter extends BaseAdapter {

    private Context context;
    private List<PersionInfo> listinfos;

    public MyAdapter(Context context, List<PersionInfo> listinfos){
        this.context =context;
        this.listinfos = listinfos;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return listinfos.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return listinfos.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        convertView = LayoutInflater.from(context).inflate(R.layout.list_item, null);
        TextView tv = (TextView) convertView.findViewById(R.id.tv);
        PersionInfo persionInfo = listinfos.get(position);
        tv.setText(persionInfo.getNameString());
        if (persionInfo.isChick()) {
            convertView.setBackgroundResource(R.mipmap.ic_launcher);
        } else {
            convertView.setBackgroundColor(Color.parseColor("#f4f4f4"));
        }
        return convertView;
    }
}