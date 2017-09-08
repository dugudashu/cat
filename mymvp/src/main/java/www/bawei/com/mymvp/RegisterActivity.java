package www.bawei.com.mymvp;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

import www.bawei.com.mymvp.model.bean.LoginDataBean;
import www.bawei.com.mymvp.model.bean.RegisterDataBean;
import www.bawei.com.mymvp.presenter.RegisterAndLoginPresenter;
import www.bawei.com.mymvp.view.fragment.FragmenTwo;
import www.bawei.com.mymvp.view.fragment.FragmentFive;
import www.bawei.com.mymvp.view.fragment.FragmentFour;
import www.bawei.com.mymvp.view.fragment.FragmentOne;
import www.bawei.com.mymvp.view.fragment.FragmentThree;
import www.bawei.com.mymvp.view.iview.IRegisterAndLoginView;

//处理View
public class RegisterActivity extends FragmentActivity   {

    private RegisterAndLoginPresenter registerPresenter;
    private RadioButton shouye;
    private RadioButton fenlei;
    private RadioButton faxian;
    private RadioButton gouwuche;
    private RadioButton wode;
    private RadioGroup rd_group;
    private int mIndex;
    private FrameLayout content;
    private Fragment[] mFragments;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        registerPresenter = new RegisterAndLoginPresenter(this);
        content = (FrameLayout) findViewById(R.id.content);
        initView();
        initFragment();
    }

    private void initView() {
        rd_group = (RadioGroup) findViewById(R.id.rd_group);
        shouye = (RadioButton) findViewById(R.id.shouye);
        fenlei = (RadioButton) findViewById(R.id.fenlei);
        faxian = (RadioButton) findViewById(R.id.faxian);
        gouwuche = (RadioButton) findViewById(R.id.gouwuche);
        wode = (RadioButton) findViewById(R.id.wode);
        rd_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {

                for (int i = 0; i <group.getChildCount() ; i++) {

                    RadioButton rb = (RadioButton)group.getChildAt(i);
                    if (rb.isChecked()){
                        setIndexSelected(i);
                        break;
                    }
                }
            }
        });
    }
    private  void initFragment(){
        FragmentOne fragmentone=new FragmentOne();
        FragmenTwo  fragmenttwo=new FragmenTwo();
        FragmentThree fragmentthree=new FragmentThree();
        FragmentFour fragmentfour=new FragmentFour();
        FragmentFive fragmentfive=new FragmentFive();
        mFragments = new Fragment[]{fragmentone,fragmenttwo,fragmentthree,fragmentfour,fragmentfive};
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.content,fragmentone).commit();
        setIndexSelected(0);
    }


    private void setIndexSelected(int i){

        if(mIndex==i){
            return;
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft= fragmentManager.beginTransaction();


        //隐藏
        ft.hide(mFragments[mIndex]);
        //判断是否添加
        if(!mFragments[i].isAdded()){
            ft.add(R.id.content,mFragments[i]).show(mFragments[i]);
        }else {
            ft.show(mFragments[i]);
        }

        ft.commit();
        //再次赋值
        mIndex=i;

    }

    public void btn_register(View view) {

        registerPresenter.btn_register();

    }
    public void btn_login(View view) {

        registerPresenter.btn_login();


    }

//    @Override
//    public void onRegisterSuccessed(RegisterDataBean dataBean) {
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//
//                Toast.makeText(RegisterActivity.this, "注册成功!", Toast.LENGTH_SHORT).show();
//
//            }
//        });
//
//    }
//
//    @Override
//    public void onRegisterFailed(String exception) {
//
//
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//
//                Toast.makeText(RegisterActivity.this, "注册失败!", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//
//    }
//
//    @Override
//    public void onLoginSuccessed(LoginDataBean dataBean) {
//
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//
//                Toast.makeText(RegisterActivity.this, "登陆成功!", Toast.LENGTH_SHORT).show();
//
//            }
//        });
//    }
//
//
//
//    @Override
//    public void onLoginFailed(String exception) {
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//
//                Toast.makeText(RegisterActivity.this, "登陆失败!", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//
//    @Override
//    public Context context() {
//
//
//
//        return this;
//    }


}
