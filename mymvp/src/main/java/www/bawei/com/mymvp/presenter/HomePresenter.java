package www.bawei.com.mymvp.presenter;

import android.support.annotation.NonNull;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import www.bawei.com.mymvp.model.HomeModel;
import www.bawei.com.mymvp.view.adapter.MyMoreAdapter;
import www.bawei.com.mymvp.view.bean.HomeBean;
import www.bawei.com.mymvp.view.fragment.FragmentOne;
import www.bawei.com.mymvp.view.iview.IHomeView;

/**
 * Created by wmm on 2017/9/15 0015.
 */

public class HomePresenter {

    private final HomeModel homodel;
    private IHomeView iHomeView;

    public HomePresenter() {

        homodel = new HomeModel();


    }

    public HomePresenter(@NonNull IHomeView iHomeView) {

        this.iHomeView=iHomeView;
        homodel=new HomeModel();
    }


    public void getData() {

        homodel.getData(new HomeModel.DataHomeCallBack<HomeBean>() {
            @Override
            public void onCallHomeSuccess(HomeBean homeBean) {

                iHomeView.onHomeSuccess(homeBean);

            }

            @Override
            public void onCallHomeFail(String fails) {
             iHomeView.onHomeFail(fails);


            }
        });



    }



}
