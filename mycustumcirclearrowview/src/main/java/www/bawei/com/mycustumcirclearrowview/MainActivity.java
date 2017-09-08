package www.bawei.com.mycustumcirclearrowview;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private MyCircleArrow my_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //找到在main.xml定义view的控件
        my_view = (MyCircleArrow) findViewById(R.id.my_view);
    }

    //调用加速的方法
    public void btn_speed(View view) {

        my_view.speedup();

    }
    //调用改变颜色的方法
    public void btn_change(View view) {

        my_view.setColor(Color.BLUE);


    }
    //调用减速的方法
    public void btn_slow(View view) {
        my_view.slowdown();


    }
    //调用暂停/开始的方法
    public void btn_restart(View view) {
        my_view.repaint();

    }
}
