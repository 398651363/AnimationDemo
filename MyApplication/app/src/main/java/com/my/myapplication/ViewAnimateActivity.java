package com.my.myapplication;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by 10937959 on 2016/8/11.
 */
public class ViewAnimateActivity extends Activity {
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_animatoractivity_layout);
        mImageView = (ImageView) findViewById(R.id.image);
    }

    public void viewAnim(View view) {
        mImageView.animate()
                .alpha(0)
                .y(getResources().getDisplayMetrics().heightPixels / 2)
                .setDuration(1000)
                .withStartAction(new Runnable() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mImageView.setY(0);
                                mImageView.setAlpha(1.0f);
                            }
                        });
                    }
                }).start();
    }

    public void propertyValuesHolder(View view) {
        PropertyValuesHolder propertyValuesHolder = PropertyValuesHolder.ofFloat("alpha", 1.0f, 0.0f, 1.0f);
        PropertyValuesHolder propertyValuesHolder1 = PropertyValuesHolder.ofFloat("y", 0, getResources().getDisplayMetrics().heightPixels / 2, 0);
        ObjectAnimator.ofPropertyValuesHolder(mImageView, propertyValuesHolder, propertyValuesHolder1).setDuration(1000).start();
    }
}
