package com.yanftch.test.mvp_new.mvp1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.yanftch.test.R;

public class Mvp1Activity extends AppCompatActivity implements Mvp1Contract.View {
    private static final String TAG = "dah_Mvp1Activity";
    private Mvp1Contract.Presenter mPresenter;
    private Button mButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp1);
        mPresenter = new Mvp1Presenter(Mvp1Model.getInstanct(), this);
        mButton = (Button) findViewById(R.id.btn1);
        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.handleData();
            }
        });
    }

    @Override
    public void showData(String data) {
        Log.e(TAG, "showData: " + data);
        mButton.setText(data);
    }
}
