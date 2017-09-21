package www.bawei.com.mymvp.presenter;

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
import www.bawei.com.mymvp.model.utils.FaxianModel;
import www.bawei.com.mymvp.view.adapter.MyRvAdapter;
import www.bawei.com.mymvp.view.bean.Faxian;
import www.bawei.com.mymvp.view.iview.IFaxianView;

/**
 * Created by wmm on 2017/9/15 0015.
 */

public class FaxianPresenter {
    private final FaxianModel faxianModel;
    private IFaxianView iFaxianView;
    public FaxianPresenter() {
        faxianModel = new FaxianModel();
    }
    public FaxianPresenter(@NonNull  IFaxianView iFaxianView) {
        this.iFaxianView = iFaxianView;
        faxianModel = new FaxianModel();
    }
    public void getData() {
        faxianModel.getData(new FaxianModel.CallFaXian<Faxian>() {
            @Override
            public void CallFaXianSuccess(Faxian faxian) {
                iFaxianView.faXianSuccess(faxian);
            }
            @Override
            public void CallFaXianFail(String faile) {
//                iFaxianView.faXianFail(faile);
            }
        });
    }
}
