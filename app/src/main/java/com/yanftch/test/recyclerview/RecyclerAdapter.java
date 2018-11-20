package com.yanftch.test.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yanftch.test.R;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> {

    private LayoutInflater mInflater;

    private ItemOnLongClickListener itemListener;

    private List<Session> sessionList;


    public RecyclerAdapter(Context context, List<Session> list) {
        sessionList = list;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemCount() {
        return null == sessionList ? 0 : sessionList.size();
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, final int position) {
        Session session = sessionList.get(position);
        holder.textView.setText(String.valueOf(session.getTop()));
        holder.imageView.setImageResource(session.getAvatar());
        //自己实现itemClickListener
        holder.mItemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                //回调
                itemListener.itemLongClick(sessionList.get(position));
                return false;
            }
        });
        if (session.getTop() == 1) {
            holder.mItemView.setBackgroundResource(R.drawable.bg_top_item_selector);
        } else {
            holder.mItemView.setBackgroundResource(R.drawable.bg_item_selector);
        }
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //  使用这种解析方式 RecyclerView的match parent 属性才不会失效
        return new RecyclerViewHolder(mInflater.inflate(R.layout.itemview, parent, false));
    }


    public void setItemListener(ItemOnLongClickListener listener) {
        itemListener = listener;
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        ImageView imageView;

        View mItemView;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            mItemView = itemView;
            textView = (TextView) itemView.findViewById(R.id.textView);
            imageView = (ImageView) itemView.findViewById(R.id.avatar_img);
        }
    }


    public interface ItemOnLongClickListener {

        void itemLongClick(Session session);
    }
}
