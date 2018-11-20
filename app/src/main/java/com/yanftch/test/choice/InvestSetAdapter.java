package com.yanftch.test.choice;

/**
 * @author Iven
 * @date 2016/9/26 12:11
 * @Description 投资设置的ListView适配器
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.yanftch.test.R;

import java.util.ArrayList;


public class InvestSetAdapter extends BaseAdapter {
    private Context mContext;//上下文
    private ArrayList<Bean> datas;//数据集
    public boolean flage = false;

    public InvestSetAdapter(Context mContext, ArrayList<Bean> datas) {
        this.mContext = mContext;
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_choice, null);
            holder.tv_text_name = (TextView) convertView.findViewById(R.id.tv_text_name);//文本
            holder.iv_option = (ImageView) convertView.findViewById(R.id.iv_option);
            holder.checkboxOperateData = (CheckBox) convertView.findViewById(R.id.checkbox_operate_data);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final Bean bean = datas.get(position);
        if (flage){
            holder.checkboxOperateData.setVisibility(View.VISIBLE);
        }else {
            holder.checkboxOperateData.setVisibility(View.GONE);
        }
        holder.checkboxOperateData.setChecked(datas.get(position).isOption());
        //数据处理
        // TODO: 2016/9/28 将颜色、文本、图标，构造一个组合的View，后续改进，
        if (datas.get(position).isOption()) {//选中
            setStyle(holder, R.drawable.shape_invest_checked, R.mipmap.invest_indu_checked, mContext.getResources().getColor(R.color.color_333333));
        } else {
            setStyle(holder, R.drawable.shape_invest_unchecked, R.mipmap.invest_indu_unchecked, mContext.getResources().getColor(R.color.color_cccccc));
        }

        holder.tv_text_name.setText(datas.get(position).getName());//文本
        holder.checkboxOperateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bean.isOption()){
                    bean.setOption(false);
                }else {
                    bean.setOption(true);
                }
            }
        });
        return convertView;
    }

    /**
     * just for simplify
     */
    private void setStyle(ViewHolder holder, int shape_invest_checked, int invest_indu_checked, int color) {
        holder.iv_option.setImageResource(invest_indu_checked);//图标
        holder.tv_text_name.setTextColor(color);//文本置黑

    }

    class ViewHolder {
        /**
         * 文本显示-name
         */
        private TextView tv_text_name;
        /**
         * 图片标识
         */
        private ImageView iv_option;
        public CheckBox checkboxOperateData;
    }

}
