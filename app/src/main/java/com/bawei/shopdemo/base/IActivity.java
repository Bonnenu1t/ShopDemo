package com.bawei.shopdemo.base;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.bawei.shopdemo.R;

public class IActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_i);
    }

}
