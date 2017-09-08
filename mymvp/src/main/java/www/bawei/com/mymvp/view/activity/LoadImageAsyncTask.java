package www.bawei.com.mymvp.view.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by wmm on 2017/9/8 0008.
 */

public class LoadImageAsyncTask extends AsyncTask<String, Void, Bitmap> {


    //1.定义一个监听(接口)

    public interface ImageCallBackListener {


        void imageCallBack(Bitmap bitmap);


    }


    //构造方法

    public LoadImageAsyncTask(ImageCallBackListener imageCallBackListener) {

        this.imageCallBackListener = imageCallBackListener;

    }


    //定义变量接收调用者所传递过来的监听对象

    private ImageCallBackListener imageCallBackListener;


    @Override

    protected void onPostExecute(Bitmap bitmap) {

        super.onPostExecute(bitmap);

        System.out.println("数据解析完成-->");

        //回调图片对象

        imageCallBackListener.imageCallBack(bitmap);


    }


    @Override

    protected Bitmap doInBackground(String... params) {


        try {

            String path = params[0];

            URL url = new URL(path);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");

            connection.setConnectTimeout(5000);

            connection.setReadTimeout(5000);


            //服务器响应

            int code = connection.getResponseCode();

            if (code == HttpURLConnection.HTTP_OK) {


                InputStream is = connection.getInputStream();


                return BitmapFactory.decodeStream(is);

            }


        } catch (Exception e) {

            e.printStackTrace();

        }

        return null;

    }

}