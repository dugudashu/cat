package www.bawei.com.mymvp.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import www.bawei.com.mymvp.R;
import www.bawei.com.mymvp.model.bean.PersionInfo;

/**
 * Created by wmm on 2017/9/8 0008.
 */

public class MyFragmentStore extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.myfragmenta , null);
        TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
        //得到数据
        PersionInfo info = (PersionInfo) getArguments().getSerializable("info");
        tv_title.setText(info.getNameString());
        return view;
    }
}