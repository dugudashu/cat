package com.heihei.com.propertyanimation;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationSet;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private View img_view;
    private LinearLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img_view = findViewById(R.id.img_view);
        container = (LinearLayout) findViewById(R.id.container);

    }

    @Override
    public void onClick(View v) {

        //属性动画  ValueAnimator 的用法  ,属性动画本质是值动画
//        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0f, 1.0f);
//        valueAnimator.setDuration(2000);
//        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//
//                float animatedValue = (float) animation.getAnimatedValue();
//                ViewGroup.LayoutParams layoutParams = img_view.getLayoutParams();
//                layoutParams.width = (int) (animatedValue * 100);
//                layoutParams.height = (int) (animatedValue * 100);
//                img_view.setLayoutParams(layoutParams);
//
//
//            }
//        });
//        valueAnimator.start();


        //ObjectAnimation的用法

//        ObjectAnimator alphaobjectAnimator=ObjectAnimator.ofFloat(img_view,"alpha",0f,1f,0.5f);
//        alphaobjectAnimator.setDuration(3000).start();
//        ObjectAnimator scaleXobjectAnimation=ObjectAnimator.ofFloat(img_view,"scaleX",0f,1f,0.5f);
//        scaleXobjectAnimation.setDuration(3000).start();
//        ObjectAnimator translationAnimation=ObjectAnimator.ofFloat(img_view,"translationX",0f,1000f);
//        translationAnimation.setDuration(3000).start();


//        ObjectAnimator rotationObjectAnimation=ObjectAnimator.ofFloat(img_view,"rotation",0f,-360f);
//        rotationObjectAnimation .setDuration(3000);
//        rotationObjectAnimation.setRepeatCount(3);
//        rotationObjectAnimation.setRepeatMode(ValueAnimator.REVERSE);
//        rotationObjectAnimation.addListener(new Animator.AnimatorListener() {
//            @Override
//            public void onAnimationStart(Animator animation) {
//
//
//
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animator animation) {
//
//            }
//
//            @Override
//            public void onAnimationCancel(Animator animation) {
//
//            }
//
//            @Override
//            public void onAnimationRepeat(Animator animation) {
//
//            }
//        });
//
//        rotationObjectAnimation.start();


        //ViewPropertyAnimator的用法
//        img_view.animate()
//                .alpha(0.5f)
//                .translationX(100f)
//                .rotation(180)
//                .scaleX(2)
//                .scaleY(2)
//                .setStartDelay(2000)
//                .setDuration(3000)
//                ;


//       AnimatorSet  组合动画

//        AnimatorSet animatorSet=new AnimatorSet();
//        ObjectAnimator alphaObject=ObjectAnimator.ofFloat(img_view,"alpha",1.0f,0f,0.5f);
//        ObjectAnimator scaleXObject=ObjectAnimator.ofFloat(img_view,"scaleX",0f,1f,0.5f);
//        animatorSet.play(alphaObject).with(scaleXObject);
//        animatorSet.playTogether(alphaObject,scaleXObject);
//        animatorSet.setDuration(3000);
//        animatorSet.start();


        //用valueAnimator实现组合动画

//        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0f, 1f);
//        valueAnimator.setDuration(3000);
//        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//
//                float animationValue = (float) animation.getAnimatedValue();
//                ViewGroup.LayoutParams layoutParams = img_view.getLayoutParams();
//                layoutParams.width = (int) (animationValue * 300);
//                layoutParams.height = (int) (animationValue * 300);
//                img_view.setLayoutParams(layoutParams);
//                img_view.setAlpha(animationValue);
//
//
//            }
//        });
//        valueAnimator.start();


        //用AnimatorInflater去加载ValueAnimator对应的xml文件
//        ValueAnimator valueAnimator = (ValueAnimator) AnimatorInflater.loadAnimator(MainActivity.this, R.animator.value_animator);
//        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//
//                float animatedValue = (float) animation.getAnimatedValue();
//                img_view.setAlpha(animatedValue);
//
//
//            }
//        });
//
//        valueAnimator.setDuration(3000);
//        valueAnimator.start();

        //用AnimatorInflater去加载ObjectAnimator对应的xml文件

//        ObjectAnimator objectAnimatior=(ObjectAnimator)AnimatorInflater.loadAnimator(this,R.animator.object_animator);
//        objectAnimatior.setDuration(3000);
//        objectAnimatior.setTarget(img_view);
//        objectAnimatior.start();


        //用AnimatorInflater去加载AnimatorSet对应的xml文件

//        Animator animator=AnimatorInflater.loadAnimator(this,R.animator.set_animtor);
//        animator.setTarget(img_view);
//        animator.start();


//        利用ValueAnimator进行颜色变化动画
        ValueAnimator valueAnimator;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            valueAnimator = ValueAnimator.ofArgb(0xffff0000, 0xff0000ff, 0xff00ff00);
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    int colorValue = (int) animation.getAnimatedValue();
                    container.setBackgroundColor(colorValue);
                }
            });
            valueAnimator.setDuration(2000);
            valueAnimator.start();
        }






    }
}
