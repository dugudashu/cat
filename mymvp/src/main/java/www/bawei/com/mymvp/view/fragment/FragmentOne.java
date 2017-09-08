package www.bawei.com.mymvp.view.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import www.bawei.com.mymvp.R;
import www.bawei.com.mymvp.view.activity.LoadImageAsyncTask;

/**
 * Created by wmm on 2017/9/6 0006.
 */

public class FragmentOne extends Fragment {


    private View view;
    private ViewPager viewpager;
    private List<String> imgeAddress = new ArrayList<String>();
    private int index;

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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_fragmentone, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        viewpager = (ViewPager) view.findViewById(R.id.viewpager);
        imgeAddress.add("https://b-ssl.duitang.com/uploads/item/201411/30/20141130202024_JrMCs.jpeg");
        imgeAddress.add("https://b-ssl.duitang.com/uploads/item/201608/03/20160803173533_wyrkz.jpeg");
        imgeAddress.add("https://b-ssl.duitang.com/uploads/item/201608/09/20160809214423_LrPFU.thumb.700_0.png");
        viewpager.setAdapter(new MyAdapter());
        autoPlay();
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

                    if (bitmap!=null){

                        imageView.setImageBitmap(bitmap);

                    }else{

                        imageView.setImageResource(R.mipmap.ic_launcher);

                    }
                }
            }).execute(imgeAddress.get(position % imgeAddress.size()));
            container.addView(imageView);
            return imageView;

        }


    }


}
