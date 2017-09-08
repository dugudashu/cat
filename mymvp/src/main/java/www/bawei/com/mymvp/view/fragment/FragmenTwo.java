package www.bawei.com.mymvp.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import www.bawei.com.mymvp.R;
import www.bawei.com.mymvp.model.bean.PersionInfo;
import www.bawei.com.mymvp.view.adapter.MyAdapter;


/**
 * Created by wmm on 2017/9/6 0006.
 */


public class FragmenTwo extends Fragment implements AdapterView.OnItemClickListener {
    List<PersionInfo> listinfoInfos = new ArrayList<PersionInfo>();
    private ListView listView;
    private MyAdapter adapter;
    private MyFragmentStore myFragmentstore;


    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_fragmenttwo, container, false);
        return view;


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();

    }


    private void initView() {
        // TODO Auto-generated method stub
        for (int i = 0; i < 10; i++) {
            PersionInfo info = new PersionInfo("常用分类" + i);
            listinfoInfos.add(info);
        }

        //
        listView = (ListView) view.findViewById(R.id.listview);
        //默认
        listinfoInfos.get(0).setChick(true);
        adapter = new MyAdapter(getContext(), listinfoInfos);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(this);

        //创建MyFragment对象
        myFragmentstore = new MyFragmentStore();
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, myFragmentstore);

        Bundle mBundle = new Bundle();
        mBundle.putSerializable("info", listinfoInfos.get(0));
        myFragmentstore.setArguments(mBundle);
        fragmentTransaction.commit();
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {

        PersionInfo info = listinfoInfos.get(position);
        for (int i = 0; i < listinfoInfos.size(); i++) {
            if (listinfoInfos.get(i).getNameString().equals(info.getNameString())) {
                listinfoInfos.get(i).setChick(true);
            } else {
                listinfoInfos.get(i).setChick(false);
            }
        }

        adapter.notifyDataSetChanged();


        //
        //创建MyFragment对象
        myFragmentstore = new MyFragmentStore();
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, myFragmentstore);

        Bundle mBundle = new Bundle();
        mBundle.putSerializable("info", listinfoInfos.get(position));
        myFragmentstore.setArguments(mBundle);
        fragmentTransaction.commit();
    }


}
