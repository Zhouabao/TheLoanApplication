package com.yuzhua.theloanapplication.widgets;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.Spanned;
import android.view.View;
import android.widget.TextView;

import com.yuzhua.theloanapplication.R;

/**
 * @author Zhou Fengmei
 * @create 2018/4/24
 * @Describe
 */
public class CustomerDialog extends Dialog implements View.OnClickListener {
    public TextView message;
    public TextView btnCancel;
    public TextView btnConfirm;
    OnDialogClickListener onDialogClickListener;

    public void setOnDialogClickListener(OnDialogClickListener onDialogClickListener) {
        this.onDialogClickListener = onDialogClickListener;
    }

    public void setMessage(Spanned msg) {
        message.setText(msg);
    }


    public CustomerDialog(@NonNull Context context) {
        super(context, R.style.CustomerDialog);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_service);
        message = findViewById(R.id.tv_message);
        btnCancel = findViewById(R.id.btn_cancel);
        btnConfirm = findViewById(R.id.btn_confirm);
        btnCancel.setOnClickListener(this);
        btnConfirm.setOnClickListener(this);
        setCancelable(true);
        setCanceledOnTouchOutside(false);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_cancel:
                onDialogClickListener.onCancelClick();
                break;
            case R.id.btn_confirm:
                onDialogClickListener.onConfirmClick();
                break;

        }
    }

    public interface OnDialogClickListener {
        void onConfirmClick();

        void onCancelClick();
    }
}
