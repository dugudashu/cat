package www.bawei.com.mymvp.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import www.bawei.com.mymvp.R;

/**
 * Created by wmm on 2017/9/7 0007.
 */

public   class MyFragment   extends Fragment{


    private View view;
    private TextView textView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.activity_myfragment,container,false);
        return view;



    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        textView = (TextView) view.findViewById(R.id.my_tv);



    }
}
