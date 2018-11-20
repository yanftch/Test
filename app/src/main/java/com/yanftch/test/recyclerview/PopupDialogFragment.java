package com.yanftch.test.recyclerview;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yanftch.test.R;

/**
 * Created by shadow on 2016/3/8.
 */
public class PopupDialogFragment extends DialogFragment {

    private DialogItemOnClickListener itemOnClickListener;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.popview, null);
        TextView onTopTv = (TextView) view.findViewById(R.id.on_top_tv);
        TextView cancelTv = (TextView) view.findViewById(R.id.cancel_top_tv);

        Bundle bundle = getArguments();
        int isTop = bundle.getInt(RvActivity.TOP_STATES);
        if (isTop == 1) {
            onTopTv.setVisibility(View.GONE);
            cancelTv.setVisibility(View.VISIBLE);
        } else if (isTop == 0) {
            onTopTv.setVisibility(View.VISIBLE);
            cancelTv.setVisibility(View.GONE);
        }

        onTopTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
                itemOnClickListener.onTop();
            }
        });

        cancelTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
                itemOnClickListener.onCancel();
            }
        });


        getDialog().getWindow().requestFeature(STYLE_NO_TITLE);
        setStyle(STYLE_NO_FRAME, android.R.style.Theme_Light);
        setCancelable(true);
        getDialog().getWindow().setBackgroundDrawableResource(R.color.write_bg);
        return view;
    }


    public void setItemOnClickListener(DialogItemOnClickListener itemOnClickListener) {
        this.itemOnClickListener = itemOnClickListener;
    }


    public interface DialogItemOnClickListener {

        void onTop();

        void onCancel();

    }


}
