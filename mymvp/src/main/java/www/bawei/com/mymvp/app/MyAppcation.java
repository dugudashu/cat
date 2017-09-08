package www.bawei.com.mymvp.app;

import android.app.Application;
import android.content.Context;
import android.os.Environment;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.utils.Log;

import java.io.File;

import static com.umeng.socialize.utils.DeviceConfig.context;

/**
 * Created by wmm on 2017/9/6 0006.
 */

public class MyAppcation extends Application {

    private Context appContext;

    {
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
    }


    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
        initImageLoader(getApplicationContext());
        UMShareAPI.get(this);
        Config.DEBUG = true;


    }

    private void initImageLoader(Context applicationContext) {


        String path = Environment.getExternalStorageDirectory().getPath() + "/hehe";
        File file = new File(path);
        //创建配置ImageLoader(所有的选项都是可选的,只使用那些你真的想定制)，这个可以设定在APPLACATION里面，设置为全局的配置参数
       ImageLoaderConfiguration config=new ImageLoaderConfiguration
                .Builder(this)
                .threadPriority(100)
                .threadPoolSize(3)//线程池内加载的数量
                .memoryCacheSize(2 * 1024 * 1024)
                .memoryCacheExtraOptions(400,400)
                .diskCacheSize(50 * 1024 * 1024)
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())//将保存的时候的URI名称用MD5 加密
                .diskCache(new UnlimitedDiskCache(file))
                //自定义缓存路径//.imageDownloader(new BaseImageDownloader(context, 5 * 1000, 30 * 1000)) // connectTimeout (5 s), readTimeout (30 s)超时时间
                .build();
        ImageLoader.getInstance().init(config);//全局初始化此配置

    }



    public static Context appContext() {

        return appContext();

    }

}
