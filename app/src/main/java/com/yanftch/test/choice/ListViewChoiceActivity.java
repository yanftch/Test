package com.yanftch.test.choice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.yanftch.test.R;

import java.util.ArrayList;

public class ListViewChoiceActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private Button btn_edit,btn_data;
    private ListView listView;
    private static final String TAG = "dah_ListViewChoiceActivity";
    private ArrayList<Bean> datas;
    private InvestSetAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_list_view_choice);
        datas = new ArrayList<>();
        initData();
        fbcListener();

    }

    private void initData() {
        for (int i = 0; i < 30; i++) {
            datas.add(new Bean("60900" + i));
        }
        mAdapter = new InvestSetAdapter(this, datas);
    }

    private void fbcListener() {
        btn_edit = (Button) findViewById(R.id.btn_edit);
        btn_data = (Button) findViewById(R.id.btn_data);
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(mAdapter);
        listView.setOnItemClickListener(this);
        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.flage = !mAdapter.flage;
                if (mAdapter.flage){
                    btn_edit.setText("取消");
                }else {
                    btn_edit.setText("编辑");
                }
                mAdapter.notifyDataSetChanged();
                BottomBoard bottomBoard = new BottomBoard(new BottomBoard.DialogClickListener() {
                    @Override
                    public void onLeftTopEvent() {
                        Toast.makeText(ListViewChoiceActivity.this, "left", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onRightCancleEvent() {
                        Toast.makeText(ListViewChoiceActivity.this, "right", Toast.LENGTH_SHORT).show();

                    }
                });
                bottomBoard.show(getFragmentManager(), "test_board");
            }
        });
        btn_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ArrayList<String> list = new ArrayList<String>();
                if (mAdapter.flage){
                    for (int i = 0; i < datas.size(); i++) {
                        if (datas.get(i).isOption()){
                            list.add(datas.get(i).getName());
                        }
                    }

                    Log.e(TAG, "onClick: " +list.toString());
                }
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Bean setBean = datas.get(position);
        if (setBean.isOption()) {
            setBean.setOption(false);
        } else {
            setBean.setOption(true);
        }

        /**
         * 一定记得刷型
         */
        mAdapter.notifyDataSetChanged();
    }
}
