package www.bawei.com.mymvp.model.utils;

import android.support.annotation.NonNull;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import www.bawei.com.mymvp.view.adapter.MyRvAdapter;
import www.bawei.com.mymvp.view.bean.Faxian;

/**
 * Created by wmm on 2017/9/15 0015.
 */


public class FaxianModel <T>{

    private int start=0;
    public FaxianModel() {
    }
    public void getData(@NonNull final CallFaXian<Faxian>callFaXian) {
        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(5, TimeUnit.SECONDS)
                .build();
        RequestBody requestbody = new FormBody.Builder()
//        http://api.jisuapi.com/news/get?channel=%E5%A4%B4%E6%9D%A1&start=0&num=10&appkey=609081e17a98144c
                .add("channel", "娱乐")
                .add("start", "" + start + "")
                .add("num", "10")
                .add("appkey", "609081e17a98144c")
                .build();
        Request request = new Request.Builder()
                .url("http://api.jisuapi.com/news/get")
                .post(requestbody).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
//              String s=e.getMessage().toString();
//                callFaXian.CallFaXianFail(s);
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final String json = response.body().string();
                Gson gson = new Gson();
                Faxian faxian = gson.fromJson(json, Faxian.class);
                callFaXian.CallFaXianSuccess(faxian);
                start++;
            }
        });
    }

    public interface CallFaXian<T> {

        void CallFaXianSuccess(T t);

        void CallFaXianFail(String s);


    }


}
