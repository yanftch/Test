//package com.yanftch.test.swap_menu_lv;
//
//import android.graphics.Color;
//import android.graphics.drawable.ColorDrawable;
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//
//import com.baoyz.swipemenulistview.SwipeMenu;
//import com.baoyz.swipemenulistview.SwipeMenuCreator;
//import com.baoyz.swipemenulistview.SwipeMenuItem;
//import com.baoyz.swipemenulistview.SwipeMenuListView;
//import com.yanftch.test.R;
//import com.yanftch.test.common_adapter.CommonAdapter;
//import com.yanftch.test.common_adapter.ViewHolder;
//
//import java.util.ArrayList;
//
//public class Test extends AppCompatActivity {
//
//    private SwipeMenuListView swip_listview;
//    private CommonAdapter adapter;
//    private ArrayList<String> datas;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_swip_menu);
//        datas = new ArrayList<>();
//        for (int i = 0; i < 30; i++) {
//            datas.add("test" + i);
//        }
//        swip_listview = (SwipeMenuListView) findViewById(R.id.swip_listview);
//        adapter = new CommonAdapter<String>(this, datas, R.layout.layout_item_swiplv) {
//            @Override
//            public void convert(ViewHolder helper, String item) {
//                helper.setText(R.id.tv_text,item.toString());
//            }
//        };
//        swip_listview.setAdapter(adapter);
//        swip_listview.setMenuCreator(creator);
//
//    }
//
//    private SwipeMenuCreator creator = new SwipeMenuCreator() {
//
//        @Override
//        public void create(SwipeMenu menu) {
//            //取消收藏的按钮
//            SwipeMenuItem deleteItem = new SwipeMenuItem(
//                    getApplicationContext());
//            deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
//                    0x3F, 0x25)));
//            deleteItem.setWidth(225);
//            deleteItem.setTitle("取关");
//            deleteItem.setTitleSize(14);
//            deleteItem.setTitleColor(Color.WHITE);
//            menu.addMenuItem(deleteItem);
//            //置顶的按钮
//            SwipeMenuItem toTopItem = new SwipeMenuItem(getApplicationContext());
//            toTopItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
//                    0x3F, 0x25)));
//            toTopItem.setWidth(225);
//            toTopItem.setTitle("置顶");
//            toTopItem.setTitleSize(26);
//            toTopItem.setTitleColor(Color.WHITE);
//            menu.addMenuItem(toTopItem);
//        }
//    };
//}
