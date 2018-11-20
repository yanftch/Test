package com.yanftch.test.choice;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.yanftch.test.R;


public class BottomBoard extends DialogFragment {
    private Dialog mDialog;
    private DialogClickListener eventListener;// 按钮点击回调
    private OnKeyListener onKeyListener;
    private TextView tv_left_top;
    private TextView tv_right_cancle;

    public BottomBoard() {

    }

    @SuppressLint("ValidFragment")
    public BottomBoard(DialogClickListener dialogClickListener) {
        eventListener = dialogClickListener;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = getActivity().getLayoutInflater().inflate(R.layout.layout_dialog_bottom_board, null);
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

    //初始化
    private void initView(final View view) {
        tv_left_top = (TextView) view.findViewById(R.id.tv_left_top);//
        tv_right_cancle = (TextView) view.findViewById(R.id.tv_right_cancle);


        tv_left_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != eventListener) {
                    eventListener.onLeftTopEvent();
                }
            }
        });
        tv_right_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != eventListener) {
                    eventListener.onRightCancleEvent();
                }
            }
        });
    }

    /**
     * Dialog相关设置
     */
    private void dialogSettings() {
        Window dialogWindow = mDialog.getWindow();
        if (null != dialogWindow) {
            // TODO: 2017/4/17  添加出现动画效果
//            dialogWindow.setWindowAnimations(R.style.dialogWindowAnim); //设置窗口弹出动画
            dialogWindow.setGravity(Gravity.BOTTOM);
            WindowManager m = dialogWindow.getWindowManager();
            Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
            WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值
            p.width = d.getWidth(); // 宽度设置为与屏幕同宽
            dialogWindow.setAttributes(p);
            mDialog.setCanceledOnTouchOutside(true);//外部必须可以点击
            mDialog.setCancelable(false);
        }
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
        void onLeftTopEvent();

        void onRightCancleEvent();

    }

    public interface OnKeyListener {
        boolean onkeyDown(int keyCode, KeyEvent event);
    }

    public void setOnKeyListener(OnKeyListener listener) {
        this.onKeyListener = listener;
    }

}
