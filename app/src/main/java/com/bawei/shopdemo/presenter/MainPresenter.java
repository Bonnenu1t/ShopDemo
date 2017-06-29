package com.bawei.shopdemo.presenter;

import com.bawei.shopdemo.base.BasePresenter;
import com.bawei.shopdemo.model.MainModelImpl;
import com.bawei.shopdemo.view.MainView;

/**
 * Created by Bonnenu1t丶 on 2017/6/19.
 */

public class MainPresenter extends BasePresenter<MainView>{

    public MainModelImpl mainModel;

    public MainPresenter(){

        mainModel = new MainModelImpl();

    }

    public void getData(){

        mainModel.getData();

    }

}
