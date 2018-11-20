package com.yanftch.test.finger;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Dialog;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyPermanentlyInvalidatedException;
import android.security.keystore.KeyProperties;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.DialogFragment;
import android.support.v4.hardware.fingerprint.FingerprintManagerCompat;
import android.support.v4.os.CancellationSignal;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
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

/**
 * User : yanftch
 * Date : 2018/10/26
 * Time : 11:58
 * Desc :https://juejin.im/entry/59e56f845188257f6970aab0
 *
 *
 *
 */
public class FingerDialog extends DialogFragment {
    private static final String TAG = "debug_FingerDialog";
    private Dialog mDialog;
    private TextView text, cancel;
    private TextView desc;
    private ImageView icon;

    public interface CallBack {
        void onCallback();

        void usePasswordCallback();
    }

    public CallBack mCallBack;
    private Context mContext;


    private FingerprintManagerCompat fingerprintManagerCompat;
    private CancellationSignal mCancellationSignal;
    private boolean isSelfCancelled;


    @SuppressLint("ValidFragment")
    public FingerDialog(Context context, CallBack callBack) {
        mContext = context;
        mCallBack = callBack;
    }

    public FingerDialog() {
    }
//    private boolean hardwareDetected;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(mContext).inflate(com.yanftch.test.R.layout.dialog_fingerprint_authenticate, null, false);
        mDialog = new Dialog(mContext, com.yanftch.test.R.style.dialog_background_dimEnabled);
        mDialog.setContentView(view);

        dialogSettings();
        initView(view);
        mDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (KeyEvent.KEYCODE_BACK == event.getKeyCode()) {
                    return true;
                }
                return false;
            }
        });
        return mDialog;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void initView(View view) {
        icon = (ImageView) view.findViewById(R.id.icon_fingerprint);
        desc = (TextView) view.findViewById(R.id.fingerprint_desc);
        text = (TextView) view.findViewById(R.id.fingerprint_text);
        cancel = (TextView) view.findViewById(R.id.tv_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDialog.isShowing()) {
                    mDialog.dismiss();
                }
            }
        });
        FingerprintManagerCompat.CryptoObject cryptoObject = new FingerprintManagerCompat.CryptoObject(mCipher);
        mCancellationSignal = new CancellationSignal();
        fingerprintManagerCompat = FingerprintManagerCompat.from(mContext);

        fingerprintManagerCompat.authenticate(cryptoObject, 100, mCancellationSignal, new FingerprintManagerCompat.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errMsgId, CharSequence errString) {
                super.onAuthenticationError(errMsgId, errString);
                Toast.makeText(mContext, "onAuthenticationError", Toast.LENGTH_SHORT).show();
                Log.e(TAG, "onAuthenticationError: errMsgId===" + errMsgId);
                dismiss();
                if (null != mCallBack) {
                    mCallBack.usePasswordCallback();
                }
            }

            @Override
            public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
                super.onAuthenticationHelp(helpMsgId, helpString);
                Toast.makeText(mContext, "onAuthenticationHelp", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAuthenticationSucceeded(FingerprintManagerCompat.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                Toast.makeText(mContext, "onAuthenticationSucceeded", Toast.LENGTH_SHORT).show();
                if (mCallBack != null) {
                    mCallBack.onCallback();
                }
                text.setVisibility(View.GONE);
                dismiss();
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                Log.e(TAG, "onAuthenticationFailed: ");
                Toast.makeText(mContext, "onAuthenticationFailed", Toast.LENGTH_SHORT).show();
                text.setVisibility(View.VISIBLE);
                text.setText("识别失败，再试一次呗~");
            }
        }, null);
    }

    private void dialogSettings() {
        Window dialogWindow = mDialog.getWindow();
        if (null != dialogWindow) {
            // TODO: 2017/4/17  添加出现动画效果
            dialogWindow.setWindowAnimations(R.style.dialogWindowAnim); //设置窗口弹出动画
            dialogWindow.setGravity(Gravity.CENTER);
            WindowManager m = dialogWindow.getWindowManager();
            Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
            WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值
            p.width = (int) (d.getWidth() * 0.8); // 宽度设置为与屏幕同宽
//            p.height = (int) (d.getHeight() * 0.6);//高度设置为全屏~~~~~~~~~~~
            dialogWindow.setAttributes(p);
            mDialog.setCanceledOnTouchOutside(false);//外部禁止点击
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mDialog != null) {
            dismiss();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mDialog != null) {
            dismiss();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
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
