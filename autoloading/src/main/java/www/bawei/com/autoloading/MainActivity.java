package www.bawei.com.autoloading;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;

public class MainActivity extends AppCompatActivity {


    private ListView lv;
    private ArrayList<String> list;
    private int index = 0;
    private MyAdapter myAdapter;


//    private Button btn_more;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.lv);
//        btn_more = (Button) findViewById(R.id.btn_more);

        addData(index);
        myAdapter = new MyAdapter();
        lv.setAdapter(myAdapter);
        lv.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

                int items = view.getCount();
                int lastitems = view.getLastVisiblePosition();
                if (scrollState == SCROLL_STATE_IDLE) {

                    if (lastitems == items - 1) {

                        addData(items);
                        myAdapter.notifyDataSetChanged();

                    }
                }
            }


            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {


            }
        });


    }

    private void addData(int index) {

        list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {


            list.add("这是第" + (index + i) + "条数据");

        }
    }

    private class MyAdapter extends BaseAdapter {

        private ViewHolder viewholder;

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {

                convertView = convertView.inflate(MainActivity.this, R.layout.item, null);

                viewholder = new ViewHolder();
                viewholder.textview = (TextView) convertView.findViewById(R.id.tvContent);
                convertView.setTag(viewholder);


            } else {

                viewholder = (ViewHolder) convertView.getTag();

            }

            viewholder.textview.setText(list.get(position));


            return convertView;
        }

        class ViewHolder {

            TextView textview;

        }

    }


}
