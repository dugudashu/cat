package www.bawei.com.mymvp.view.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.widget.TabView;
import www.bawei.com.mymvp.R;
import www.bawei.com.mymvp.app.MyAppcation;
import www.bawei.com.mymvp.model.bean.Fenlei;
import www.bawei.com.mymvp.model.bean.PersionInfo;
import www.bawei.com.mymvp.view.adapter.MyAdapter;


/**
 * Created by wmm on 2017/9/6 0006.
 */


public class FragmenTwo extends Fragment {
    private View view;
    private ListView lv;
    private Handler handler = null;
    private List<Fenlei.DatasBean.ClassListBean> class_list;
    private MyAdapter myadapter;
    private FrameLayout fragment_container;
    private MyFragmentStore fragmentStore;
    private VerticalTabLayout vtablayout;
    private ViewPager vviewpager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_fragmenttwo, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        handler = new Handler();
        initView();
    }

    private void initView() {
        vtablayout = (VerticalTabLayout) view.findViewById(R.id.vtablayout);
        vviewpager = (ViewPager) view.findViewById(R.id.vviewpager);

        getData();
    }

    private void getData() {
        OkHttpClient okhttpclient = MyAppcation.getAppContext();
        Request request = new Request.Builder().url("http://169.254.183.9/mobile/index.php?act=goods_class").get().build();
        okhttpclient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String str = response.body().string();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Gson gson = new Gson();
                        Fenlei fenlei = gson.fromJson(str, Fenlei.class);
                        class_list = fenlei.getDatas().getClass_list();
                        vviewpager.setAdapter(new MyTableAdapter(getActivity().getSupportFragmentManager(), class_list));
                        vtablayout.setupWithViewPager(vviewpager);
// myadapter = new MyAdapter(getContext(), class_list);
//                        lv.setAdapter(myadapter);
                    }
                });
            }
        });
    }
}



