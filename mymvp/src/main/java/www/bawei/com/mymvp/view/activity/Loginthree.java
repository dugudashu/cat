package www.bawei.com.mymvp.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

import www.bawei.com.mymvp.R;
import www.bawei.com.mymvp.model.Dao.UserDao;
import www.bawei.com.mymvp.view.fragment.FragmentFive;
/**
 * Created by wmm on 2017/9/7 0007.
 */

public class Loginthree extends Activity implements View.OnClickListener, TextWatcher {
    private EditText et_name;
    private EditText et_pwd;
    private Button btn_login;
    private ImageView img_qq;
    private TextView tv_bottom;
    private SharedPreferences.Editor edit;
    private UserDao dao;
    private Button btn_yan;
    private String mingzi="17316083247";
    private String mima="wangmiaomiao";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.avtivity_loginthree);
        dao = new UserDao(this);
        initView();
    }
    private void initView() {
        et_name = (EditText) findViewById(R.id.et_name);
        et_pwd = (EditText) findViewById(R.id.et_pwd);
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_yan = (Button) findViewById(R.id.btn_yan);
        btn_yan.setOnClickListener(this);
        btn_login.setEnabled(false);
        et_name.addTextChangedListener(this);
        et_pwd.addTextChangedListener(this);
        img_qq = (ImageView) findViewById(R.id.img_qq);
        tv_bottom = (TextView) findViewById(R.id.tv_bottom);
        SharedPreferences sp = getSharedPreferences("file", MODE_PRIVATE);
        edit = sp.edit();
        boolean flag = sp.getBoolean("flag", false);
        if (flag) {
            String name = sp.getString("name", "");
            et_name.setText(name);
            String pwd = sp.getString("pwd", "");
            et_pwd.setText(pwd);
            dao.add(name,pwd);
        }
        img_qq.setOnClickListener(this);
        btn_login.setOnClickListener(this);
    }
    UMAuthListener umAuthListener = new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
            //授权开始的回调
        }
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            Toast.makeText(getApplicationContext(), "Authorize succeed", Toast.LENGTH_SHORT).show();
            String name = data.get("name");
            String gender = data.get("gender");
            String photoUrl = data.get("iconurl");
            tv_bottom.setText(name);
            ImageLoader.getInstance().displayImage(photoUrl, img_qq);
            SharedPreferences sp = getSharedPreferences("user", MODE_PRIVATE);
            SharedPreferences.Editor edit = sp.edit();
            edit.putString("hehe", name);
            edit.putString("iconurl", photoUrl);
            edit.commit();
            finish();
        }
        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            if (UMShareAPI.get(Loginthree.this).isInstall(Loginthree.this, SHARE_MEDIA.QQ)) {
                Toast.makeText(getApplicationContext(), "失败：", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "没有安装qq", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(getApplicationContext(), "取消了", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_qq:
                UMShareAPI.get(this).getPlatformInfo(Loginthree.this, SHARE_MEDIA.QQ, umAuthListener);
                break;
            case R.id.btn_login:
                initData();
                break;
            case R.id.btn_yan:
                showPwd();
                break;
        }
    }
    private void showPwd() {
        int type = InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD;
        if (et_pwd.getInputType() == type) {
            et_pwd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            et_pwd.setSelection(et_pwd.getText().length());        //把光标设置到当前文本末尾
        } else {
            et_pwd.setInputType(type);
            et_pwd.setSelection(et_pwd.getText().length());
        }
    }
    private void initData() {
        String name = et_name.getText().toString().trim();
        String pwd = et_pwd.getText().toString().trim();
        if (!name.equals(mingzi)||!pwd.equals(mima)) {
            Toast.makeText(Loginthree.this, "用户账户或密码不正确请重新输入", Toast.LENGTH_SHORT).show();
            return;
        } else {
            edit.putString("name", name);
            edit.putString("pwd", pwd);
            edit.putBoolean("flag", true);
            edit.commit();
        }
        finish();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        btn_login.setEnabled(false);
    }
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }
    @Override
    public void afterTextChanged(Editable s) {
        if (!(et_name.getText().toString().equals("") || et_pwd.getText().toString().equals(""))) {
            btn_login.setEnabled(true);
        }
    }
}
