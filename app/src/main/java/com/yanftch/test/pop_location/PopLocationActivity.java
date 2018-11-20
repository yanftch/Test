package com.yanftch.test.pop_location;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.yanftch.test.R;
import com.yanftch.test.common_adapter.CommonAdapter;
import com.yanftch.test.common_adapter.ViewHolder;

import java.util.ArrayList;
import java.util.List;

public class PopLocationActivity extends AppCompatActivity {
    private static final String TAG = "dah_PopLocationActivity";
    private ListView listView;
    private CommonAdapter<String> mAdapter;
    private List<String> datas = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_location);
        listView = (ListView) findViewById(R.id.listView);
        for (int i = 0; i < 20; i++) {
            datas.add("item" + i);
        }
        mAdapter = new CommonAdapter<String>(this, datas, R.layout.item_pop_view) {
            @Override
            public void convert(ViewHolder holder, String item) {
                final int position = holder.getPosition();
                holder.setText(R.id.textView, item);
                ImageView view = holder.getView(R.id.imageView);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showPopupwindow(position, v);
                    }
                });
            }
        };
        listView.setAdapter(mAdapter);
    }

    private void showPopupwindow(final int position, View anchorView) {
        final PopupWindow popupWindow = new PopupWindow(PopLocationActivity.this);
        popupWindow.setWidth(220);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);
        //popupWindow.setAnimationStyle(R.style.anim_menu_bottombar);//设置动画效果
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        View view = View.inflate(PopLocationActivity.this, R.layout.layout_pop_view, null);
        ListView listView = (ListView) view.findViewById(R.id.popListView);
        final List<PopWindowItemBean> datas = new ArrayList<>();
        /**
         *----------------------------------------------------------------------------------------------------
         */
        PopWindowItemBean bean = new PopWindowItemBean();
        bean.setPopName("001");
        bean.setPopCode("00");
        datas.add(bean);
        PopWindowItemBean bean1 = new PopWindowItemBean();
        bean1.setPopName("002");
        bean1.setPopCode("01");
        datas.add(bean1);
        PopWindowItemBean bean2 = new PopWindowItemBean();
        bean2.setPopName("003");
        bean2.setPopCode("02");
        datas.add(bean2);
        PopWindowItemBean bean3 = new PopWindowItemBean();
        bean3.setPopName("004");
        bean3.setPopCode("03");
        datas.add(bean3);
        PopWindowItemBean bean4 = new PopWindowItemBean();
        bean4.setPopName("005");
        bean4.setPopCode("04");
        datas.add(bean4);
        PopWindowItemBean bean5 = new PopWindowItemBean();
        bean5.setPopName("006");
        bean5.setPopCode("05");
        datas.add(bean5);
        /**
         *----------------------------------------------------------------------------------------------------
         */

        CommonAdapter<PopWindowItemBean> adapter;
        adapter = new CommonAdapter<PopWindowItemBean>(PopLocationActivity.this, datas, R.layout.item_layout_pop_lv_item) {
            @Override
            public void convert(ViewHolder holder, PopWindowItemBean item) {
                if (!TextUtils.isEmpty(item.getPopName()))
                    holder.setText(R.id.hahah, item.getPopName());
            }
        };

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PopWindowItemBean bean = datas.get(position);
                Toast.makeText(PopLocationActivity.this, bean.getPopName(), Toast.LENGTH_SHORT).show();
                popupWindow.dismiss();
            }
        });
        popupWindow.setContentView(view);
        int[] locations = new int[2];
        anchorView.getLocationOnScreen(locations);//获取当前控件在屏幕上的位置
        popupWindow.showAtLocation(anchorView, Gravity.NO_GRAVITY, locations[0] - 180, locations[1] + 50);
    }
}
