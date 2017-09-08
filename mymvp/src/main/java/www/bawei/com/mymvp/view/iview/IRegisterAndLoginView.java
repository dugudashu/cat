package www.bawei.com.mymvp.view.iview;

import www.bawei.com.mymvp.model.bean.LoginDataBean;
import www.bawei.com.mymvp.model.bean.RegisterDataBean;

/**
 * Created by wmm on 2017/9/5 0005.
 */

//通知RegisterView刷新页面的接口
public interface IRegisterAndLoginView extends IView{

    void onRegisterSuccessed(RegisterDataBean dataBean);
    void onRegisterFailed(String exception);

    void onLoginSuccessed(LoginDataBean dataBean);
    void onLoginFailed(String exception);


}
