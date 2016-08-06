package com.my.myapplication;

import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.graphics.PointF;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

/**
 * Created by 10937959 on 2016/8/6.
 */

/* 本文件是ValueAnimator的例子*/
public class MainActivity extends Activity{

    private ImageView mImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.value_animator);
        mImage = (ImageView) findViewById(R.id.image);
    }

    /* 实现自由落体的效果 */
    public void vertivalRun(View view){
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 1000);
        valueAnimator.setTarget(mImage);
        valueAnimator.setDuration(2000);
        valueAnimator.start();
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mImage.setTranslationY((float)animation.getAnimatedValue());
            }
        });
    }

    /* 实现抛物线的效果，水平方向100px/s，垂直方向加速度200px/s*s  */
    public void parabolaRun(View view){
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setDuration(3000);
        valueAnimator.setTarget(mImage);
        valueAnimator.setObjectValues(new PointF(0, 0));
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setEvaluator(new TypeEvaluator<PointF>() {
            @Override
            public PointF evaluate(float fraction, PointF startValue, PointF endValue) {
                Log.i("TAG", "fraction is : " + fraction);
                PointF pointF = new PointF();
                fraction *= 3;
                pointF.x = 200 * fraction;
                pointF.y = 0.5f * 200 * fraction * fraction;
                return pointF;
            }
        });
        valueAnimator.start();
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                PointF pointF = (PointF) animation.getAnimatedValue();
                mImage.setTranslationX(pointF.x);
                mImage.setTranslationY(pointF.y);
            }
        });
    }
}
