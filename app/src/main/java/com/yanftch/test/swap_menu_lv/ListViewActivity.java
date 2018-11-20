package com.yanftch.test.swap_menu_lv;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.yanftch.test.R;
import com.yanftch.test.common_adapter.CommonAdapter;
import com.yanftch.test.common_adapter.ViewHolder;
import com.yanftch.test.refreshview.XRefreshView;
import com.yanftch.test.swipemenulistview.SwipeMenu;
import com.yanftch.test.swipemenulistview.SwipeMenuCreator;
import com.yanftch.test.swipemenulistview.SwipeMenuItem;

import java.util.ArrayList;
import java.util.List;

public class ListViewActivity extends Activity {
    private List<CollectionBean> datas = new ArrayList<>();
    private XRefreshView refreshView;
    private CommonAdapter adapter;
    public static long lastRefreshTime;
    private static final String TAG = "dah_ListViewActivity";
    private ListView swip_listView;
    private int count;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        for (int i = 0; i < 3; i++) {
            datas.add(new CollectionBean("数据" + i));
        }
        swip_listView = (ListView) findViewById(R.id.swip_listView);
        refreshView = (XRefreshView) findViewById(R.id.custom_view);
        refreshView.setMoveForHorizontal(true);//滑动冲突处理，一定要加上 啊啊啊啊--最好是加上~~~~~~~
        adapter = new CommonAdapter<CollectionBean>(this, datas, R.layout.layout_item_swiplv) {
            @Override
            public void convert(ViewHolder holder, CollectionBean item) {
                holder.setText(R.id.tv_text, item.getName().toString());
            }
        };
        swip_listView.setAdapter(adapter);

        swip_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ListViewActivity.this, datas.get(position).toString() + "position = " + position, Toast.LENGTH_SHORT).show();

            }
        });

        // 设置是否可以下拉刷新
        refreshView.setPullRefreshEnable(true);
        // 设置是否可以上拉加载
        refreshView.setPullLoadEnable(true);
        // 设置上次刷新的时间
        refreshView.restoreLastRefreshTime(lastRefreshTime);
        //当下拉刷新被禁用时，调用这个方法并传入false可以不让头部被下拉
        refreshView.setMoveHeadWhenDisablePullRefresh(true);
        // 设置时候可以自动刷新
        refreshView.setAutoRefresh(false);
        refreshView.setAutoLoadMore(true);
        //自定义FootView
        refreshView.setCustomFooterView(new CustomFooterView(this));
        refreshView.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {
            @Override
            public void onRefresh(boolean isPullDown) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Log.e(TAG, "run: 刷新中。。。。。");
                        refreshView.stopRefresh();
                        lastRefreshTime = refreshView.getLastRefreshTime();
                        datas.add(1, new CollectionBean("刷新了的数据" + count));
                        count += 1;
                        adapter.notifyDataSetChanged();

                    }
                }, 2000);
            }

            @Override
            public void onLoadMore(boolean isSilence) {
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        Log.e(TAG, "run: " + "加载中。。。。。。。。。。");
                        refreshView.stopLoadMore();
                        for (int i = 0; i < 10; i++) {
                            datas.add(new CollectionBean("TEST" + count + 1));
                        }
                        adapter.notifyDataSetChanged();
                    }
                }, 2000);
            }

            @Override
            public void onRelease(float direction) {
                super.onRelease(direction);
                if (direction > 0) {
                    toast("下拉");
                } else {
                    toast("上拉");
                }
            }
        });

    }

    public void toast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    private SwipeMenuCreator creator = new SwipeMenuCreator() {

        @Override
        public void create(SwipeMenu menu) {
            //取消收藏的按钮
            SwipeMenuItem deleteItem = new SwipeMenuItem(
                    getApplicationContext());
            deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                    0x3F, 0x25)));
            deleteItem.setWidth(225);
            deleteItem.setTitle("取关");
            deleteItem.setTitleSize(14);
            deleteItem.setTitleColor(Color.WHITE);
            menu.addMenuItem(deleteItem);
            //置顶的按钮
            SwipeMenuItem toTopItem = new SwipeMenuItem(getApplicationContext());
            toTopItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                    0x3F, 0x25)));
            toTopItem.setWidth(225);
            toTopItem.setTitle("置顶");
            toTopItem.setTitleSize(26);
            toTopItem.setTitleColor(Color.WHITE);
            menu.addMenuItem(toTopItem);
            View view = View.inflate(getApplicationContext(), R.layout.layout_custom_menu, null);
            MyMenuItem item = new MyMenuItem(getApplicationContext(),view);
            item.setBackground(new ColorDrawable(Color.rgb(0xF9,
                    0x3F, 0x25)));
            item.setTv_menu_text("额额");
            item.setIcon(R.mipmap.bank_gsyh_icon);
            item.setWidth(225);
            menu.addMenuItem(item);
        }
    };
}