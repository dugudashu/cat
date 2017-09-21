package www.bawei.com.mymvp.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import www.bawei.com.mymvp.R;
import www.bawei.com.mymvp.view.activity.Loginthree;

/**
 * Created by wmm on 2017/9/6 0006.
 */

public class FragmentFive extends Fragment {
    private View view;
    private ImageView img_login;
    private TextView tv_name;
    private String iconuri;
    private SharedPreferences sp;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_fragmentfive, container, false);
        tv_name = (TextView) view.findViewById(R.id.tv_name);
        return view;


    }

    @Override
    public void onResume() {
        super.onResume();
        sp = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        String name = sp.getString("hehe", "登录/注册>");
        iconuri = sp.getString("iconurl", String.valueOf(R.drawable.b3h));
        tv_name.setText(name);
        ImageLoader.getInstance().displayImage(iconuri,img_login);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        img_login = (ImageView) view.findViewById(R.id.img_login);
        img_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), Loginthree.class);
                startActivity(intent);


            }
        });
    }





}
