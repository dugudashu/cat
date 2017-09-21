package www.bawei.com.mymvp.view.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.swiperefreshlayoutlibrary.SwipyRefreshLayout;
import com.bawei.swiperefreshlayoutlibrary.SwipyRefreshLayoutDirection;
import com.google.gson.Gson;
import com.library.zxing.activity.QRCodeScanFragment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import cz.msebera.android.httpclient.client.methods.RequestBuilder;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import www.bawei.com.mymvp.R;
import www.bawei.com.mymvp.presenter.HomePresenter;
import www.bawei.com.mymvp.view.activity.LoadImageAsyncTask;
import www.bawei.com.mymvp.view.adapter.MyMoreAdapter;
import www.bawei.com.mymvp.view.bean.HomeBean;
import www.bawei.com.mymvp.view.iview.IHomeView;


/**
 * Created by wmm on 2017/9/6 0006.
 */

public class FragmentOne extends QRCodeScanFragment implements IHomeView {
    private int page = 1;
    private View view;
    private ViewPager viewpager;
    private List<String> imgeAddress = new ArrayList<String>();
    private int index;
    private int index2 = 0;
    private Button btn_saomiao;
    private ViewPager viewpager2;
    private ArrayList<View> viewslist;
    private RadioGroup radio_g;
    private RecyclerView recyclerv;
    private SwipyRefreshLayout swip;
    private Handler handler2 = null;
    private List<HomeBean.GoodsListBean> goods_list;


    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                int m = (int) msg.obj;
                viewpager.setCurrentItem(m % imgeAddress.size());
            }
        }
    };
    private HomePresenter homePresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_fragmentone, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        handler2 = new Handler();
        homePresenter = new HomePresenter(this);
        homePresenter.getData();
        initView();
        initData();
//        getData(new View(getContext()));


        viewpager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
        homePresenter.getData();
        swip.setDirection(SwipyRefreshLayoutDirection.BOTH);
        swip.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(int index) {
                handler2.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swip.setRefreshing(false);
                        Toast.makeText(getContext(), "加载成功", Toast.LENGTH_SHORT).show();
                        page++;
                        homePresenter.getData();
                    }
                }, 2000);

            }

            @Override
            public void onLoad(int index) {
                handler2.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swip.setRefreshing(false);
                        page++;
                        homePresenter.getData();
                        Toast.makeText(getContext(), "加载成功", Toast.LENGTH_SHORT).show();
                    }
                }, 2000);

            }
        });


        btn_saomiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startScanQRCode();
            }
        });


        viewpager2 = (ViewPager) view.findViewById(R.id.viewpager2);
        radio_g = (RadioGroup) view.findViewById(R.id.radio_g);


        viewslist = new ArrayList<>();

        View viwe1 = view.inflate(getActivity(), R.layout.activity_viewone, null);
        View viwe2 = view.inflate(getActivity(), R.layout.activity_viewtwo, null);


        viewslist.add(viwe1);
        viewslist.add(viwe2);

        radio_g.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // 循环遍历radiogroup的数量
                for (int i = 0; i < radio_g.getChildCount(); i++) {
                    // 如果当前radiobutton被选中的话，将对应索引的viewpager设置成当前显示页面
                    RadioButton rb = (RadioButton) radio_g.getChildAt(i);
                    if (rb.isChecked()) {
                        viewpager.setCurrentItem(i);
                    }
                }
            }
        });


        viewpager2.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return viewslist.size();
            }


            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {


                container.removeView((View) object);

            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {


                View v = viewslist.get(position);
                container.addView(v);
                return v;


            }
        });

        viewpager2.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                RadioButton radioBtn = (RadioButton) radio_g.getChildAt(position);
                for (int i = 0; i < viewslist.size(); i++) {
                    if (position == i) {
                        radioBtn.setChecked(true);
                    }
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        RadioButton rb1 = (RadioButton) radio_g.getChildAt(index2);
        rb1.setChecked(true);
        viewpager.setCurrentItem(index2);
        imgeAddress.add("http://img2.imgtn.bdimg.com/it/u=4269919569,2274362830&fm=214&gp=0.jpg");
        imgeAddress.add("http://pic.58pic.com/58pic/12/13/33/80358PIC9xp.jpg");
        imgeAddress.add("http://www.adquan.com/upload/20151027/1445928404555870.jpg");
        imgeAddress.add("http://img.zcool.cn/community/01c0745543b1180000019ae9204fb6.jpg@900w_1l_2o_100sh.jpg");
        viewpager.setAdapter(new MyAdapter());
        autoPlay();


    }



    private void initData() {

//        new FullyLinearLayoutManager();

        LinearLayoutManager linearmanager = new LinearLayoutManager(getContext());
        linearmanager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerv.setLayoutManager(linearmanager);


    }

    private void initView() {
        viewpager = (ViewPager) view.findViewById(R.id.viewpager);
        btn_saomiao = (Button) view.findViewById(R.id.btn_saomiao);
        swip = (SwipyRefreshLayout) view.findViewById(R.id.swip);
        recyclerv = (RecyclerView) view.findViewById(R.id.recyclerv);
    }

    private void autoPlay() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Message message = new Message();
                message.what = 0;

                message.obj = index;
                System.out.println("index--------" + index);
                handler.sendMessage(message);
                index++;
            }
        }, 3000, 3000);
    }

    @Override
    public void onHomeSuccess(final HomeBean homeBean) {

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getContext(), "请求成功", Toast.LENGTH_SHORT).show();
                goods_list = homeBean.getGoods_list();
                final MyMoreAdapter mymoreadapter = new MyMoreAdapter(getContext(), goods_list);
                recyclerv.setAdapter(mymoreadapter);
            }
        });
    }
    @Override
    public void onHomeFail(String failes) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getContext(), "请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private class MyAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return imgeAddress.size();
        }
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            final ImageView imageView = new ImageView(getContext());
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                LoadImageAsyncTask loadImageAsyncTask = (LoadImageAsyncTask) new LoadImageAsyncTask(new LoadImageAsyncTask.ImageCallBackListener() {
                @Override
                public void imageCallBack(Bitmap bitmap) {
                    if (bitmap != null) {
                        imageView.setImageBitmap(bitmap);
                    } else {
                        imageView.setImageResource(R.mipmap.ic_launcher);
                    }
                }
            }).execute(imgeAddress.get(position % imgeAddress.size()));
            container.addView(imageView);
            return imageView;
        }
    }


}
