package www.bawei.com.mymvp.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.ViewGroup;

import java.util.List;

import www.bawei.com.mymvp.model.bean.Fenlei;

/**
 * Created by wmm on 2017/9/21 0021.
 */

public class MyTableAdapter extends FragmentPagerAdapter {
    private FragmentManager manager;
    private List<Fenlei.DatasBean.ClassListBean> list;
    public MyTableAdapter(FragmentManager manager, List<Fenlei.DatasBean.ClassListBean> class_list) {
        super(manager);
        this.manager = manager;
        this.list = class_list;
    }
    @Override
    public Fragment getItem(int position) {
        MyFragmentStore myfragmentstore = new MyFragmentStore();
        String gc_id = list.get(position).getGc_id();
        Bundle bundle = new Bundle();
        bundle.putString("id",gc_id);
        myfragmentstore.setArguments(bundle);
        return myfragmentstore;
    }
    @Override
    public int getCount() {
        return list.size();
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return list.get(position).getGc_name();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

    }
}
