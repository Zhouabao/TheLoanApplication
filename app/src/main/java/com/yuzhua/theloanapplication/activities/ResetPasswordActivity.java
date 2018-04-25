package com.yuzhua.theloanapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.yuzhua.theloanapplication.R;
import com.yuzhua.theloanapplication.widgets.CustomerDialog;
import com.yuzhua.universalinvinciblesdk.base.BaseActivity;
import com.yuzhua.universalinvinciblesdk.util.RegexUtils;
import com.yuzhua.universalinvinciblesdk.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ResetPasswordActivity extends BaseActivity {

    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_authcode)
    EditText etAuthcode;
    @BindView(R.id.ll_role)
    LinearLayout llRole;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.et_confirm_pwd)
    EditText etConfirmPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        setData();

    }

    private void setData() {
        setTitle("重置密码");
        llRole.setVisibility(View.GONE);
        etAuthcode.setVisibility(View.GONE);

    }

    @OnClick({R.id.btn_cannot_code, R.id.btn_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_cannot_code:
                showMessageDialog();
                break;
            case R.id.btn_confirm:
                login();
                break;
        }
    }

    private void login() {
        if (TextUtils.isEmpty(etPhone.getText().toString())) {
            ToastUtil.toastWarning(this, "手机号码不能为空！", false);
            return;
        } else if (RegexUtils.isMobileSimple(etPhone.getText().toString())) {
            ToastUtil.toastWarning(this, "手机号码格式不正确！", false);
            return;
        }
        if (TextUtils.isEmpty(etPassword.getText().toString())) {
            ToastUtil.toastWarning(this, "密码不能为空！", false);
            return;
        }
        if (TextUtils.isEmpty(etConfirmPwd.getText().toString())) {
            ToastUtil.toastWarning(this, "再次确认密码不能为空！", false);
            return;
        }
        if (!etConfirmPwd.getText().toString().equals(etPassword.getText().toString())) {
            ToastUtil.toastWarning(this, "两次密码输入不一致！", false);
            return;
        }
        startActivity(new Intent(ResetPasswordActivity.this, MainActivity.class));
    }

    CustomerDialog dialog;

    private void showMessageDialog() {
        if (dialog == null) {
            dialog = new CustomerDialog(this);
        }
        dialog.show();
        dialog.message.setText(Html.fromHtml(getString(R.string.edit_message)));
        dialog.btnCancel.setText("稍后发送");
        dialog.btnConfirm.setText("现在发送");
        dialog.setOnDialogClickListener(new CustomerDialog.OnDialogClickListener() {
            @Override
            public void onConfirmClick() {
                ToastUtil.toastSuccess(ResetPasswordActivity.this, "发送成功！", true);
                dialog.dismiss();
            }

            @Override
            public void onCancelClick() {
                dialog.dismiss();
            }
        });

    }
}
