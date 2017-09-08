package www.bawei.com.mymvp.presenter;

import android.content.Context;

import www.bawei.com.mymvp.app.MyAppcation;
import www.bawei.com.mymvp.view.iview.IView;

/**
 * Created by wmm on 2017/9/6 0006.
 */

//所有presenter的父类
public class BasePresenter<T extends IView  > {

    protected T iView;


    public BasePresenter(T iView) {
        this.iView =iView;

    }
//如果接口的环境变量为空的话就返回application的环境变量
    Context context(){

        if (iView!=null&&iView.context()!=null)
        {
            return  iView.context();
        }

        return MyAppcation.appContext() ;

    }



}
