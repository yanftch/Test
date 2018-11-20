package com.yanftch.test.vp.pager;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.yanftch.test.R;
import com.yanftch.test.vp.BasePager;

import java.util.ArrayList;

/**
 * User : yanftch
 * Date : 2017/5/11
 * Time : 11:35
 * Desc :
 */

public class Pager3 extends BasePager<ArrayList<String>> {
    private Context mContext;
    private TextView tv_pager_3;
    private static final String TAG = "dah_Pager3";

    public Pager3(Context context) {
        super(context);
        this.mContext = context;
    }

    @Override
    public View initView() {
        Log.e(TAG, "initView: 3333333");
        View view = View.inflate(context, R.layout.layout_item_3, null);
        tv_pager_3 = (TextView) view.findViewById(R.id.tv_pager_3);
        return view;
    }

    @Override
    public void initData(ArrayList<String> strings) {
        Log.e(TAG, "initData: 3333333");
        StringBuilder sb = new StringBuilder();
        int size = strings.size();
        for (int i = 0; i < size; i++) {
            sb.append(strings.get(i) + "-");
        }
        tv_pager_3.setText(sb.toString());
    }
}
