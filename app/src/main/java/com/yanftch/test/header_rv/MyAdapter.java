package com.yanftch.test.header_rv;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yanftch.test.R;

import java.util.ArrayList;
import java.util.List;

/**
 * User : yanftch
 * Date : 2017/7/21
 * Time : 08:38
 * Desc :
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {
    private RecyclerView mRecyclerView;
    private List<Bean> data = new ArrayList<>();
    private Context mContext;
    private View footerView;
    private View headerView;
    private onItemClickListener mOnItemClickListener;
    //Type
    private int TYPE_NORMAL = 1000;
    private int TYPE_HEADER = 1001;
    private int TYPE_FOOTER = 1002;

    public MyAdapter(List<Bean> data, Context mContext) {
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_FOOTER) {
            return new MyHolder(footerView);//foot布局
        } else if (viewType == TYPE_HEADER) {
            return new MyHolder(headerView);//head布局
        } else {
            return new MyHolder(getLayout(R.layout.item_list_layout,parent));//列表布局
        }
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        if (!isHeaderView(position) && !isFooterView(position)) {
            if (haveHeaderView()) position--;
            holder.tv_content.setText(data.get(position).getName());
            holder.tv_time.setText(data.get(position).getTime());
            final int finalPosition = position;
            holder.ll_container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != mOnItemClickListener) {
                        mOnItemClickListener.onItemClick(finalPosition);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        int count = (null == data ? 0 : data.size());
        if (null != footerView) {
            count++;
        }

        if (null != headerView) {
            count++;
        }
        return count;
    }

    @Override
    public int getItemViewType(int position) {
        if (isHeaderView(position)) {
            return TYPE_HEADER;
        } else if (isFooterView(position)) {
            return TYPE_FOOTER;
        } else {
            return TYPE_NORMAL;
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        try {
            if (mRecyclerView == null && mRecyclerView != recyclerView) {
                mRecyclerView = recyclerView;
            }
            ifGridLayoutManager();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private View getLayout(int layoutId, ViewGroup parent) {
        return LayoutInflater.from(mContext).inflate(layoutId, parent,false);
    }

    public void addHeaderView(View headerView) {
        if (haveHeaderView()) {
            throw new IllegalStateException("hearview has already exists!");
        } else {
            //避免出现宽度自适应
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            headerView.setLayoutParams(params);
            this.headerView = headerView;
            ifGridLayoutManager();
            notifyItemInserted(0);
        }

    }

    public void addFooterView(View footerView) {
        if (haveFooterView()) {
            throw new IllegalStateException("footerView has already exists!");
        } else {
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            footerView.setLayoutParams(params);
            this.footerView = footerView;
            ifGridLayoutManager();
            notifyItemInserted(getItemCount() - 1);
        }
    }
    //grid特殊处理
    private void ifGridLayoutManager() {
        if (mRecyclerView == null) return;
        final RecyclerView.LayoutManager layoutManager = mRecyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager.SpanSizeLookup originalSpanSizeLookup =
                    ((GridLayoutManager) layoutManager).getSpanSizeLookup();
            ((GridLayoutManager) layoutManager).setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return (isHeaderView(position) || isFooterView(position)) ?
                            ((GridLayoutManager) layoutManager).getSpanCount() :
                            1;
                }
            });
        }
    }

    private boolean haveHeaderView() {
        return headerView != null;
    }

    public boolean haveFooterView() {
        return footerView != null;
    }

    private boolean isHeaderView(int position) {
        return haveHeaderView() && position == 0;
    }

    private boolean isFooterView(int position) {
        return haveFooterView() && position == getItemCount() - 1;
    }


    public static class MyHolder extends RecyclerView.ViewHolder {
        private TextView tv_content, tv_time;
        private LinearLayout ll_container;

        public MyHolder(View itemView) {
            super(itemView);
            tv_content = (TextView) itemView.findViewById(R.id.item_content);
            tv_time = (TextView) itemView.findViewById(R.id.item_time);
            ll_container = (LinearLayout) itemView.findViewById(R.id.ll_container);
        }
    }

    public interface onItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(onItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }
}
