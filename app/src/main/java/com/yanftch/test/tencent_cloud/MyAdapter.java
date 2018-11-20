package com.yanftch.test.tencent_cloud;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.yanftch.test.R;

import java.util.List;

/**
 * User : yanftch
 * Date : 2017/8/28
 * Time : 13:53
 * Desc :
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private static final String TAG = "dah_MyAdapter";
    private List<DownloadListBean> datas;
    private Context mContext;

    public MyAdapter(List<DownloadListBean> datas, Context context) {
        this.datas = datas;
        mContext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                mContext).inflate(R.layout.layout_progress_view, viewGroup,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int i) {
        DownloadListBean downloadBean = datas.get(i);
        holder.tv_speed.setText(downloadBean.speed);
        holder.tv_total.setText(downloadBean.total);
        holder.progress_view.setProgress((int) downloadBean.progressl);

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private ProgressBar progress_view;
        private TextView tv_speed, tv_total;

        public MyViewHolder(View itemView) {
            super(itemView);
            progress_view = (ProgressBar) itemView.findViewById(R.id.progress_view);
            tv_speed = (TextView) itemView.findViewById(R.id.tv_speed);
            tv_total = (TextView) itemView.findViewById(R.id.tv_total);
        }
    }
}
