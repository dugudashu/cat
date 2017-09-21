package www.bawei.com.mymvp.view.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.bawei.swiperefreshlayoutlibrary.SwipyRefreshLayout;
import com.bawei.swiperefreshlayoutlibrary.SwipyRefreshLayoutDirection;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import www.bawei.com.mymvp.R;
import www.bawei.com.mymvp.app.MyAppcation;
import www.bawei.com.mymvp.model.bean.Fenlei;
import www.bawei.com.mymvp.view.adapter.MyGridAdapter;

/**
 * Created by wmm on 2017/9/8 0008.
 */

public class MyFragmentStore extends Fragment {
    private View view;
    private Handler handler;
    private List<Fenlei.DatasBean.ClassListBean> class_list;
//    private MyGridAdapter gridadapter;
//    private SwipyRefreshLayout swipflei;
    private RecyclerView recycler;
    private GridLayoutManager gridmanager;
    private String id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_gridview, container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        gridview = (GridView) view.findViewById(R.id.gridview);
        handler = new Handler();
        recycler = (RecyclerView) view.findViewById(R.id.ryflei);
        gridmanager = new GridLayoutManager(getContext(), 3);
        recycler.setLayoutManager(gridmanager);
        id = getArguments().getString("id");
        System.out.println("=================================="+ id);
        getData();
    }

    private void getData() {

        OkHttpClient client = MyAppcation.getAppContext();
        Request request = new Request.Builder().get().url("http://169.254.183.9/mobile/index.php?act=goods_class&gc_id=" + id).build();
        client.newCall(request).enqueue(new Callback() {
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
//                        class_list.get(0).getGc_id();
                        MyGridAdapter myGridAdapter = new MyGridAdapter(getActivity(), class_list);
                        recycler.setAdapter(myGridAdapter);
                    }
                });
            }
        });
    }
}
















