package www.bawei.com.wangmiaomiaocheckbox0905;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MyAdapter myAdapter;
    private LinearLayoutManager linearLayoutManager;
    private GridLayoutManager gridLayoutManager;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;
    private Button btn_selectall;
    private Button btn_reverse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        btn_selectall = (Button) findViewById(R.id.btn_selectall);
        btn_reverse = (Button) findViewById(R.id.btn_reverse);


        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        gridLayoutManager = new GridLayoutManager(this, 3);


        staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(linearLayoutManager);
        myAdapter = new MyAdapter();
        recyclerView.setAdapter(myAdapter);

        btn_selectall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                myAdapter.selectedAll();

            }
        });


        myAdapter.setOnItemLongClickListener(new MyAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(View v, int position) {


                myAdapter.revertSelected();
            }
        });
    }
}


