package www.bawei.com.wangmiamiaorecycler0907;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recycler;
    private LinearLayoutManager layoutManager;
    private MyAdapter myadapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recycler = (RecyclerView) findViewById(R.id.recycler);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        final MyItemDecoration  myItemDecoration=new MyItemDecoration(this,layoutManager.getOrientation());
        recycler.setLayoutManager(layoutManager);
        recycler.addItemDecoration(myItemDecoration);
        myadapter = new MyAdapter();
        recycler.setAdapter(myadapter);
        myadapter.SetOnMyClickListener(new MyAdapter.OnMyClickListener() {
            @Override
            public void mOnClick(View v, int position) {


                Toast.makeText(MainActivity.this, "撒大奖哦", Toast.LENGTH_SHORT).show();

            }
        });






    }



}
