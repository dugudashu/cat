package www.bawei.com.mymvp.model;

import android.support.annotation.NonNull;

import java.net.HttpURLConnection;
import java.net.URL;

import www.bawei.com.mymvp.model.bean.RegisterDataBean;

/**
 * Created by wmm on 2017/9/5 0005.
 */
//处理数据
public class RegisterModel <T>{


    private RegisterDataBean dataBean;
    private HttpURLConnection connection;

    public RegisterModel() {
    }
    public void btn_register(@NonNull final DataCallBack<RegisterDataBean> dataCallBack) {
        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    URL url=new URL("http://www.baidu.com");
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(5000);
                    connection.setReadTimeout(5000);
                    int  code= connection.getResponseCode();
                    if (code==302){

                        dataBean = new RegisterDataBean("注册成功");
                        dataCallBack.onGetDataSuccess(dataBean);




                    }else{


                        String responseMessage = connection.getResponseMessage();

                        dataCallBack.onGetDataFail(responseMessage);


                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }


    public interface   DataCallBack<T>{

        void onGetDataSuccess(T t);
        void onGetDataFail(String exception);


    }



}
