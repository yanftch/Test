package com.yanftch.test.tab;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import com.yanftch.test.R;

public class TabChangeActivity extends AppCompatActivity implements RadioButton.OnCheckedChangeListener {
    private FragmentManager manager;
    private FragmentTransaction ft;
    private static final String TAG = "dah_TabChangeActivity";
    private ProductFragment mProductFragment;
    private FindFragment mFindFragment;
    private RadioButton rb_left, rb_right;
    private View view_left,view_right;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_change);
        rb_left = (RadioButton) findViewById(R.id.rb_left);
        rb_right = (RadioButton) findViewById(R.id.rb_right);
        rb_left.setOnCheckedChangeListener(this);
        rb_right.setOnCheckedChangeListener(this);
        view_left = findViewById(R.id.view_left);
        view_right = findViewById(R.id.view_right);
        mProductFragment = new ProductFragment();
        mFindFragment = new FindFragment();
        manager = getSupportFragmentManager();
        ft = manager.beginTransaction();

        rb_left.setChecked(true);
    }


    public void switchContent(Fragment from, Fragment to, String tag) {
        FragmentTransaction transaction = manager.beginTransaction();
        if (null == from || !from.isAdded()) {
            transaction.add(R.id.fragment_content, to, tag).commit();
            return;

        }
        if (!to.isAdded()) {
            transaction.hide(from).add(R.id.fragment_content, to, tag).commit();
        } else {
            transaction.hide(from).show(to).commit();
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked && manager != null) {
            switch (buttonView.getId()) {
                case R.id.rb_left:
                    switchContent(mFindFragment, mProductFragment, "left");
                    view_left.setVisibility(View.VISIBLE);
                    view_right.setVisibility(View.INVISIBLE);
                    break;
                case R.id.rb_right:
                    switchContent(mProductFragment, mFindFragment, "right");
                    view_left.setVisibility(View.INVISIBLE);
                    view_right.setVisibility(View.VISIBLE);
                    break;
            }
        }
    }
}
