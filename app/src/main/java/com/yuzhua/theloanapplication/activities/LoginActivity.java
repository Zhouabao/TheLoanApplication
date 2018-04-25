package com.yuzhua.theloanapplication.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.yuzhua.theloanapplication.Constants;
import com.yuzhua.theloanapplication.R;
import com.yuzhua.universalinvinciblesdk.Constant;
import com.yuzhua.universalinvinciblesdk.base.BaseActivity;
import com.yuzhua.universalinvinciblesdk.util.RegexUtils;
import com.yuzhua.universalinvinciblesdk.util.SharedPreferenceUtil;
import com.yuzhua.universalinvinciblesdk.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements CompoundButton.OnCheckedChangeListener {

    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.cb_remember_pwd)
    CheckBox cbRememberPwd;
    SharedPreferenceUtil sharedPreferenceUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        setTitle("登录");
        sharedPreferenceUtil = SharedPreferenceUtil.instance(this);
        if (sharedPreferenceUtil.getString(Constants.PASSWORD) != null && sharedPreferenceUtil.getBoolean(Constants.ISLOGIN, false)) {
            etPassword.setText(sharedPreferenceUtil.getString(Constants.PASSWORD));
        }
        cbRememberPwd.setOnCheckedChangeListener(this);
    }

    @OnClick({R.id.btn_register, R.id.btn_login, R.id.btn_forget_pwd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_register:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
            case R.id.btn_login:
                login();
                break;
            case R.id.btn_forget_pwd:
                startActivity(new Intent(LoginActivity.this, ResetPasswordActivity.class));
                break;
        }
    }

    private void login() {
        if (TextUtils.isEmpty(etPhone.getText().toString())) {
            ToastUtil.toastWarning(this, "手机号码不能为空！", false);
            return;
        } else if (!RegexUtils.isMobileSimple(etPhone.getText().toString())) {
            ToastUtil.toastWarning(this, "手机号码格式不正确！", false);
            return;
        }

        if (TextUtils.isEmpty(etPassword.getText().toString())) {
            ToastUtil.toastWarning(this, "密码不能为空！", false);
            return;
        }

        sharedPreferenceUtil.saveBoolean(Constants.ISLOGIN, true);
        if (sharedPreferenceUtil.getBoolean(Constants.ISAUTH, false))
            startActivity(new Intent(LoginActivity.this, AuthenticationActivity.class));
        else
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();
    }

    /**
     * @param buttonView
     * @param isChecked
     */
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            sharedPreferenceUtil.saveString("password", etPassword.getText().toString());
        }
    }
}
