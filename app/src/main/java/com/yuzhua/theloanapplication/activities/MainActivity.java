package com.yuzhua.theloanapplication.activities;

import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;

import com.yuzhua.theloanapplication.R;
import com.yuzhua.theloanapplication.widgets.CustomerDialog;
import com.yuzhua.universalinvinciblesdk.util.ToastUtil;

public class MainActivity extends AppCompatActivity {

    private CustomerDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

}
