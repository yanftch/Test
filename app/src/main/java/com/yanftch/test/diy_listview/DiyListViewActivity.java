package com.yanftch.test.diy_listview;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.yanftch.test.R;
import com.yanftch.test.common_adapter.CommonAdapter;
import com.yanftch.test.common_adapter.ViewHolder;
import com.yanftch.test.swap_menu_lv.CollectionBean;

import java.util.ArrayList;

public class DiyListViewActivity extends AppCompatActivity {

    private RefreshListView listview_dit;
    private CommonAdapter adapter;
    private ArrayList<CollectionBean> datas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diy_list_view);
        datas = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            datas.add(new CollectionBean("数据" + i));
        }
        listview_dit = (RefreshListView) findViewById(R.id.listview_dit);
        adapter = new CommonAdapter<CollectionBean>(this, datas, R.layout.layout_item_swiplv) {
            @Override
            public void convert(ViewHolder holder, CollectionBean item) {
                holder.setText(R.id.tv_text, item.getName().toString());
            }
        };
        listview_dit.setAdapter(adapter);
        listview_dit.setPullEnable(true);
        listview_dit.setPushEnable(true);
        listview_dit.setOnRefreshListener(new RefreshListView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        int size = datas.size();
                        datas.clear();
                        for (int i = 0; i < 20; i++) {
                            datas.add(new CollectionBean("newItem" + i));
                        }
                        adapter.notifyDataSetChanged();
                        listview_dit.onRefreshComplete(true);
                    }
                }, 2000);
            }

            @Override
            public void onLoadMore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        int size = datas.size();
                        for (int i = 0; i < 20; i++) {
                            datas.add(new CollectionBean("XXXXXXX" + i));
                        }
                        adapter.notifyDataSetChanged();
                        listview_dit.onRefreshComplete(true);
                    }
                }, 2000);
            }
        });

    }
}
