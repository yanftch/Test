package com.yanftch.test.mvp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.yanftch.test.R;
import com.yanftch.test.mvp.simple_mvp.RequestPresenter1;
import com.yanftch.test.mvp.simple_mvp.RequestView1;
import com.yanftch.test.mvp_new.mvp1.Mvp1Activity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MVPActivity extends AppCompatActivity implements RequestView1 {
    private static final String TAG = "dah_MVPActivity";
    private TextView mTextView;
    private RequestPresenter1 presenter;
    private String key="23f278735f7a6";
    private String id="00100010070000000001";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp);
        mTextView = (TextView) findViewById(R.id.textView);
        presenter = new RequestPresenter1(this);
        findViewById(R.id.mvp1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MVPActivity.this.startActivity(new Intent(MVPActivity.this, Mvp1Activity.class));
            }
        });
        commonCLick();
        simpleMVPClick();
    }

    private void simpleMVPClick() {
        findViewById(R.id.btnSimpleMVP).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            presenter.P_getData(key,id);
            }
        });
    }

    private void commonCLick() {
//        http://apicloud.mob.com/v1/cook/menu/query?key=23f278735f7a6&id=00100010070000000001
        findViewById(R.id.btnCommon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(Api.base_url)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                ApiService apiService = retrofit.create(ApiService.class);
                Call<Bean> beanCall = apiService.getBeanData("23f278735f7a6", "00100010070000000001");
                beanCall.enqueue(new Callback<Bean>() {
                    @Override
                    public void onResponse(Call<Bean> call, Response<Bean> response) {
                        Log.e(TAG, "onResponse: " + response.body().getResult().getName());
                        mTextView.setText(response.body().getResult().getName() + "---" + response.body().getResult().getCtgTitles());
                    }

                    @Override
                    public void onFailure(Call<Bean> call, Throwable t) {

                    }
                });
            }
        });
    }

    @Override
    public void requestLoading() {
            mTextView.setText("loaidng.....");
    }

    @Override
    public void resultSuccess(Bean result) {
        mTextView.setText(result.getResult().getName()+"00simple");
    }

    @Override
    public void resultFailure(String result) {

    }
}
