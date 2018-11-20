package com.yanftch.test.keyboard;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.Toast;

import com.yanftch.test.Main2Activity;
import com.yanftch.test.R;
import com.yanftch.test.other_view.CheckView;
import com.yanftch.test.other_view.CountdownButton;
import com.yanftch.test.other_view.VerView;
import com.yanftch.test.other_view.VerifyCodeView;
import com.yanftch.test.status.ProgressStatusLayout;
import com.yanftch.test.timepickerview.OnDismissListener;
import com.yanftch.test.timepickerview.TimePickerView;
import com.yanftch.test.timepickerview.TimeShiftUtil;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class KeyboardActivity extends AppCompatActivity {
    private static final String TAG = "dah_KeyboardActivity";
    private ProgressStatusLayout progress_layout;
    private int count = 0;
    private CountdownButton cdb_register_timer;
    private ImageView ver_view;
    private VerifyCodeView my_codeview;
    private static final char[] CHARS = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
    };
    private WebView mWebView;
    private WebSettings mSettings;
    private String mURL = "http://www.baidu.com";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keyboard);
        mWebView = (WebView) findViewById(R.id.webView);
//        mWebView.setLayerType(View.LAYER_TYPE_HARDWARE,null);
//        mSettings = mWebView.getSettings();
//        mSettings.setJavaScriptEnabled(true);
//
//        mWebView.setWebChromeClient(new WebChromeClient());
//        mWebView.setWebViewClient(new WebViewClient(){
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                view.loadUrl(url);
//                return true;
//            }
//        });


        initCheckView();
        setLayoutProgress();
        cdb_register_timer = (CountdownButton) findViewById(R.id.cdb_register_timer);
        cdb_register_timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cdb_register_timer.start();
            }
        });
        ver_view = (ImageView) findViewById(R.id.ver_view);
        Bitmap bitmap = VerView.getInstance().createBitmap();
        ver_view.setImageBitmap(bitmap);
        ver_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ver_view.setImageBitmap(VerView.getInstance().createBitmap());
            }
        });
        my_codeview = (VerifyCodeView) findViewById(R.id.my_codeview);
        my_codeview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = createCode();
                my_codeview.setCheckCodeText(code);
                Toast.makeText(KeyboardActivity.this, "code == " + my_codeview.getCode(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private String createCode() {
        Random random = new Random();
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            buffer.append(CHARS[random.nextInt(CHARS.length)]);
        }
        Log.e(TAG, "createCode: 验证码 == " + buffer.toString());
        return buffer.toString();
    }

    private void initCheckView() {
        final CheckView checkView = (CheckView) findViewById(R.id.che_view);
        checkView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkView.invaliChenkCode();
            }
        });
    }


    private void setLayoutProgress() {
        progress_layout = (ProgressStatusLayout) findViewById(R.id.progress_layout);
        progress_layout.setSchedule(0);//左边是带颜色的
        progress_layout.setText("111", "222", "333");
        progress_layout.setTextBottom("b1", "b2", "b3");

    }

    public void btnClick2ChangeStatus(View view) {
        count++;
        Log.e(TAG, "btnClick2ChangeStatus:    count   ===   " + count);
        if (count == 1) {
            progress_layout.setText("体现0", "进行中0", "成功0");
            progress_layout.setTextBottom("2011-11-11 12:12", "进行中b0", "成功b0");
            progress_layout.setTextColor(R.color.colorAccent, R.color.colorAccent, R.color.line_color_gray);
            progress_layout.setIconBackground(R.drawable.shape_circle_red, R.drawable.shape_circle_gray, R.drawable.shape_circle_gray);
//            progress_layout.setIcon(R.mipmap.step_1_enable, R.mipmap.step_2_enable, R.mipmap.step_3_unable);
            progress_layout.setSchedule(1);

        }
        if (count == 2) {
            progress_layout.setText("体现1", "进行中1", "成功1");
            progress_layout.setTextBottom("2011-11-11 12:12", "2011-11-11 12:12", "成功b1");
            progress_layout.setIconBackground(R.drawable.shape_circle_red, R.drawable.shape_circle_red, R.drawable.shape_circle_gray);
//            progress_layout.setIcon(R.mipmap.step_1_enable, R.mipmap.step_2_enable, R.mipmap.step_3_enable);
            progress_layout.setSchedule(2);
            progress_layout.setTextColor(R.color.colorAccent, R.color.colorAccent, R.color.colorAccent);
        }
        if (count == 3) {
            progress_layout.setText("体现2", "进行中2", "失败2");
            progress_layout.setTextBottom("体现b2", "233333", "2011-11-11 12:12");
            progress_layout.setIconBackground(R.drawable.shape_circle_red, R.drawable.shape_circle_red, R.drawable.shape_circle_red);
//            progress_layout.setIcon(R.mipmap.step_1_unable, R.mipmap.step_2_unable, R.mipmap.step_3_unable);
            progress_layout.setSchedule(0);
            progress_layout.setTextColor(R.color.line_color_gray, R.color.line_color_gray, R.color.line_color_gray);
            count = 0;
        }
    }

    /**
     * 提现功能
     *
     * @param view
     */
    public void btnClick2Withdraw(View view) {
        KeyBoardFragment keyBoardFragment = new KeyBoardFragment(new KeyBoardFragment.DialogClickListener() {
            @Override
            public void onCancleClick() {
                Toast.makeText(KeyboardActivity.this, "点击了x", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPwdCompleteListener(String pwd) {
                Toast.makeText(KeyboardActivity.this, "callback   for     password == " + pwd, Toast.LENGTH_SHORT).show();
            }
        });
//        keyBoardFragment.show(getFragmentManager(), "keyboard");
        mWebView.loadUrl("https://www.baidu.com");
    }

    public void btnClick2SelectTime(View view) {
        addTimeSelctor("计息日", "确定");
    }

    /**
     * 时间选择器
     *
     * @param title
     * @param righttitle
     */
    private void addTimeSelctor(final String title, final String righttitle) {
        final TimePickerView pvTime = new TimePickerView(this, TimePickerView.Type.YEAR_MONTH);
        Calendar calendar = Calendar.getInstance();
        pvTime.setTitle(title);
        pvTime.setRightButton(righttitle);
        pvTime.setRange(2015, calendar.get(Calendar.YEAR));//时间选择范围

        String time = null;
        if (TextUtils.equals(righttitle, "确认")) {
            time = "2016-10";
        } else {
            time = "2016-11";
        }
        pvTime.setTime(TimeShiftUtil.timeToDate(time));
        pvTime.setCyclic(false);
        pvTime.setCancelable(true);
        pvTime.show();

        pvTime.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date) {
                Log.e(TAG, "onTimeSelect: " + date);
                Log.e(TAG, "onTimeSelect: getTime ==== " + getTime(date));
            }
        });
        pvTime.setOnSubmitClickListener(new TimePickerView.SubmitOnClickListener() {
            @Override
            public void onSubmitClickListener() {
                Toast.makeText(KeyboardActivity.this, "submit...", Toast.LENGTH_SHORT).show();
                pvTime.dismiss(1);
            }
        });
        pvTime.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss(Object o) {
                Toast.makeText(KeyboardActivity.this, "dismiss...", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 格式化
     */
    public static String getTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        return format.format(date);
    }


    public void btnHalfActivity(View view) {
        startActivity(new Intent(KeyboardActivity.this, Main2Activity.class));
    }
}
