package com.my.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

public class MainActivity extends Activity {

    private ImageView mImage;
    private int mCurrentAngle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scale);

        mImage = (ImageView) findViewById(R.id.image);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.scale);
        animation.setFillAfter(true);
        mImage.startAnimation(animation);
    }

    public void positive(View v) {
        Animation anim = new RotateAnimation(mCurrentAngle, mCurrentAngle + 180, Animation.RELATIVE_TO_SELF,
                0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        /* 匀速插值器 */
        LinearInterpolator linearInterpolator = new LinearInterpolator();
        anim.setInterpolator(linearInterpolator);
        anim.setDuration(1000);
        /* 动画完成之后是否恢复原状 */
        anim.setFillAfter(true);
        mCurrentAngle += 180;
        if (mCurrentAngle > 360)
            mCurrentAngle -= 360;
        mImage.startAnimation(anim);
    }

    public void negative(View v){
        Animation anim = new RotateAnimation(mCurrentAngle, mCurrentAngle - 180, Animation.RELATIVE_TO_SELF,
                0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        /* 匀速插值器 */
        LinearInterpolator linearInterpolator = new LinearInterpolator();
        anim.setInterpolator(linearInterpolator);
        anim.setDuration(1000);
        /* 动画完成之后是否恢复原状 */
        anim.setFillAfter(false);
        mCurrentAngle -= 180;
        if (mCurrentAngle < -360)
            mCurrentAngle += 360;
        mImage.startAnimation(anim);
    }

    public void alpha(View v){
        Animation anim = new AlphaAnimation(1.0f, 0.0f);
        anim.setDuration(3000);
        anim.setFillAfter(true);
        mImage.startAnimation(anim);
    }

    public void translate(View v) {
        Animation anim = new TranslateAnimation(500, 0, 800, 0);
        anim.setDuration(4000);
        anim.setFillAfter(true);
        OvershootInterpolator overshootInterpolator = new OvershootInterpolator();
        anim.setInterpolator(overshootInterpolator);
        mImage.startAnimation(anim);
    }

    public void scale(View v) {
        Animation anim = new ScaleAnimation(2.0f, 0.5f, 2.0f, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setDuration(2000);
        anim.setFillAfter(true);
        BounceInterpolator bounceInterpolator = new BounceInterpolator();
        anim.setInterpolator(bounceInterpolator);
        mImage.startAnimation(anim);
    }
}
