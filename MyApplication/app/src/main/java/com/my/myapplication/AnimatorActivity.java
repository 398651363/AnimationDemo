package com.my.myapplication;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by 10937959 on 2016/8/11.
 */
/* 本文件使用xml文件实现动画效果 */
public class AnimatorActivity extends Activity {

    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animatoractivity_layout);
        mImageView = (ImageView) findViewById(R.id.image);
    }

    public void scaleX(View view) {
        Animator animator = AnimatorInflater.loadAnimator(this, R.animator.scalex);
        animator.setTarget(mImageView);
        animator.start();
    }

    public void scaleXandScaleY(View view) {
        Animator animator = AnimatorInflater.loadAnimator(this, R.animator.scale);
        //以图片右下角为中心点缩放
        mImageView.setPivotX(mImageView.getWidth());
        mImageView.setPivotY(mImageView.getHeight());
        mImageView.invalidate();  //加与不加没啥区别
        animator.setTarget(mImageView);
        animator.start();
    }
}
