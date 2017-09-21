package www.bawei.com.mymvp.view.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import www.bawei.com.mymvp.R;
import www.bawei.com.mymvp.view.fragment.FragmentFour;
import www.bawei.com.mymvp.view.fragment.Shoucang;

/**
 * Created by wmm on 2017/9/12 0012.
 */

public class Adapter extends BaseAdapter {
    List<Map<String,String>> sd;
    Map<String,String> selectDataItem;
    private Context context;
    SharedPreferences sp;

    private List<Shoucang> listData=new ArrayList<>();

    public Adapter(Context context) {
        super();
        sp =context.getSharedPreferences("shop_shoucang_data", Context.MODE_PRIVATE);
        this.context = context;
    }
    public List<Shoucang> getListData() {
        return listData;
    }

    public void setListData(List<Shoucang> listData) {
        this.listData = listData;
    }


    //2显示checkBox并选中，3显示但未选中

    public void setListDataUnFocuseAble(){
        for(int idx = 0;idx < this.listData.size();idx++){
            this.listData.get(idx).setDrugFlag(3);
        }
    }

    public void setListDataFocuseAble(){
        for(int idx = 0;idx < this.listData.size();idx++){
            this.listData.get(idx).setDrugFlag(2);
        }
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }



    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        DrugListHolder holder=null;
        if(view==null){
            holder=new DrugListHolder();
            view= LayoutInflater.from(context).inflate(R.layout.shoucang_druglist_item, null);
            holder.itemName  =(TextView) view.findViewById(R.id.drugName);
            holder.itemMoney  =(TextView) view.findViewById(R.id.drugmoney);
            holder.cb_choice  =(CheckBox) view.findViewById(R.id.cb_choice_person_shoucang);
            view.setTag(holder);
        }else{
            holder=(DrugListHolder) view.getTag();
        }

        holder.cb_choice.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    listData.get(position).setDrugFlag(2);
                    FragmentFour.shoppingCartRefush(listData);
                    Toast.makeText(context,"您选中了第"+position+"项",Toast.LENGTH_SHORT).show();
                }else{
                    listData.get(position).setDrugFlag(3);
                    FragmentFour.shoppingCartRefush(listData);
                    Toast.makeText(context, "您取消中了第" + position + "项", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //请求列表展示图
        Shoucang Shoucang=listData.get(position);
        //全选和取消选择
        if(Shoucang.getDrugFlag()==2){
            holder.cb_choice.setChecked(true);
        }else if(Shoucang.getDrugFlag()==3){
            holder.cb_choice.setChecked(false);
        }


        //ImageCacheUtil.getInstance().displayImage(context,holder.itemimage1,listData.get(position).getImgIv1());
        holder.itemName.setText("" + Shoucang.getDrugName());
        holder.itemMoney.setText("" + Shoucang.getDrugmoney());

        final String shoucangId= listData.get(position).getShoucangId();
        return view;
    }


    //列表数据
    public final class DrugListHolder {
        //列表名
        TextView itemName;
        TextView itemMoney;
        CheckBox cb_choice;
    }

    public List<Map<String,String>> getselectData(){
        sd = new  ArrayList<Map<String,String>>();
        if(this.listData.size() > 0){
            for(int idx = 0;idx < this.listData.size();idx++){
                if(this.listData.get(idx).getDrugFlag() == 2){
                    selectDataItem = new HashMap<String,String>();
                    selectDataItem.put("id",this.listData.get(idx).getShoucangId());
                    selectDataItem.put("zongjia",String.valueOf(listData.get(idx).getDrugmoney()));
                    sd.add(selectDataItem);
                }
            }
        }
        return sd;
    }



    //删除成功后更新数据
    public void removeSelectData(List<Map<String, String>> selectData){
        if(selectData.size() > 0){
            for(int idx = this.listData.size()-1;idx >= 0;idx--) {
                for (int i = 0; i < selectData.size(); i++){
                    if ((""+this.listData.get(idx).getShoucangId()).equals(""+selectData.get(i).get("id"))) {
                        this.listData.remove(idx);
                        break;
                    }
                }
            }
        }
    }








}