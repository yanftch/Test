package com.yanftch.test.tab;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yanftch.test.R;

/**
 * User : yanftch
 * Date : 2017/5/25
 * Time : 22:22
 * Desc :
 */

public class ProductFragment extends Fragment {
    private static final String TAG = "dah_ProductFragment";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_product,null);
        return view;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate: 产品");
    }
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden){
            Log.e(TAG, "onHiddenChanged:    产品  隐藏了");
        }else {
            Log.e(TAG, "onHiddenChanged:    产品  出现了");
        }
    }
}
