package www.bawei.com.mymvp.view.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.swiperefreshlayoutlibrary.SwipyRefreshLayout;
import com.bawei.swiperefreshlayoutlibrary.SwipyRefreshLayoutDirection;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import www.bawei.com.mymvp.R;
import www.bawei.com.mymvp.presenter.FaxianPresenter;
import www.bawei.com.mymvp.view.adapter.MyAdapter;
import www.bawei.com.mymvp.view.adapter.MyRvAdapter;
import www.bawei.com.mymvp.view.bean.Faxian;
import www.bawei.com.mymvp.view.iview.IFaxianView;

/**
 * Created by wmm on 2017/9/7 0007.
 */

public class MyFragment extends Fragment implements IFaxianView {
    private View view;
    private RecyclerView recyclerview;
    private List<Faxian.ResultBean.ListBean> list;
    private SwipyRefreshLayout swipt;
    private int start = 0;
    Handler handler = null;
    private FaxianPresenter faxianPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_myfragment, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        handler=new Handler();
        faxianPresenter = new FaxianPresenter(this);
        faxianPresenter.getData();
        initView();
        initData();
        faxianPresenter.getData();
        swipt.setDirection(SwipyRefreshLayoutDirection.BOTH);
        swipt.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(int index) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipt.setRefreshing(false);
                        Toast.makeText(getContext(), "加载成功", Toast.LENGTH_SHORT).show();
                        start++;
                        faxianPresenter.getData();
                    }
                }, 2000);
            }

            @Override
            public void onLoad(int index) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipt.setRefreshing(false);
                        start++;
                        faxianPresenter.getData();
                        Toast.makeText(getContext(), "加载成功", Toast.LENGTH_SHORT).show();
                    }
                }, 2000);
            }
        });
    }
    private void initData() {
        LinearLayoutManager lineManager = new LinearLayoutManager(getContext());
        lineManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(lineManager);
    }

    private void initView() {
        recyclerview = (RecyclerView) view.findViewById(R.id.recyclerview);
        swipt = (SwipyRefreshLayout) view.findViewById(R.id.swipt);
    }
    @Override
    public void faXianSuccess(final Faxian faxian) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getContext(), "请求成功", Toast.LENGTH_SHORT).show();
                list = faxian.getResult().getList();
                MyRvAdapter myadapter = new MyRvAdapter(getContext(), list);
                recyclerview.setAdapter(myadapter);
            }
        });
    }

    @Override
    public void faXianFail(String s) {


        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {

                Toast.makeText(getContext(), "请求失败", Toast.LENGTH_SHORT).show();


            }
        });


    }
}
