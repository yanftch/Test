package com.yanftch.test.pullrefresh;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.xlf.nrl.NsRefreshLayout;
import com.yanftch.test.R;
import com.yanftch.test.common_adapter.CommonAdapter;
import com.yanftch.test.common_adapter.ViewHolder;
import com.yanftch.test.swap_menu_lv.CollectionBean;

import java.util.ArrayList;
import java.util.List;

public class PullActivity extends AppCompatActivity implements
        NsRefreshLayout.NsRefreshLayoutController, NsRefreshLayout.NsRefreshLayoutListener {
    private List<CollectionBean> datas = new ArrayList<>();
    private static final String TAG = "dah_PullActivity";
    private CommonAdapter adapter;
    private ListView listvierw;
    private NsRefreshLayout refreshLayout;
    private int count=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull);
        for (int i = 0; i < 3; i++) {
            datas.add(new CollectionBean("数据" + i));
        }
        listvierw = (ListView) findViewById(R.id.listvierw);
        refreshLayout = (NsRefreshLayout) findViewById(R.id.nrl_test);
        initRefreshLayout();
        adapter = new CommonAdapter<CollectionBean>(this, datas, R.layout.layout_item_swiplv) {
            @Override
            public void convert(ViewHolder holder, CollectionBean item) {
                holder.setText(R.id.tv_text, item.getName().toString());
            }
        };
        listvierw.setAdapter(adapter);

    }

    private void initRefreshLayout() {
        refreshLayout.setRefreshLayoutController(this);
        refreshLayout.setRefreshLayoutListener(this);
    }


    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.e(TAG, "run: 刷新中。。。。。");
                datas.add(1, new CollectionBean("刷新了的数据" + count));
                count += 1;
                refreshLayout.finishPullRefresh();
                adapter.notifyDataSetChanged();

            }
        }, 2000);
    }

    @Override
    public void onLoadMore() {
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                Log.e(TAG, "run: " + "加载中。。。。。。。。。。");
                for (int i = 0; i < 10; i++) {
                    datas.add(new CollectionBean("TEST" + count + 1));
                }
                adapter.notifyDataSetChanged();
                refreshLayout.finishPullLoad();
            }
        }, 2000);
    }

    @Override
    public boolean isPullRefreshEnable() {
        return true;
    }

    @Override
    public boolean isPullLoadEnable() {
        return true;
    }
}
