package com.yanftch.test.calendar.dialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yanftch.test.R;


/**
 * @auth Iven
 * 2017/1/15 10:58
 * @desc 通用的Dialog弹出框
 */

public class DialogFragmentUtils extends DialogFragment {
    private static final String TAG = "dah_DialogFragmentUtils";
    private Dialog mDialog;
    private DialogClickListener eventListener;// 按钮点击回调
    //左按钮、右按钮、标题、内容
    private TextView tv_left, tv_right, dialog_textView_title, dialog_textView_content;
    //对应的文案
    private String leftContent, rightContent, title, content;
    //左右按钮之间的分割线
    private TextView dialog_line;
    private OnKeyListener onKeyListener;
    //右上角关闭
    private ImageView dialog_close;
    private ImageView iv_promotion_icon;//推广图片
    private String imgUrl;//推广图片的链接
    private LinearLayout dialog_layout_button;
    private Context mContext;
    private int leftColor = -1, rightColor = -1;

    public DialogFragmentUtils() {

    }

    /**
     * @param title               Title
     * @param content             content
     * @param leftContent         左边按钮
     * @param rightContent        右边按钮
     * @param dialogClickListener 回调
     */

    @SuppressLint("ValidFragment")
    public DialogFragmentUtils(Context mContext, String title, String content, String leftContent, String rightContent, DialogClickListener dialogClickListener) {
        this.mContext = mContext;
        this.title = title;
        this.content = content;
        this.leftContent = leftContent;
        this.rightContent = rightContent;
        eventListener = dialogClickListener;
    }

    @SuppressLint("ValidFragment")
    public DialogFragmentUtils(Context mContext, String title, String content, String leftContent, String rightContent, String imgUrl, DialogClickListener dialogClickListener) {
        this.mContext = mContext;
        this.title = title;
        this.content = content;
        this.leftContent = leftContent;
        this.rightContent = rightContent;
        this.eventListener = dialogClickListener;
        this.imgUrl = imgUrl;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = getActivity().getLayoutInflater().inflate(R.layout.layout_dialog, null);
        mDialog = new Dialog(getActivity(), R.style.dialog_background_dimEnabled);
        mDialog.setContentView(view);//添加view
        dialogSettings();
        initView(view);
        mDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (onKeyListener != null) {
                    return onKeyListener.onkeyDown(keyCode, event);
                } else
                    return false;
            }
        });
        return mDialog;

    }

    private void initView(View view) {
        dialog_layout_button = (LinearLayout) view.findViewById(R.id.dialog_layout_button);
        iv_promotion_icon = (ImageView) view.findViewById(R.id.iv_promotion_icon);
        tv_left = ((TextView) view.findViewById(R.id.dialog_textView_left));
        tv_right = ((TextView) view.findViewById(R.id.dialog_textView_right));
        dialog_textView_content = ((TextView) view.findViewById(R.id.dialog_textView_content));
        dialog_textView_title = ((TextView) view.findViewById(R.id.dialog_textView_title));
        dialog_line = ((TextView) view.findViewById(R.id.dialog_line));
        dialog_close = ((ImageView) view.findViewById(R.id.dialog_close));
        //左侧按钮点击事件
        tv_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != eventListener) {
                    eventListener.onLeftClick();
                }
                dismiss();
            }
        });
        //右侧按钮点击事件
        tv_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != eventListener) {
                    eventListener.onRightClick();
                }
                dismiss();
            }
        });
        dialog_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != eventListener) {
                    eventListener.onCloseClick();
                }
                dismiss();
            }
        });
        iv_promotion_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != eventListener) {
                    eventListener.onImageClick();
                }
                dismiss();
            }
        });
        //俩按钮
        if (TextUtils.isEmpty(leftContent)) {
            tv_left.setVisibility(View.GONE);
            tv_left.setText("");
            dialog_line.setVisibility(View.GONE);
        } else {
            tv_left.setVisibility(View.VISIBLE);
            tv_left.setText(leftContent);
            dialog_line.setVisibility(View.VISIBLE);
        }

        if (TextUtils.isEmpty(rightContent)) {
            tv_right.setVisibility(View.GONE);
            tv_right.setText("");
            dialog_line.setVisibility(View.GONE);
        } else {
            tv_right.setVisibility(View.VISIBLE);
            tv_right.setText(rightContent);
            dialog_line.setVisibility(View.VISIBLE);
        }
        if (leftColor != -1) {
            tv_left.setTextColor(getResources().getColor(leftColor));
        }
        if (rightColor != -1) {
            tv_right.setTextColor(getResources().getColor(rightColor));
        }
        //广告图处理
        if (!TextUtils.isEmpty(imgUrl)) {
            iv_promotion_icon.setVisibility(View.VISIBLE);
            Picasso.with(mContext).load(imgUrl).into(iv_promotion_icon);
        } else {
            iv_promotion_icon.setVisibility(View.GONE);
        }

        if (TextUtils.isEmpty(leftContent) && TextUtils.isEmpty(rightContent)) {
            dialog_layout_button.setVisibility(View.GONE);
        }

        //标题
        if (TextUtils.isEmpty(title)) {
            dialog_textView_title.setVisibility(View.GONE);
        } else {
            dialog_textView_title.setVisibility(View.VISIBLE);
            dialog_textView_title.setText(title);
        }
        //内容
        if (TextUtils.isEmpty(content)) {
            dialog_textView_content.setVisibility(View.GONE);
        } else {
            dialog_textView_content.setVisibility(View.VISIBLE);
            dialog_textView_content.setText(content);
        }
    }

    /**
     * Dialog相关设置
     */
    private void dialogSettings() {
        Window dialogWindow = mDialog.getWindow();
        dialogWindow.setGravity(Gravity.CENTER);
        WindowManager m = dialogWindow.getWindowManager();
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
        WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        // p.height = (int) (d.getHeight() * 0.42); // 高度
        p.width = (int) (d.getWidth() * 0.8); // 宽度设置为屏幕的0.8
        dialogWindow.setAttributes(p);
        mDialog.setCanceledOnTouchOutside(false);//外部禁止点击
    }

    @Override
    public void onResume() {
        super.onResume();
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

    public interface DialogClickListener {

        /**
         * 左按钮点击事件
         */
        void onLeftClick();

        /**
         * 右按钮点击事件
         */
        void onRightClick();

        /**
         * X号点击回调
         */
        void onCloseClick();

        /**
         * 广告图点击回调
         */
        void onImageClick();
    }

    public interface OnKeyListener {
        boolean onkeyDown(int keyCode, KeyEvent event);
    }

    public void setOnKeyListener(OnKeyListener listener) {
        this.onKeyListener = listener;
    }

    public void setButtonTextColor(int leftColor, int rightColor) {
        this.leftColor = leftColor;
        this.rightColor = rightColor;
    }
}
