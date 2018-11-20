package com.yanftch.test.event_bus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.yanftch.test.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 *
 * user : yanftch
 * date : 2017/4/20
 * time : 10:38
 * desc :
 */


public class EventBus1Activity extends AppCompatActivity {
    private static final String TAG = "dah_EventBus1Activity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus1);
        EventBus.getDefault().register(this);

        Button btn_go_next = (Button) findViewById(R.id.btn_go_next);
        btn_go_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EventBus1Activity.this,EventBus2Activity.class));
            }
        });
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(MsgEvent msgEvent) {
        String type = msgEvent.getType();
        Log.e(TAG, "onEvent:type ==  " + type);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
