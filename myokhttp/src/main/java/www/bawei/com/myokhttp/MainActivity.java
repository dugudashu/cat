package www.bawei.com.myokhttp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.guozheng.okhttputils.okhttp.CallBackUtil;
import com.guozheng.okhttputils.okhttp.OkhttpUtil;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void OnClickGet(View v) {


        OkHttpClient client = new OkHttpClient();
        Request.Builder requestBuilder = new Request.Builder().url("http://www.baidu.com");
        requestBuilder.method("GET", null);
        Request request = requestBuilder.build();
        Call mcall = client.newCall(request);
        mcall.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {


            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                if (null != response.cacheResponse()) {
                    String str = response.cacheResponse().toString();
                    Log.i("wangsu", "cache--" + str);


                } else {
                    response.body().string();
                    String str = response.networkResponse().toString();

                    Log.i("wangsu", "network--" + str);


                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        Toast.makeText(MainActivity.this, "请求成功", Toast.LENGTH_SHORT).show();


                    }
                });


            }
        });


    }

    public void OnClickPost(View v) {
        String url = "https://www.baidu.com/";
        HashMap<String, String> paramsMap = new HashMap<>();
        paramsMap.put("title","title");
        OkhttpUtil.okHttpPost(url, paramsMap, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {

                Toast.makeText(MainActivity.this, "失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String response) {
                Toast.makeText(MainActivity.this, "成功", Toast.LENGTH_SHORT).show();


            }
        });


    }


}
