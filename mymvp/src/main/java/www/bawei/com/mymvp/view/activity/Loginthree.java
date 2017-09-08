package www.bawei.com.mymvp.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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


/**
 * Created by wmm on 2017/9/7 0007.
 */

public class Loginthree  extends Activity implements View.OnClickListener {


    private EditText et_name;
    private EditText et_pwd;
    private Button btn_login;
    private ImageView img_qq;

    private TextView tv_bottom;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.avtivity_loginthree);
        et_name = (EditText) findViewById(R.id.et_name);
        et_pwd = (EditText) findViewById(R.id.et_pwd);
        btn_login = (Button) findViewById(R.id.btn_login);
        img_qq = (ImageView) findViewById(R.id.img_qq);
        tv_bottom = (TextView) findViewById(R.id.tv_bottom);
        img_qq.setOnClickListener(this);


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


            ImageLoader.getInstance().displayImage(photoUrl,img_qq);
            SharedPreferences sp=getSharedPreferences("user",MODE_PRIVATE);
            SharedPreferences.Editor edit = sp.edit();
            edit.putString("hehe",name);
            edit.putString("iconurl",photoUrl);
            edit.commit();
            finish();

        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            if( UMShareAPI.get(Loginthree.this).isInstall(Loginthree.this,SHARE_MEDIA.QQ)){
                Toast.makeText(getApplicationContext(), "失败：", Toast.LENGTH_SHORT).show();
            }else{
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
        switch (v.getId()){

                case R.id.img_qq:
                UMShareAPI.get(this).getPlatformInfo(Loginthree.this, SHARE_MEDIA.QQ, umAuthListener);



            break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);




    }


}
