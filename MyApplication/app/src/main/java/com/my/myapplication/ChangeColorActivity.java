package com.my.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

/**
 * Created by 10937959 on 2016/8/11.
 */
/* 本文件主要展示怎么改变部分字体的颜色 */
public class ChangeColorActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_color_layout);

        //改变字体颜色
        TextView textView1 = (TextView) findViewById(R.id.text1);
//        textView1.setText(Html.fromHtml(getResources().getString(R.string.test_string1)));
        textView1.setText(R.string.action_settings);

        //改变字体颜色，并且实现换行
        TextView textView2 = (TextView) findViewById(R.id.text2);
        String testString = getResources().getString(R.string.test_string2);
        testString = testString.replace("\n", "<br/>");
        textView2.setText(Html.fromHtml(testString));
    }
}
