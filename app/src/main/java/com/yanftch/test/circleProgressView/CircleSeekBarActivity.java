package com.yanftch.test.circleProgressView;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.yanftch.test.R;
import com.yanftch.test.audio.AudioRecordButton;
import com.yanftch.test.audio.FileUtils;

public class CircleSeekBarActivity extends AppCompatActivity {
    private static final String TAG = "dah_CircleSeekBarActivity";
    private Button startServiceBtn;
    private Button stopServiceBtn;
    private MyService mMyService;
    private Context mContext;
    private ServiceConnection mServiceConnection;
    private Button recode;
    private AudioRecordButton audioButton;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int what = msg.what;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_seek_bar);
        mContext = this;
        mServiceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.e(TAG, "onServiceConnected: 连接上了。。。");
                String s = ((MyService.MyBinder) service).http_load();
                Log.e(TAG, "onServiceConnected: s====" + s);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };
        startServiceBtn = (Button) findViewById(R.id.startService);
        stopServiceBtn = (Button) findViewById(R.id.stopService);
        startServiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MyService.class);
                startService(intent);
                bindService(intent, mServiceConnection, BIND_AUTO_CREATE);
            }
        });
        stopServiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MyService.class);
                stopService(intent);
            }
        });
        recodeButton();

    }

    private void recodeButton() {
        audioButton = (AudioRecordButton) findViewById(R.id.audioButton);
        audioButton.setOnAudioFinshListener(new AudioRecordButton.onAudioFinishRecoredListener() {
            @Override
            public void onRecoredFinish(float seconds, String path) {
                Log.e(TAG, "onRecoredFinish: seconds=" + seconds + "   path=" + path);
                convert(path);
            }
        });
    }

    private void convert(String filePath) {
        byte[] bytes = FileUtils.readStream(filePath);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Uri audioPath = data.getData();
            Toast.makeText(this, audioPath.toString(), Toast.LENGTH_LONG).show();
        }
    }
}
