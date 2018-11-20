package com.yanftch.test.vp.pager;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.yanftch.test.R;
import com.yanftch.test.vp.BasePager;

import java.util.ArrayList;

/**
 * User : yanftch
 * Date : 2017/5/11
 * Time : 11:33
 * Desc :
 */

public class Pager1 extends BasePager<ArrayList<String>> {
    private Context mContext;
    private TextView tv_pager_1;
    private static final String TAG = "dah_Pager1";

    public Pager1(Context context) {
        super(context);
        this.mContext = context;
    }

    @Override
    public View initView() {
        Log.e(TAG, "initView: 111111");
        View view = View.inflate(context, R.layout.layout_item_1, null);
        tv_pager_1 = (TextView) view.findViewById(R.id.tv_pager_1);
        tv_pager_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "点击", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    @Override
    public void initData(ArrayList<String> strings) {
        Log.e(TAG, "initData: 1111111");
        StringBuilder sb = new StringBuilder();
        int size = strings.size();
        for (int i = 0; i < size; i++) {
            sb.append(strings.get(i) + "=");
        }
        tv_pager_1.setText(sb.toString());
    }
}
