package com.yanftch.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yanftch.test.event_bus.EventBus1Activity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "dah_MainActivity";
    private LinearLayout ll_container;
    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv = (ImageView) findViewById(R.id.iv);
        Picasso.with(this).load("http://s3.cn-north-1.amazonaws.com.cn/uploadfiles/eriskfile/ERiskPic/h_baobei.png").into(iv);
        Log.e(TAG, "onCreate: " );
        ll_container = (LinearLayout) findViewById(R.id.ll_container);
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            list.add("str"+i);
        }
        for (int i = 0; i < list.size(); i++) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT,1);

            TextView textView = new TextView(this);
            textView.setLayoutParams(params);
            textView.setText(list.get(i));
            textView.setGravity(Gravity.CENTER);
            ll_container.addView(textView);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume: " );
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "onStart: " );
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.e(TAG, "onNewIntent: " );
    }

    /**
     * 我的收益
     */
    public void btnClick2MyProfit(View view) {
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }

    /**
     * 练习EventBus
     *
     * @param view
     */
    public void btnClick2EventBus(View view) {
        Intent intent = new Intent(this, EventBus1Activity.class);
        startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "onPause: " );
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy: " );
    }
}
