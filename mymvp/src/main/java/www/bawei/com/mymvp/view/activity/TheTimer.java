package www.bawei.com.mymvp.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import www.bawei.com.mymvp.R;
import www.bawei.com.mymvp.RegisterActivity;

/**
 * Created by wmm on 2017/9/6 0006.
 */

public class TheTimer extends Activity {

    private Button btn_bypass;
    private Timer timer;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int time = (int) msg.obj;
            if (msg.what== 0) {
                tv_timer.setText("" + time);
                if (time==0){
                    Intent intent = new Intent(TheTimer.this, RegisterActivity.class);
                    startActivity(intent);
                    finish();
                }

            }




        }
    };
    private TextView tv_timer;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_start);
        btn_bypass = (Button) findViewById(R.id.btn_bypass);
        tv_timer = (TextView) findViewById(R.id.tv_timer);
        killsanpang();
        btn_bypass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TheTimer.this, RegisterActivity.class);
                startActivity(intent);
                TheTimer.this.finish();
            }
        });


    }


    public void killsanpang() {
        timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            int i = 4;
            @Override
            public void run() {

                if (i!=0){
                    i--;
                }
                Message msg = new Message();
                msg.obj = i;
                msg.what=0;

                handler.sendMessage(msg);
            }
        };
        timer.schedule(timerTask, 1000, 1000);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer!=null){
            timer.cancel();
        }
    }
}

