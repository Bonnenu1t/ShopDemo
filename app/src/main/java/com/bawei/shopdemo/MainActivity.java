package com.bawei.shopdemo;

import android.content.Intent;
import android.os.Bundle;

import com.bawei.shopdemo.activitys.TabActivity;
import com.bawei.shopdemo.base.BaseMvpActivity;
import com.bawei.shopdemo.presenter.MainPresenter;
import com.bawei.shopdemo.view.MainView;

public class MainActivity extends BaseMvpActivity<MainView,MainPresenter> {

    @Override
    public MainPresenter initPresenter() {
        return new MainPresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter.getData();

        startActivity(new Intent(this, TabActivity.class));


        finish();
    }


}
