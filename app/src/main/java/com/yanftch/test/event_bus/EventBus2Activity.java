package com.yanftch.test.event_bus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.yanftch.test.R;

import org.greenrobot.eventbus.EventBus;

public class EventBus2Activity extends AppCompatActivity {
    private static final String TAG = "dah_EventBus2Activity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus2);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().post(new MsgEvent(MsgEvent.MSG_TEST_01));
    }
}
