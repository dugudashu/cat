package www.bawei.com.mymvp.model;

import android.support.annotation.NonNull;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import www.bawei.com.mymvp.view.bean.HomeBean;

/**
 * Created by wmm on 2017/9/15 0015.
 */

public class HomeModel<T> {

    private  int page=1;
    public  HomeModel(){
    }
    public void getData(@NonNull final DataHomeCallBack<HomeBean>dataHomeCallBack) {
        OkHttpClient client = new OkHttpClient();
        Request.Builder requestBuilder = new Request.Builder().url("http://apiv3.yangkeduo.com/v5/newlist?page=" + page + "&size=20");
        Request request = requestBuilder.build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
//                String s = e.getMessage().toString();
//                dataHomeCallBack.onCallHomeFail(s);
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String json = response.body().string();
                        Gson gson = new Gson();
                        HomeBean homeBean = gson.fromJson(json, HomeBean.class);
                        dataHomeCallBack.onCallHomeSuccess(homeBean);
                        page++;
            }
        });
    }



    public interface  DataHomeCallBack<T>{
        void onCallHomeSuccess(T t);
        void onCallHomeFail(String fails);

    }



}
