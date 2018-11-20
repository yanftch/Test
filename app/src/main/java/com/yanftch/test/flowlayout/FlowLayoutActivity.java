package com.yanftch.test.flowlayout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.yanftch.test.R;
import com.yanftch.test.flowlayout.flowlayout.FlowLayout;
import com.yanftch.test.flowlayout.flowlayout.TagAdapter;
import com.yanftch.test.flowlayout.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class FlowLayoutActivity extends AppCompatActivity {
    private static final String TAG = "dah_FlowLayoutActivity";

    private TagFlowLayout id_flowlayout;
    private List<String> mList = new ArrayList();
    private LayoutInflater mInflater;
    private FlowLayout flowlayout;


    private int a = 9;
    private int b = 9;

    private int[] arr = new int[4];

    private List mLists  = new ArrayList();

    private List<Person> mPersons = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flow_layout);
        id_flowlayout = (TagFlowLayout) findViewById(R.id.id_flowlayout);
        flowlayout = (FlowLayout) findViewById(R.id.flowlayout);



        Log.e(TAG, "onCreate: "+mPersons.size());

        mInflater = LayoutInflater.from(this);
        for (int i = 0; i < 8; i++) {
            mList.add("标签" + i);
        }
        id_flowlayout.setAdapter(new TagAdapter<String>(mList) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) mInflater.inflate(R.layout.layout_tv,
                        id_flowlayout, false);
                tv.setText(s);
                return tv;
            }
        });
    }

    public void btnGetMyData(View view) {
        Set<Integer> selectedList = id_flowlayout.getSelectedList();
        for (Integer i : selectedList) {
            Log.e(TAG, "btnGetMyData: " + mList.get(i));
        }
    }
}
