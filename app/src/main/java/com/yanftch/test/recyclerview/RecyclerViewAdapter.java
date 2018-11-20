package com.yanftch.test.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yanftch.test.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shadow on 2016/3/15.
 * 建议只有在有复杂的数据操作的时候才使用ArrayAdapter
 *
 */
public class RecyclerViewAdapter extends RecyclerArrayAdapter<Session, RecyclerViewAdapter.RecyclerViewHolder> {


    private LayoutInflater mInflater;

    private ItemOnLongClickListener itemListener;


    public RecyclerViewAdapter(Context context) {
        super(new ArrayList<Session>());
        mInflater = LayoutInflater.from(context);
    }

    public RecyclerViewAdapter(Context context,List<Session> list) {
        super(list);
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, final int position) {
        Session session = getItem(position);
        holder.textView.setText(String.valueOf(session.getTop()));
        holder.imageView.setImageResource(session.getAvatar());
        //自己实现itemClickListener
        holder.mItemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                //回调
                itemListener.itemLongClick(getItem(position));
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

    public void updateData(List<Session> list) {
        clear();
        addAll(list);
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
