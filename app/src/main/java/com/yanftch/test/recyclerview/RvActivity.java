package com.yanftch.test.recyclerview;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.yanftch.test.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RvActivity extends AppCompatActivity {

    public static String TOP_STATES = "TOP";

    private RecyclerView mRecyclerView;

    /**
     * 可以不使用ArrayAdapter来实现
     */
//    private RecyclerViewAdapter mRecyclerViewAdapter;

    //使用默认的adapter
    private RecyclerAdapter mRecyclerAdapter;

    private List<Session> sessionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv);
        init();
        registerListener();
    }


    private void init() {
        TypedArray typedArray = this.getResources().obtainTypedArray(R.array.icon_array);
        sessionList = new ArrayList<>();
        for (int i = 0; i < 18; i++) {
            Session session = new Session();
            session.setTime(i);
            session.setAvatar(typedArray.getResourceId(i, R.mipmap.ic_launcher));
            sessionList.add(session);
        }

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
//        mRecyclerViewAdapter = new RecyclerViewAdapter(this,sessionList);
        mRecyclerAdapter = new RecyclerAdapter(this, sessionList);
        mRecyclerView.setAdapter(mRecyclerAdapter);
        ItemTouchHelper.Callback callback = new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                int fromPosition = viewHolder.getAdapterPosition();
                int toPosition = target.getAdapterPosition();
                if (fromPosition < toPosition) {
                    for (int i = fromPosition; i < toPosition; i++) {
                        Collections.swap(sessionList, i, i + 1);
                    }
                } else {
                    for (int i = toPosition; i < fromPosition; i++) {
                        Collections.swap(sessionList, i, i + 1);
                    }
                }

                mRecyclerAdapter.notifyItemMoved(fromPosition, toPosition);

                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);

        refreshView();
        typedArray.recycle();
    }


    private void registerListener() {

        mRecyclerAdapter.setItemListener(new RecyclerAdapter.ItemOnLongClickListener() {
            @Override
            public void itemLongClick(final Session session) {
                Bundle bundle = new Bundle();
                bundle.putInt(TOP_STATES, session.getTop());
                PopupDialogFragment popupDialog = new PopupDialogFragment();
                popupDialog.setArguments(bundle);
                popupDialog.setItemOnClickListener(new PopupDialogFragment.DialogItemOnClickListener() {
                    @Override
                    public void onTop() {
                        //置顶
                        session.setTop(1);
                        session.setTime(System.currentTimeMillis());
                        refreshView();
                    }

                    @Override
                    public void onCancel() {
                        //取消
                        session.setTop(0);
                        session.setTime(System.currentTimeMillis());
                        refreshView();
                    }
                });
                popupDialog.show(getFragmentManager(), "popup");
            }
        });

        mRecyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "hello", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }


    private void refreshView() {
        //如果不调用sort方法，是不会进行排序的，也就不会调用compareTo
        Collections.sort(sessionList);
        mRecyclerAdapter.notifyDataSetChanged();
    }
}
