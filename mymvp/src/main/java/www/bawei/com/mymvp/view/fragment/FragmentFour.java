package www.bawei.com.mymvp.view.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import www.bawei.com.mymvp.R;
import www.bawei.com.mymvp.view.adapter.Adapter;

/**
 * Created by wmm on 2017/9/6 0006.
 */

public class FragmentFour extends Fragment {
    private View view;
    MyListView listView;
    static Adapter adapter;
    CheckBox cb_cart_all;
    static Float zongjia;
    static TextView tv_cart_Allprice_1;
    private Button btn_de;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_fragmentfour, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//         getActivity().requestWindowFeature(Window.FEATURE_NO_TITLE);


        tv_cart_Allprice_1 = (TextView) view.findViewById(R.id.tv_cart_Allprice_1);
        listView = (MyListView) view.findViewById(R.id.listview);
        cb_cart_all = (CheckBox) view.findViewById(R.id.cb_cart_all);
        btn_de = (Button) view.findViewById(R.id.btn_de);
        cb_cart_all.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


                if (isChecked) {
                    //全选
                    adapter.setListDataFocuseAble();
                    jisuanZongJia();
                } else {
                    //取消选择
                    adapter.setListDataUnFocuseAble();
                    jisuanZongJia();
                }
                adapter.notifyDataSetChanged();


            }
        });


        btn_de.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                List<Map<String, String>> selectData = getSelectIDList();
                if (selectData.size() != 0) {
                    adapter.removeSelectData(selectData);
                    adapter.setListDataUnFocuseAble();
                    adapter.notifyDataSetChanged();
                }


            }
        });

        adapter = new Adapter(getContext());
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        init();


    }

    private void init() {
        List<Shoucang> listdata = new ArrayList<Shoucang>();
        for (int i = 0; i < 5; i++) {
            Shoucang shoucang = new Shoucang();
            shoucang.setDrugName("第" + i + "台");
            shoucang.setDrugmoney("" + i * 10);
            //有一个id用于删除和向后台传递数据
            shoucang.setShoucangId("" + (199 + i));
            shoucang.setDrugFlag(3);
            listdata.add(shoucang);
        }
        adapter.setListData(listdata);


    }

    static public void jisuanZongJia() {
        final List<Map<String, String>> selectData = getSelectIDList();
        float s = 0;
        for (int i = selectData.size() - 1; i >= 0; i--) {
            s += Float.valueOf(selectData.get(i).get("zongjia"));
        }
        zongjia = s;
        shoppingCartSetTotalPrice(zongjia);
    }

    //设置总价
    public static void shoppingCartSetTotalPrice(float price) {
        tv_cart_Allprice_1.setText("" + price);
    }

    //CB点击
    public static void shoppingCartRefush(List<Shoucang> listDataRec) {
        jisuanZongJia();
    }

    static public List<Map<String, String>> getSelectIDList() {
        return adapter.getselectData();
    }


}
