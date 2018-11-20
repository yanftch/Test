package com.yanftch.test.my_profit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.yanftch.test.R;
import com.yanftch.test.keyboard.KeyBoardFragment;
import com.yanftch.test.utils.SpannableStrings;

/**
 * 提现页面-输入提现金额的页面，显示绑定的银行卡
 * todo 金额输入框，假如用户输入的第一位是0，怎么处理？（支付宝是如果你第一个输入的是0，再输第二个的时候如果还是0，就不让输入第二个0，如果是别的数字的话，就直接将第一个0给干掉，然后将刚才输入的数字放在第一位了）
 */
public class WithDrawMoneryActivity extends AppCompatActivity implements OnClickListener {
    private static final String TAG = "dah_WithDrawMoneryActivity";
    private double availableTotalMonery;//可以提现的总金额
    private double withdrawMonery;//提现金额
    private double feeMonery;//手续费
    private Button btn_go2withdraw;//提现
    private EditText et_withdraw_monery;
    private boolean nextCanInput;
    private TextView tv_withdraw_total_monery;
    private TextView tv_protol;//协议

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_with_draw_monery);
        availableTotalMonery = 1234567.88;//接口返回，赋值时机
        fbc();
    }

    private void fbc() {
        btn_go2withdraw = (Button) findViewById(R.id.btn_go2withdraw);
        et_withdraw_monery = (EditText) findViewById(R.id.et_withdraw_monery);
        tv_withdraw_total_monery = (TextView) findViewById(R.id.tv_withdraw_total_monery);
        tv_protol = (TextView) findViewById(R.id.tv_protol);
        String text = "阅读并同意代扣协议";
        tv_protol.setText(SpannableStrings.setTextColor(text, getResources().getColor(R.color.colorAccent), text.length() - 4, text.length()));
        initListener();
    }

    private void initListener() {
        btn_go2withdraw.setOnClickListener(this);
        et_withdraw_monery.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().contains(".")) {
                    if (s.length() - 1 - s.toString().indexOf(".") > 2) {
                        s = s.toString().subSequence(0,
                                s.toString().indexOf(".") + 3);
                        et_withdraw_monery.setText(s);
                        et_withdraw_monery.setSelection(s.length());
                    }
                }
                if (s.toString().trim().substring(0).equals(".")) {
                    s = "0" + s;
                    et_withdraw_monery.setText(s);
                    et_withdraw_monery.setSelection(2);
                }
                //第一位输入为0之后，不能直接输入数字了，需要输入小数点解锁输入
                if (s.toString().startsWith("0")
                        && s.toString().trim().length() > 1) {
                    if (!s.toString().substring(1, 2).equals(".")) {
                        et_withdraw_monery.setText(s.subSequence(0, 1));
                        et_withdraw_monery.setSelection(1);
                        return;
                    }
                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        tv_withdraw_total_monery.setOnClickListener(this);
        tv_protol.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn_go2withdraw://提现
                if (TextUtils.isEmpty(et_withdraw_monery.getText().toString())) {
                    Toast.makeText(this, "提现金额不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                withdrawMonery = Double.parseDouble(et_withdraw_monery.getText().toString());
                if (withdrawMonery != 0) {
                    showPwdInput(withdrawMonery);
                } else {
                    Toast.makeText(this, "提现金额得大于0元吧？~~", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.tv_withdraw_total_monery://全部
                // TODO: 2017/4/20 全部点完之后什么操作？
                if (availableTotalMonery > 0) {
                    et_withdraw_monery.setText(String.format("%s", availableTotalMonery));
                }
                break;
            case R.id.tv_protol:
                Toast.makeText(this, "协议", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    /**
     * 显示
     *
     * @param monery 要提现的金额
     */
    private void showPwdInput(double monery) {
        KeyBoardFragment keyBoardFragment = new KeyBoardFragment(monery, new KeyBoardFragment.DialogClickListener() {
            @Override
            public void onCancleClick() {
                Toast.makeText(WithDrawMoneryActivity.this, "取消了～～～", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPwdCompleteListener(String pwd) {
                // TODO: 2017/4/20 调接口，验证密码是否正确    密码正确，返回个TRUE，然后判断
                if (true) {
                    Toast.makeText(WithDrawMoneryActivity.this, "密码 = " + pwd, Toast.LENGTH_SHORT).show();
                } else {
                    //显示密码输入错误的提示框
                }
            }
        });
        keyBoardFragment.show(getFragmentManager(), "keyboard");
    }


}
