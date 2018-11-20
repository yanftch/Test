package com.yanftch.test.rsa;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.yanftch.test.R;

public class RSAActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "dah_RSAActivity";
    private EditText res_et;
    private TextView tv_show_encode, tv_show_decode;
    private final String PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCfRTdcPIH10gT9f31rQuIInLwe  \n" +
            "7fl2dtEJ93gTmjE9c2H+kLVENWgECiJVQ5sonQNfwToMKdO0b3Olf4pgBKeLThra  \n" +
            "z/L3nYJYlbqjHC3jTjUnZc0luumpXGsox62+PuSGBlfb8zJO6hix4GV/vhyQVCpG  \n" +
            "9aYqgE7zyTRZYX9byQIDAQAB ";
    private final String PRIVATE_KEY = "MIICXQIBAAKBgQCfRTdcPIH10gT9f31rQuIInLwe7fl2dtEJ93gTmjE9c2H+kLVE  \n" +
            "NWgECiJVQ5sonQNfwToMKdO0b3Olf4pgBKeLThraz/L3nYJYlbqjHC3jTjUnZc0l  \n" +
            "uumpXGsox62+PuSGBlfb8zJO6hix4GV/vhyQVCpG9aYqgE7zyTRZYX9byQIDAQAB  \n" +
            "AoGAO9+sYRtKC9xJDfcocfMxv+UT/1ic6EDgcqu6Uzwq+Jvwod9KlXqyQJqCr6T7  \n" +
            "pjfodc3RAZOTx4gCZJverBvz053RH5GawCdocEgaqbXAAWJOhA+9IEU0NUud7ckF  \n" +
            "yDko0QXLoGP9tanrMEt5zMqt8QxDyl6Xcij3mk8rivOgBJECQQDNTO6dZX8xCozc  \n" +
            "Ne0gzC53Gv/KQXANBBHMr7WkKUb2i5+tXkEJ5z3abx2ppEQXDr4AgJH8Gtbm6K7t  \n" +
            "EHV4ov4FAkEAxppD/iiT1/SVQq20be8CsiHpsjTPiestWQWdm1Qn/Y2nAkGkpCFp  \n" +
            "yEdUvVDPtQhRN9EqNggNAnwg5kMvsuwN9QJAfHBhQe4/hk5Kyz+0l+irUW6AFOxN  \n" +
            "KtaIo3TtuK98X/yJsOAstAACMeCgLi9vRjqdWFiWJCVwlU38mZ0cVx8UsQJBALzt  \n" +
            "M5Er+LiPKw5rQCD0JZRfPnkQU/3XgyQUe4Gv5PsHLcCvwXeBcafcc3hEz9JfPyPi  \n" +
            "Dk2oCvg6LPHfKBkFBaECQQCODcKX6DBWiyVxmPaJOOcF63KpCYDPkjeovIUHro1x  \n" +
            "ElR2GrQCC/9Q4C4vruOhBQ+vX8NMPnO6NBy5TLGDwMyc ";
    private static final String DEFAULT_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDosvH1gCpQTTZLXGMcSeeqDjWuDVY0+Aab1VbtGJqWdkPd32D4hEUwFjVJ+FJbq7UpvFFDQ3k2y2n/1rzxWapFk/e+BNNCSKP9e6+Of1SLs83So27dgiAeAKmdQoxwfXrgvP1/QRMJJ0i6m3CRRyTlXO+cMGbYqRv1iTT9uaRolQIDAQAB";
    private static final String DEFAULT_PRIVATE_KEY = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAOiy8fWAKlBNNktcYxxJ56oONa4NVjT4BpvVVu0YmpZ2Q93fYPiERTAWNUn4UlurtSm8UUNDeTbLaf/WvPFZqkWT974E00JIo/17r45/VIuzzdKjbt2CIB4AqZ1CjHB9euC8/X9BEwknSLqbcJFHJOVc75wwZtipG/WJNP25pGiVAgMBAAECgYEAv4PXY8hyCtkhYHDPGU8yHWHIiFFtq/ad6c9x1X00bbU0Mf1Q3/hswSDmBtUbY1s0pP7amtODhbdwrCFeK/0yBrOegb2fQeJs/QL6/y4/DPzRB21k9N8cQjgmv5tQb72fwdY8nDROXnzKQceMo6b/xkWaIhvhdUq6nCqPvoIGRIECQQD+lOKTQk769G9BQd7HW+2H2NioPbxri+V27daC1M5uBfBj8Wt3NDJ5IyMvOHz5yTlm8FsE2Zz1/aFdLJ/Rv4IRAkEA6f7ZOMcuxlRsAiN708+r3q3sxAyBood+qAJ1MKhOrdR94RcAPUkcjFTZ8j1v0eclj6+w2RChcpb5Ath93ia6RQJBAP3b6x+axHUcn4A8NfEn6vFGu6zwet3nT3bLbddia0JtK6wNhfMFGruO3TvuITlXfaT3UlvAv/LP6kOmBuw6AnECQQDR3r29awjM4ZMuJ908EJs6Ugx1mjH7MEOtNOcfCRXoWxm79QFF9nkgdEo2NlxAi2zo/s9DIONs/3O/1aSux1VxAkBkkOdc0f2ogWZHqtCYfVfYjwbMvlW/6lnbq0B76V1SVqogoSubwnF7EUBdmqpzWmzqM4xURBh9QqDnUUfBzPMW";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rsa);
        res_et = (EditText) findViewById(R.id.res_et);
        findViewById(R.id.btn_encode).setOnClickListener(this);
        findViewById(R.id.btn_decode).setOnClickListener(this);
        tv_show_encode = (TextView) findViewById(R.id.tv_show_encode);
        tv_show_decode = (TextView) findViewById(R.id.tv_show_decode);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_encode://加密
                String pwd = res_et.getText().toString().trim();
                try {
                    test();
                } catch (Exception e) {
                    e.printStackTrace();
                }
//                try {
//                    PublicKey publicKey = RSAUtils.loadPublicKey(PUBLIC_KEY);
//                    //加密
//                    byte[] encryptByte = RSAUtils.encryptData(pwd.getBytes(), publicKey);
//                    String afterPwd = new String(encryptByte);
//                    Log.e(TAG, "onClick: 加密之后的密码是 === " + afterPwd);
//                    tv_show_decode.setText(afterPwd);
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
                break;
            case R.id.btn_decode://解密
                break;
        }
    }

    private void test() throws Exception {
        System.err.println("公钥加密——私钥解密");
        String source = "123456";
        Log.e(TAG, "\r加密前文字：\r\n" + source);
        byte[] data = source.getBytes();
        byte[] encodedData = RSAUtils.encryptByPublicKey(data, DEFAULT_PUBLIC_KEY);
        Log.e(TAG, "加密后文字：\r\n" + new String(encodedData));
        byte[] decodedData = RSAUtils.decryptByPrivateKey(encodedData,
                DEFAULT_PRIVATE_KEY);
        String target = new String(decodedData);
        Log.e(TAG, "解密后文字: \r\n" + target);
    }
}
