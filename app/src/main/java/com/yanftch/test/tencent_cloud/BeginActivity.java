package com.yanftch.test.tencent_cloud;

import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.yanftch.test.R;
import com.yanftch.test.nohttp.DownloadBean;
import com.yanzhenjie.nohttp.Headers;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.download.DownloadListener;
import com.yanzhenjie.nohttp.download.DownloadQueue;
import com.yanzhenjie.nohttp.download.DownloadRequest;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class BeginActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "dah_BeginActivity";
    private String apkUrl = "http://gdown.baidu.com/data/wisegame/4f45d1baacb6ee7f/baidushoujizhushouyuan91zhu_16789458.apk";

    private DownloadQueue downLoadQueue;
    private DownloadRequest request;
    private DownloadBean mDownloadBean;
    private ProgressView mProgressView;
    private long totalSize;
    private int lines = 0;
    private RecyclerView recycler_view;
    private List<DownloadListBean> datas = new ArrayList<>();
    private MyAdapter mMyAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_begin);
        findViewById(R.id.btn_download).setOnClickListener(this);
        recycler_view = (RecyclerView) findViewById(R.id.recycler_view);

        mDownloadBean = new DownloadBean();
        mDownloadBean.videoFlag = "apk";
        mDownloadBean.videoUrl = apkUrl;
        mDownloadBean.videoName = "安装包";
        DownloadListBean bean = new DownloadListBean();
        bean.progressl = 20;
        bean.speed = "20/s";
        bean.total = "900";
        datas.add(bean);
        recycler_view.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mMyAdapter = new MyAdapter(datas, this);
        recycler_view.setAdapter(mMyAdapter);

    }

    private void downLoadApk(int i) {
        request = NoHttp.createDownloadRequest(apkUrl, getFilePath(), getApkName(), true, true);
        getDownloadQueueInstance().add(i, request, mDownloadListener);
    }

    /**
     * 下载监听事件
     */
    private DownloadListener mDownloadListener = new DownloadListener() {
        public DownloadListBean bean;

        @Override
        public void onDownloadError(int what, Exception exception) {
        }

        @Override
        public void onStart(int what, boolean isResume, long rangeSize, Headers responseHeaders, long allCount) {
            Log.e(TAG, "onStart: " + "allCount = " + allCount);
            mProgressView = new ProgressView(BeginActivity.this);
            totalSize = allCount;
            lines++;
            double file = (double) allCount / 1024 / 1024;
            String fileString = String.format("%.2f", file);
            bean = new DownloadListBean();
            bean.what = what;
            datas.add(bean);
            mMyAdapter.notifyDataSetChanged();
        }

        @Override
        public void onProgress(int what, int progress, long fileCount, long speed) {
            int xKB = (int) speed / 1024; // 单位：xKB/S
            double file = (double) fileCount / 1024 / 1024; // 单位：xM/S
            String fileString = String.format("%.2f", file);//已经下载的数量
            mProgressView.setData(progress, "速度：" + xKB, totalSize + "KB");
            Log.e(TAG, "onProgress: "+bean.what);
//            if (bean.what == what) {
//                bean.progressl = progress;
//                bean.speed = xKB + "速度";
//                bean.total = totalSize + "KB";
//            }
//            mMyAdapter.notifyDataSetChanged();
        }

        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public void onFinish(int what, String filePath) {
            Toast.makeText(BeginActivity.this, "下载完毕", Toast.LENGTH_SHORT).show();
//            installApk(filePath);
        }

        @Override
        public void onCancel(int what) {
        }
    };


    @Override
    public void onClick(View v) {
        Log.e(TAG, "onClick: lines == " + lines);
        switch (v.getId()) {
            case R.id.btn_download://下载
                downLoadApk(lines);
                break;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != downLoadQueue) {
            downLoadQueue.cancelAll();
            downLoadQueue.stop();
        }
    }


    private DownloadQueue getDownloadQueueInstance() {
        if (downLoadQueue == null) {
            downLoadQueue = NoHttp.newDownloadQueue(100);
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
//        Log.e(TAG, "getFilePath: 路径=" + path);
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

}
