package www.bawei.com.mymvp.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import www.bawei.com.mymvp.R;
import www.bawei.com.mymvp.view.adapter.TabAdapter;

/**
 * Created by wmm on 2017/9/6 0006.
 */

public class FragmentThree extends Fragment {

        private View view;
    private TabLayout tablayout;
    private ViewPager viewpagerf1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_fragmentthree,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tablayout = (TabLayout) view.findViewById(R.id.tablayout);
        viewpagerf1 = (ViewPager) view.findViewById(R.id.viewpagerf1);
        viewpagerf1.setAdapter(new TabAdapter(getActivity().getSupportFragmentManager()));
        tablayout.setupWithViewPager(viewpagerf1);
    }




}
