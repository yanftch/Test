package com.yanftch.test.swap_menu_lv;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yanftch.test.R;
import com.yanftch.test.swipemenulistview.SwipeMenuItem;

/**
 * User : yanftch
 * Date : 2017/5/18
 * Time : 19:12
 * Desc :
 */

public class MyMenuItem extends SwipeMenuItem {
    private Context mContext;
    private LinearLayout ll_menu_container;//点击的容器
    private LinearLayout ll_totalcontainer;//点击的容器
    private ImageView iv_menu_icon;
    private TextView tv_menu_text;
    private int iconID;
    private Drawable background;
//    SwipeMenuItem deleteItem = new SwipeMenuItem(
//            getApplicationContext());
//            deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
//                    0x3F, 0x25)));
//            deleteItem.setWidth(225);
//            deleteItem.setTitle("取关");
//            deleteItem.setTitleSize(14);
//            deleteItem.setTitleColor(Color.WHITE);
//            menu.addMenuItem(deleteItem);


    private void init(View view) {
        ll_totalcontainer = (LinearLayout) view.findViewById(R.id.ll_totalcontainer);
        ll_menu_container = (LinearLayout) view.findViewById(R.id.ll_menu_container);
        iv_menu_icon = (ImageView) view.findViewById(R.id.iv_menu_icon);
        tv_menu_text = (TextView) view.findViewById(R.id.tv_menu_text);
    }

    private void setIv_menu_icon() {
        iv_menu_icon.setImageResource(iconID);
    }

    private void setTv_menu_texts(String text) {
        tv_menu_text.setText(text);
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void setBackground() {
        ll_totalcontainer.setBackground(background);
    }

    /****/
    public void setIcon(int icon) {
        this.iconID = icon;
        setIv_menu_icon();
    }

    public void setTv_menu_text(String text) {
        setTv_menu_texts(text);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void setBackground(Drawable background) {
        this.background = background;
        setBackground();
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void setBackground(int resId) {
        this.background = mContext.getResources().getDrawable(resId);
        setBackground();
    }


    public MyMenuItem(Context context, View view) {
        super(context);
        this.mContext = context;
        init(view);
    }

}
