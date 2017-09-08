package www.bawei.com.wangmiaomiao0908;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView img_v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img_v = (ImageView) findViewById(R.id.img_v);
        img_v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                useObjectAnimatorSet();

            }

            private void useObjectAnimatorSet() {
                PropertyValuesHolder alpha = PropertyValuesHolder.ofFloat("alpha", 0f, 1f,0f);
                PropertyValuesHolder translationX = PropertyValuesHolder.ofFloat("translationX", 0f, 200f,-100f,0f);
                PropertyValuesHolder sc=PropertyValuesHolder.ofFloat("scaleX",1,2,1);
                PropertyValuesHolder ro=PropertyValuesHolder.ofFloat("rotation",1,360);
                ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(img_v, alpha, translationX,sc,ro);
                objectAnimator.setDuration(3000);
                objectAnimator.setRepeatCount(-1);
                objectAnimator.start();
            }
        });



    }
}
