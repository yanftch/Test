package com.yanftch.test.my_profit;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.yanftch.test.R;
import com.yanftch.test.other_view.NoScrollViewPager;
/**
 *
 * User : yanftch
 * Date : 2017/4/20
 * Time : 17:07
 * Desc :
 */


public class EarningsDetailsActivity extends AppCompatActivity {
    private final String TAG_IN = "TAG_IN";
    private final String TAG_OUT = "TAG_OUT";
    private static final String TAG = "dah_EarningsDetailsActivity";

    private TabLayout tab_layout;
    private NoScrollViewPager no_view_pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earnings_details);
        fbc();
    }

    private void fbc() {
        tab_layout = (TabLayout) findViewById(R.id.tab_layout);
        tab_layout.addTab(tab_layout.newTab().setText("收入明细").setTag(TAG_IN));
        tab_layout.addTab(tab_layout.newTab().setText("支出明细").setTag(TAG_OUT));
        no_view_pager = (NoScrollViewPager) findViewById(R.id.no_view_pager);
        tab_layout.setupWithViewPager(no_view_pager);
        initListener();//一定放在fbc方法的最后一行，防止空指针
    }

    /**
     * 事件监听
     */
    private void initListener() {
        tab_layout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                String tag = (String) tab.getTag();
                Log.e(TAG, "onTabSelected: tag === " + tag);
                if (TextUtils.equals(TAG_IN, tag)) {//收入
                    Toast.makeText(EarningsDetailsActivity.this, "收入", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(EarningsDetailsActivity.this, "支出", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
