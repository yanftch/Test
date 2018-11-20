package com.yanftch.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class Main2Activity extends AppCompatActivity {
    private static final String TAG = "dah_Main2Activity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Log.e(TAG, "onCreate: " );
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume: " );
    }

    public void btnBack(View view) {
        Intent intent  = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy: " );
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "onPause: " );
    }
}
