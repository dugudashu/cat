package www.bawei.com.mymvp.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import www.bawei.com.mymvp.view.fragment.MyFragment;

/**
 * Created by wmm on 2017/9/7 0007.
 */

 public  class TabAdapter extends FragmentPagerAdapter {
    private  String []title={"精选","直播","订阅","视频购","社区","清单"};
    public TabAdapter(FragmentManager fm) {
        super(fm);
    }
    @Override
    public Fragment getItem(int position) {

        MyFragment myfragment=new MyFragment();
        return myfragment;
    }
    @Override
    public int getCount() {
        return title.length;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

    }
}
