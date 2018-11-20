package com.yanftch.test.finger;

import android.annotation.TargetApi;
import android.app.KeyguardManager;
import android.content.DialogInterface;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyPermanentlyInvalidatedException;
import android.security.keystore.KeyProperties;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.yanftch.test.R;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class FingerActivity extends AppCompatActivity {
    private static final String TAG = "debug_FingerActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finger);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void btnFinger(View view) {
        switch (view.getId()) {
            case R.id.btn_check:
                FingerDialog fingerDialog = new FingerDialog(this, new FingerDialog.CallBack() {
                    @Override
                    public void onCallback() {
                        Log.e(TAG, "onCallback: Activity 里边回调了");

                    }

                    @Override
                    public void usePasswordCallback() {
                        Log.e(TAG, "usePasswordCallback: 去输入密码去吧！！！！");
                        final EditText et = new EditText(FingerActivity.this);

                        new AlertDialog.Builder(FingerActivity.this).setTitle("搜索")
                                .setIcon(android.R.drawable.ic_dialog_info)
                                .setView(et)
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        String input = et.getText().toString();
                                        if (TextUtils.isEmpty(input)) {
                                            Toast.makeText(getApplicationContext(), "内容不能为空！" + input, Toast.LENGTH_LONG).show();
                                        }
                                        else {

                                        }
                                    }
                                })
                                .setNegativeButton("取消", null)
                                .show();

                    }
                });
                fingerDialog.show(getSupportFragmentManager(),"123");
//                FingerprintManagerCompat fingerprintManagerCompat = FingerprintManagerCompat.from(this);
//                boolean hardwareDetected = fingerprintManagerCompat.isHardwareDetected();//判断是否支持指纹识别
//                Log.e(TAG, "btnFinger: hardwareDetected=" + hardwareDetected);
//                if (hardwareDetected) {
//                    FingerprintManagerCompat.CryptoObject cryptoObject = new FingerprintManagerCompat.CryptoObject(mCipher);
//
//                    fingerprintManagerCompat.authenticate(cryptoObject, 100, null, new FingerprintManagerCompat.AuthenticationCallback() {
//                        @Override
//                        public void onAuthenticationError(int errMsgId, CharSequence errString) {
//                            super.onAuthenticationError(errMsgId, errString);
//                            Toast.makeText(FingerActivity.this, "onAuthenticationError", Toast.LENGTH_SHORT).show();
//                        }
//
//                        @Override
//                        public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
//                            super.onAuthenticationHelp(helpMsgId, helpString);
//                            Toast.makeText(FingerActivity.this, "onAuthenticationHelp", Toast.LENGTH_SHORT).show();
//
//                        }
//
//                        @Override
//                        public void onAuthenticationSucceeded(FingerprintManagerCompat.AuthenticationResult result) {
//                            super.onAuthenticationSucceeded(result);
//                            Toast.makeText(FingerActivity.this, "onAuthenticationSucceeded", Toast.LENGTH_SHORT).show();
//
//                        }
//
//                        @Override
//                        public void onAuthenticationFailed() {
//                            super.onAuthenticationFailed();
//                            Toast.makeText(FingerActivity.this, "onAuthenticationFailed", Toast.LENGTH_SHORT).show();
//
//                        }
//                    },null);
//                }
                break;

        }
    }


    /***------------------------------------------------------------------------------------------------------------------------*/
    private KeyguardManager mKeyguardManager;
    private FingerprintManager mFingerprintManager;
    private static final String DIALOG_FRAGMENT_TAG = "myFragment";
    private static final String SECRET_MESSAGE = "Very secret message";
    public static boolean isAuthenticating = false;
    public static final String PARAM_DISMISS_DIALOG = "param_dismiss_dialog";
    /**
     * Alias for our key in the Android Key Store
     */
    private static final String KEY_NAME = "my_key";
    private KeyStore mKeyStore;
    private KeyGenerator mKeyGenerator;
    private Cipher mCipher;

    @TargetApi(Build.VERSION_CODES.M)
    private boolean initCipher() {
        try {
            mCipher = Cipher.getInstance(KeyProperties.KEY_ALGORITHM_AES + "/"
                    + KeyProperties.BLOCK_MODE_CBC + "/"
                    + KeyProperties.ENCRYPTION_PADDING_PKCS7);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            throw new RuntimeException("Failed to get an instance of Cipher", e);
        }
        try {
            mKeyStore.load(null);
            SecretKey key = (SecretKey) mKeyStore.getKey(KEY_NAME, null);
            mCipher.init(Cipher.ENCRYPT_MODE, key);
            return true;
        } catch (KeyPermanentlyInvalidatedException e) {
            return false;
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (KeyStoreException | UnrecoverableKeyException | IOException
                | NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException("Failed to init Cipher", e);
        }
        return false;
    }

    /**
     * Creates a symmetric key in the Android Key Store which can only be used after the user has
     * authenticated with fingerprint.
     */
    @TargetApi(Build.VERSION_CODES.M)
    public void createKey() {
        // The enrolling flow for fingerprint. This is where you ask the user to set up fingerprint
        // for your flow. Use of keys is necessary if you need to know if the set of
        // enrolled fingerprints has changed.
        mKeyStore = null;
        mKeyGenerator = null;
        try {
            mKeyStore = KeyStore.getInstance("AndroidKeyStore");
        } catch (KeyStoreException e) {
            throw new RuntimeException("Failed to get an instance of KeyStore", e);
        }
        try {
            mKeyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore");
        } catch (NoSuchAlgorithmException | NoSuchProviderException e) {
            throw new RuntimeException("Failed to get an instance of KeyGenerator", e);
        }
        try {
            mKeyStore.load(null);
            // Set the alias of the entry in Android KeyStore where the key will appear
            // and the constrains (purposes) in the constructor of the Builder
            mKeyGenerator.init(new KeyGenParameterSpec.Builder(KEY_NAME,
                    KeyProperties.PURPOSE_ENCRYPT |
                            KeyProperties.PURPOSE_DECRYPT)
                    .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
                    // Require the user to authenticate with a fingerprint to authorize every use
                    // of the key
                    .setUserAuthenticationRequired(true)
                    .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7)
                    .build());
            mKeyGenerator.generateKey();
        } catch (NoSuchAlgorithmException | InvalidAlgorithmParameterException
                | CertificateException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
