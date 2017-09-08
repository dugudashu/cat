package www.bawei.com.mymvp.presenter;

import android.app.Activity;
import android.widget.Toast;

import www.bawei.com.mymvp.model.RegisterModel;
import www.bawei.com.mymvp.model.bean.LoginDataBean;
import www.bawei.com.mymvp.model.bean.RegisterDataBean;
import www.bawei.com.mymvp.model.utils.LoginModel;
import www.bawei.com.mymvp.view.iview.IRegisterAndLoginView;

/**
 * Created by wmm on 2017/9/5 0005.
 */
//处理逻辑
public class RegisterAndLoginPresenter extends  BasePresenter<IRegisterAndLoginView>{


    private  RegisterModel registerModel;
    private  LoginModel loginmodel;

    //在构造器中拿到activity传给我们的接口,当有结果的时候我们就利用这个接口通知activity
    public RegisterAndLoginPresenter(IRegisterAndLoginView iRegisterView) {
        super(iRegisterView);
        registerModel = new RegisterModel();
        loginmodel   =new LoginModel();




    }


    public void btn_register() {

        registerModel.btn_register(new RegisterModel.DataCallBack<RegisterDataBean>() {
            @Override
            public void onGetDataSuccess(RegisterDataBean dataBean) {
                    //view的刷新不应该放在这里,只是为了使用一下
                ((Activity)context()).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(context(), "我拿到父类的环境变量了", Toast.LENGTH_SHORT).show();


                    }
                });
            //利用activity传过来的接口去通知activity刷新界面
                iView.onRegisterSuccessed(dataBean);
                



            }

            @Override
            public void onGetDataFail(String exception) {

                iView.onRegisterFailed(exception);


            }
        });

    }

    public void btn_login() {

    loginmodel.Login(new LoginModel.DataCallBack<LoginDataBean>() {


        @Override
        public void onGetDataSuccess(LoginDataBean loginDataBean) {

            iView.onLoginSuccessed(loginDataBean);

        }

        @Override
        public void onGetDataFail(String exception) {

            iView.onLoginFailed(exception);

        }
    });

    }



}
