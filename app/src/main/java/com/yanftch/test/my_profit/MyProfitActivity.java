package com.yanftch.test.my_profit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yanftch.test.R;
import com.yanftch.test.utils.SpannableStrings;

/**
 * 我的收益
 */
public class MyProfitActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "dah_MyProfitActivity";
    private TextView tv_customer_service;//客服热线TextView
    private TextView tv_tax_deduction_detail;
    private Button btn_withdraw;
    private TextView tv_test;
    private TextView tv_money;
    private ImageView iv_tip_info;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profit);
        fbc();
        initCustomerService();
    }

    private void fbc() {
        iv_tip_info = (ImageView) findViewById(R.id.iv_tip_info);
        tv_money = (TextView) findViewById(R.id.tv_money);
        tv_customer_service = (TextView) findViewById(R.id.tv_customer_service);
        tv_tax_deduction_detail = (TextView) findViewById(R.id.tv_tax_deduction_detail);
        btn_withdraw = (Button) findViewById(R.id.btn_withdraw);
        tv_test = (TextView) findViewById(R.id.tv_test);
        initListener();
    }

    private void initListener() {
        tv_customer_service.setOnClickListener(this);
        tv_tax_deduction_detail.setOnClickListener(this);
        btn_withdraw.setOnClickListener(this);
        tv_test.setOnClickListener(this);
        iv_tip_info.setOnClickListener(this);
    }

    /**
     * 客服热线
     */
    private void initCustomerService() {
        String text = "客服热线:400-111-1111转8";
        SpannableString spannableString = SpannableStrings.setTextColor(text, getResources().getColor(R.color.gray), 0, 5);
        tv_customer_service.setText(spannableString);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.tv_customer_service:
                Toast.makeText(this, "拨打电话", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_tax_deduction_detail://扣税明细
                Toast.makeText(this, "查看扣税明细", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_withdraw://提现
                Intent intent = new Intent(this, WithDrawMoneryActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_test://明细
                Intent intent1 = new Intent(this, EarningsDetailsActivity.class);
                startActivity(intent1);
                break;
            case R.id.iv_tip_info://问号Tip
                Toast.makeText(this, "TIP", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
