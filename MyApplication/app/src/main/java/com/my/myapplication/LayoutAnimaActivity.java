package com.my.myapplication;

import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.GridLayout;

/**
 * Created by 10937959 on 2016/8/11.
 */
public class LayoutAnimaActivity extends Activity implements OnCheckedChangeListener {
    private ViewGroup mViewGroup;
    private CheckBox mAppear, mChangeAppear, mDisappear, mChangeDisappear;
    private GridLayout mGridLayout;
    private int mVal;
    private LayoutTransition mTransition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layoutanimatoractivity_layout);
        mViewGroup = (ViewGroup) findViewById(R.id.id_container);

        mAppear = (CheckBox) findViewById(R.id.id_appear);
        mChangeAppear = (CheckBox) findViewById(R.id.id_change_appear);
        mDisappear = (CheckBox) findViewById(R.id.id_disappear);
        mChangeDisappear = (CheckBox) findViewById(R.id.id_change_disappear);

        mAppear.setOnCheckedChangeListener(this);
        mChangeAppear.setOnCheckedChangeListener(this);
        mDisappear.setOnCheckedChangeListener(this);
        mChangeDisappear.setOnCheckedChangeListener(this);

        /* 创建一个GridLayout */
        mGridLayout = new GridLayout(this);
        /* 设置每列4个按钮 */
        mGridLayout.setColumnCount(4);
        /* 添加到布局中 */
        mViewGroup.addView(mGridLayout);
        /* 默认动画全部开启 */
        if (mTransition == null) {
            mTransition = new LayoutTransition();
        }
        mGridLayout.setLayoutTransition(mTransition);
    }

    public void addBtn(View view) {
        final Button button = new Button(this);
        button.setText(++mVal + "");
        mGridLayout.addView(button, Math.min(1, mGridLayout.getChildCount()));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGridLayout.removeView(button);
            }
        });
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (mTransition == null) {
            mTransition = new LayoutTransition();
        }
        mTransition.setAnimator(LayoutTransition.APPEARING,
                (mAppear.isChecked() ? ObjectAnimator.ofFloat(this, "scaleX", 0, 1): null));
        mTransition.setAnimator(LayoutTransition.CHANGE_APPEARING,
                (mChangeAppear.isChecked() ? mTransition.getAnimator(LayoutTransition.CHANGE_APPEARING) : null));
        mTransition.setAnimator(LayoutTransition.DISAPPEARING,
                (mDisappear.isChecked() ? mTransition.getAnimator(LayoutTransition.DISAPPEARING) : null));
        mTransition.setAnimator(LayoutTransition.CHANGE_DISAPPEARING,
                (mChangeDisappear.isChecked() ? mTransition.getAnimator(LayoutTransition.CHANGE_DISAPPEARING) : null));
        mGridLayout.setLayoutTransition(mTransition);
    }
}
