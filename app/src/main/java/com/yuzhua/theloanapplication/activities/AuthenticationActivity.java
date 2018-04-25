package com.yuzhua.theloanapplication.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.yuzhua.theloanapplication.R;
import com.yuzhua.universalinvinciblesdk.base.BaseActivity;

public class AuthenticationActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);
        setTitle("认证中心");

    }

}
