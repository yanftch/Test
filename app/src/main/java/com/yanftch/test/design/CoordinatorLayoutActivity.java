package com.yanftch.test.design;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.yanftch.test.R;

import java.util.ArrayList;
import java.util.List;

public class CoordinatorLayoutActivity extends AppCompatActivity {
    private static final String TAG = "dah_CoordinatorLayoutActivi";
    private RecyclerView recyclerView;
    private List<String> datas;
    private LayoutInflater mLayoutInflater;
    private MyAdapter mMyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_coordinator_layout);
        initView();

    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        datas = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            datas.add("item" + i);
        }
        mMyAdapter = new MyAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mMyAdapter);
    }
    class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(CoordinatorLayoutActivity.this).inflate(R.layout.item_corrdinator_view,parent,false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.mTextView.setText(datas.get(position));
        }

        @Override
        public int getItemCount() {
            return datas.size();
        }
        class MyViewHolder extends RecyclerView.ViewHolder{
            private TextView mTextView;

            public MyViewHolder(View itemView) {
                super(itemView);
                mTextView = (TextView) itemView.findViewById(R.id.textView);

            }
        }
    }
}
