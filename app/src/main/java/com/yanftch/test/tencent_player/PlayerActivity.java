package com.yanftch.test.tencent_player;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tencent.rtmp.TXLiveConstants;
import com.tencent.rtmp.TXLivePlayer;
import com.tencent.rtmp.ui.TXCloudVideoView;
import com.yanftch.test.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class PlayerActivity extends AppCompatActivity {
    private static final String TAG = "dah_PlayerActivity";
    private TextView tv_show;
    private StringBuilder mStringBuilder = new StringBuilder();
    private String remoteMp4Url = "http://7xle4d.com1.z0.glb.clouddn.com/WeChatSight121.mp4";
    private String localUrl = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "dahuo_video" + "/我是视屏的名字1503639987955.mp4";
    //    private String localUrl = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "dahuo_video" + "/bootaudio.mp3";
    private TXLivePlayer mLivePlayer;
    private TXCloudVideoView mPlayerView;
    private LinearLayout ll_channel;
    private Button mBtnPlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_player);
        tv_show = (TextView) findViewById(R.id.tv_show);
        ll_channel = (LinearLayout) findViewById(R.id.ll_channel);
        fbc();
        player();
    }

    private void fbc() {
        mBtnPlay = (Button) findViewById(R.id.btnPlay);
        mBtnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


    /**
     * -------------无用------------
     **/

    public void btnPlayLocal(View view) {
        mLivePlayer.startPlay(remoteMp4Url, TXLivePlayer.PLAY_TYPE_VOD_MP4); //本地视频
    }

    private void player() {
        //mPlayerView即step1中添加的界面view
        mPlayerView = (TXCloudVideoView) findViewById(R.id.video_view);
        //创建player对象
        mLivePlayer = new TXLivePlayer(this);
        //关键player对象与界面view
        mLivePlayer.setPlayerView(mPlayerView);
    }

    public void btnGetData(View view) {
        int size = getFilePathFromSD().size();
        for (int i = 0; i < size; i++) {
            mStringBuilder.append(getFilePathFromSD().get(i) + "\r\n" + "\r\n");
            Log.e(TAG, "btnGetData: " + getFilePathFromSD().get(i));
        }
        tv_show.setText(mStringBuilder.toString());
    }

    private List<String> getFilePathFromSD() {
        // 图片列表
        List<String> imagePathList = new ArrayList<String>();
        // 得到sd卡内image文件夹的路径   File.separator(/)
        String filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "dahuo_video";
        // 得到该路径文件夹下所有的文件
        File fileAll = new File(filePath);
        File[] files = fileAll.listFiles();
        // 将所有的文件存入ArrayList中,并过滤所有图片格式的文件
        for (int i = 0; i < files.length; i++) {
            File file = files[i];
            if (checkIsAPKFile(file.getPath())) {
                imagePathList.add(file.getPath());
            }
        }
        // 返回得到的图片列表
        return imagePathList;
    }

    private boolean checkIsAPKFile(String fName) {
        boolean isImageFile = false;
        // 获取扩展名
        String FileEnd = fName.substring(fName.lastIndexOf(".") + 1,
                fName.length()).toLowerCase();
        if (FileEnd.equals("apk") || FileEnd.equals(".apk") || FileEnd.equals(".mp4")
                || FileEnd.equals("jpeg") || FileEnd.equals("mp4")) {
            isImageFile = true;
        } else {
            isImageFile = false;
        }
        return isImageFile;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mStringBuilder = null;
        mLivePlayer.stopPlay(true); // true代表清除最后一帧画面
        mPlayerView.onDestroy();
    }

    public void changeOri(View view) {
        ll_channel.setVisibility(View.GONE);
        mLivePlayer.setRenderRotation(TXLiveConstants.RENDER_ROTATION_LANDSCAPE);
    }

}
