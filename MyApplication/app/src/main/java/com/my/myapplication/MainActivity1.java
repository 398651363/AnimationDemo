package com.my.myapplication;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
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

public class MainActivity1 extends Activity {

    private ImageView mImage;
    private int mCurrentAngle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* 自定义转圈扫描动画 */
        /*mImage = (ImageView) findViewById(R.id.image);
        startAnimation();*/

        /* Frame Animation */
        /*mImage.setBackgroundResource(R.anim.frame);
        AnimationDrawable anim = (AnimationDrawable) mImage.getBackground();
        anim.start();*/

        /* Tween Animation */
        /*Animation animation = AnimationUtils.loadAnimation(this, R.anim.scale);
        animation.setFillAfter(true);
        mImage.startAnimation(animation);*/
    }

    public void rotateAnimator(final View view) {
        /* 采用propertyValueHolder的方式，从显示到隐藏，同时缩小，再到显示，同时放大 */
        PropertyValuesHolder alphaHolder = PropertyValuesHolder.ofFloat("alpha", 1.0f, 0.0f, 1.0f);
        PropertyValuesHolder scaleXHolder = PropertyValuesHolder.ofFloat("scaleX", 1.0f, 0.0f, 1.0f);
        PropertyValuesHolder scaleYHolder = PropertyValuesHolder.ofFloat("scaleY", 1.0f, 0.0f, 1.0f);
        ObjectAnimator.ofPropertyValuesHolder(view, alphaHolder, scaleXHolder, scaleYHolder)
                .setDuration(2000)
                .start();

        /* 采用AnimatorSet的实现方式，从显示到隐藏，同时缩小*/
        /*ObjectAnimator alphaAnim = ObjectAnimator.ofFloat(view, "alpha", 1.0f, 0.0f);
        ObjectAnimator xScaleAnim = ObjectAnimator.ofFloat(view, "scaleX", 1.0f, 0.0f);
        ObjectAnimator yScaleAnim = ObjectAnimator.ofFloat(view, "scaleY", 1.0f, 0.0f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(2000);
        animatorSet.playTogether(alphaAnim, xScaleAnim, yScaleAnim);
        animatorSet.start();*/

        /* 采用更新绘图的方式，从显示到隐藏，同时缩小*/
        /*final ObjectAnimator animator = ObjectAnimator.ofFloat(view, "sets", 1.0F, 0.0F)
                .setDuration(2000);
        animator.start();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float curVal = (float) animation.getAnimatedValue();
                view.setAlpha(curVal);
                view.setScaleX(curVal);
                view.setScaleY(curVal);
            }
        });*/
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

    public void stopFrame(View v){
        AnimationDrawable anim = (AnimationDrawable) mImage.getBackground();
        if (anim.isRunning())
            anim.stop();
    }

    public void startFrame(View v){
        /*mImage.setBackgroundResource(R.anim.frame);
        AnimationDrawable anim = (AnimationDrawable) mImage.getBackground();
        anim.start();*/
        AnimationDrawable anim = new AnimationDrawable();
        for (int i = 0; i < 9; i++) {
            int id = getResources().getIdentifier("vivo_progress_0" + i, "mipmap", getPackageName());
            Drawable drawable = getResources().getDrawable(id, null);
            anim.addFrame(drawable, 30);
        }
        for (int i = 10; i < 44; i++) {
            int id = getResources().getIdentifier("vivo_progress_" + i, "mipmap", getPackageName());
            Drawable drawable = getResources().getDrawable(id, null);
            anim.addFrame(drawable, 30);
        }
        anim.setOneShot(false);
        mImage.setBackground(anim);
        anim.start();
    }

    private void startAnimation() {
        mImage.setImageResource(R.mipmap.virus_scan_img);
        /*ViewGroup.LayoutParams layoutParams = mImage.getLayoutParams();
        layoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        mImage.setLayoutParams(layoutParams);*/
        Animation anim = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setInterpolator(new LinearInterpolator());
        anim.setRepeatCount(Animation.INFINITE);
        anim.setDuration(2000);
        mImage.startAnimation(anim);
    }
}
