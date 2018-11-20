package com.yanftch.test.nohttp;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.tencent.rtmp.TXLiveBase;
import com.yanftch.test.R;
import com.yanftch.test.tencent_player.PlayerActivity;
import com.yanzhenjie.nohttp.Headers;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.download.DownloadListener;
import com.yanzhenjie.nohttp.download.DownloadQueue;
import com.yanzhenjie.nohttp.download.DownloadRequest;

import java.io.File;

public class NohttpActivity extends AppCompatActivity {
    private DownloadBean mDownloadBean;
    private static final String TAG = "dah_NohttpActivity";
    //        private String apkUrl = "http://7xle4d.com1.z0.glb.clouddn.com/029%E6%A0%88%E5%92%8C%E9%98%9F%E5%88%977.mp4";
//    private String apkUrl = "http://7xle4d.com1.z0.glb.clouddn.com/WeChatSight121.mp4";
    private String apkUrl = "http://gdown.baidu.com/data/wisegame/4f45d1baacb6ee7f/baidushoujizhushouyuan91zhu_16789458.apk";

    private DownloadQueue downLoadQueue;
    private ProgressBar mProgressBar, mProgressBar2, mProgressBar3, mProgressBar4;
    private DownloadRequest request;

    private TextView tv_total, tv_speed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nohttp);
        /**
         * 验证版本号
         */
        String sdkVersionStr = TXLiveBase.getSDKVersionStr();
        Log.e(TAG, "onCreate: sdkVersionStr == " + sdkVersionStr);
        /**----------*/
        mDownloadBean = new DownloadBean();
        mDownloadBean.videoFlag = "apk";
        mDownloadBean.videoUrl = apkUrl;
        mDownloadBean.videoName = "安装包";
        /**----------*/
        mProgressBar = (ProgressBar) findViewById(R.id.progress);
        mProgressBar2 = (ProgressBar) findViewById(R.id.progress2);
        mProgressBar3 = (ProgressBar) findViewById(R.id.progress3);
        mProgressBar4 = (ProgressBar) findViewById(R.id.progress4);
        tv_total = (TextView) findViewById(R.id.tv_total);
        tv_speed = (TextView) findViewById(R.id.tv_speed);

    }

    public void btnInter3(View view) {
      startActivity(new Intent(this, PlayerActivity.class));
    }




    public void btnInter(View view) {
        Toast.makeText(this, "开始", Toast.LENGTH_SHORT).show();
        downLoadApk();
    }

    public void btnInter2(View view) {
        Toast.makeText(this, "取消", Toast.LENGTH_SHORT).show();
        if (request != null) {
            request.cancel();
        }
    }

    private void downLoadApk() {
        request = NoHttp.createDownloadRequest(apkUrl, getFilePath(), getApkName(), true, true);
        getDownloadQueueInstance().add(0, request, mDownloadListener);
    }

    /**
     * 下载监听事件
     */
    private DownloadListener mDownloadListener = new DownloadListener() {
        @Override
        public void onDownloadError(int what, Exception exception) {

        }

        @Override
        public void onStart(int what, boolean isResume, long rangeSize, Headers responseHeaders, long allCount) {
            Log.e(TAG, "onStart: " + "allCount = " + allCount);
            double file = (double) allCount / 1024 / 1024;
            String fileString = String.format("%.2f", file);
            tv_total.setText("总大小：" + fileString + " M");
        }

        @Override
        public void onProgress(int what, int progress, long fileCount, long speed) {
            int xKB = (int) speed / 1024; // 单位：xKB/S
            double file = (double) fileCount / 1024 / 1024; // 单位：xM/S
            String fileString = String.format("%.2f", file);
            Log.e(TAG, "onProgress: 已下载文件大小是:" + fileString + "      下载速度 = " + xKB + " KB/S");
            tv_speed.setText("下载速度：" + xKB);
            mProgressBar.setProgress(progress);
            mProgressBar2.setProgress(progress);
            mProgressBar3.setProgress(progress);
            mProgressBar4.setProgress(progress);
        }

        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public void onFinish(int what, String filePath) {
            Toast.makeText(NohttpActivity.this, "下载完毕", Toast.LENGTH_SHORT).show();
//            installApk(filePath);
        }

        @Override
        public void onCancel(int what) {

        }
    };


    private DownloadQueue getDownloadQueueInstance() {
        if (downLoadQueue == null) {
            downLoadQueue = NoHttp.newDownloadQueue(2);
        }
        return downLoadQueue;
    }

    private String getApkName() {
        return mDownloadBean.videoName + System.currentTimeMillis() + "." + mDownloadBean.videoFlag;
    }

    private String getFilePath() {

        String path;
        if (sdCardIsAvailable()) {
            path = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "dahuo_video";
        } else {
            path = Environment.getDataDirectory().getAbsolutePath() + File.separator + "dahuo_video";
        }
        Log.e(TAG, "getFilePath: 路径=" + path);
        return path;
    }

    /**
     * SD卡是否可用.
     */
    private static boolean sdCardIsAvailable() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File sd = new File(Environment.getExternalStorageDirectory().getPath());
            return sd.canWrite();
        } else
            return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        downLoadQueue.cancelAll();
    }

    /**
     * 安装apk包
     */
    private void installApk(String filePath) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        //解决安装完成后有时会直接退出，不弹出安装成功的界面，
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(Uri.fromFile(new File(filePath)), "application/vnd.android.package-archive");
        NohttpActivity.this.startActivity(intent);
    }
}
