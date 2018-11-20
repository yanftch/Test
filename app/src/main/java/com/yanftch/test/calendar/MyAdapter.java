package com.yanftch.test.calendar;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yanftch.test.R;

import java.util.ArrayList;

/**
 * User : yanftch
 * Date : 2017/6/29
 * Time : 15:49
 * Desc :
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private static final String TAG = "dah_MyAdapter";
    private ArrayList<CBean> datas;
    private Context mContext;
    private LayoutInflater mInflater;
    private onItemClickListener mOnItemClickListener;

    public interface onItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(onItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public MyAdapter(Context context, ArrayList<CBean> datas) {
        this.datas = datas;
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyAdapter.ViewHolder(mInflater.inflate(R.layout.my_calendar_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        CBean bean = datas.get(position);
        holder.mDateTextView.setText(bean.getDateNo());
        boolean hasSign = bean.isHasSign();//是否已经签到了
        final boolean canBeSign = bean.isCanBeSign();//是否可以点击签到
        if (hasSign) {
            holder.mDateTextView.setBackgroundResource(R.drawable.shape_circle_red);
//            holder.bgColorLinearLayout.setBackgroundColor(Color.parseColor("#ff00ff"));
        } else {
//            holder.bgColorLinearLayout.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
        if (canBeSign && !hasSign) {//可以点击 && 未签到
            holder.bgColorLinearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != mOnItemClickListener &&  canBeSign) {
                        mOnItemClickListener.onItemClick(position);
                    }
                }
            });
        } else {
            holder.bgColorLinearLayout.setOnClickListener(null);
            holder.mDateTextView.setTextColor(mContext.getResources().getColor(R.color.color_cccccc));
        }
    }

    @Override
    public int getItemCount() {
        return null == datas ? 0 : datas.size();
    }
    public ArrayList<CBean> getDatas(){
        return datas;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mDateTextView;
        private LinearLayout bgColorLinearLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            bgColorLinearLayout = (LinearLayout) itemView.findViewById(R.id.ll_bg_container);
            mDateTextView = (TextView) itemView.findViewById(R.id.tv_date_show);
        }
    }
}
