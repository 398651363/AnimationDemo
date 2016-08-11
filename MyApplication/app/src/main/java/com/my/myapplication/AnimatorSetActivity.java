package com.my.myapplication;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

/**
 * Created by 10937959 on 2016/8/10.
 */
/*本文件练习使用animatorset*/
public class AnimatorSetActivity extends Activity {

    private ImageView mImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animatorset_layout);
        mImage = (ImageView) findViewById(R.id.image);
    }

    public void togetherRun(View view) {
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(mImage, "scaleX", 1.0f, 2.0f);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(mImage, "scaleY", 1.0f, 2.0f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(2000);
        animatorSet.setInterpolator(new LinearInterpolator());
        animatorSet.playTogether(animator1, animator2);
        animatorSet.start();
    }

    public void playWithAfter(View view) {
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(mImage, "scaleX", 1.0f, 2.0f);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(mImage, "scaleY", 1.0f, 2.0f);
        float currentX = mImage.getX();
        ObjectAnimator animator3 = ObjectAnimator.ofFloat(mImage, "x", currentX, mImage.getWidth() / 2);
        ObjectAnimator animator4 = ObjectAnimator.ofFloat(mImage, "x", getResources().getDisplayMetrics().widthPixels - 2 * mImage.getWidth());
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(2000);
        animatorSet.setInterpolator(new LinearInterpolator());
        animatorSet.play(animator1).with(animator2);
        animatorSet.play(animator2).with(animator3);
        animatorSet.play(animator4).after(animator3);
        animatorSet.start();
    }
}
