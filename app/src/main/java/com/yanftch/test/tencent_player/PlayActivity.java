package com.yanftch.test.tencent_player;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.tencent.rtmp.TXLivePlayer;
import com.tencent.rtmp.ui.TXCloudVideoView;
import com.yanftch.test.R;

import java.io.File;

public class PlayActivity extends AppCompatActivity {
    private static final String TAG = "dah_PlayActivity";
    private String remoteMp4Url = "https://s3.cn-north-1.amazonaws.com.cn/uploadfiles/plive/vodie/%E5%86%A0%E5%86%9B%E8%AF%B4%E7%89%87%E5%A4%B4%E6%88%90%E7%89%87.mp4";
    private String localUrl = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "dahuo_video" + "/我是视屏的名字15036399879551.mp4";
    //    private String localUrl = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "dahuo_video" + "/bootaudio.mp3";
    private TXLivePlayer mLivePlayer;
    private TXCloudVideoView mPlayerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        Log.e(TAG, "onCreate: ");
        player();
        findViewById(R.id.ttttttttttttttttt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLivePlayer.startPlay(remoteMp4Url, TXLivePlayer.PLAY_TYPE_VOD_MP4); //视频
            }
        });
    }

    private void player() {
        //mPlayerView即step1中添加的界面view
        mPlayerView = (TXCloudVideoView) findViewById(R.id.video_view);
        //创建player对象
        mLivePlayer = new TXLivePlayer(this);
        //关键player对象与界面view
        mLivePlayer.setPlayerView(mPlayerView);
    }
}
