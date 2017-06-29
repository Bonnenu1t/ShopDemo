package com.bawei.shopdemo.model;

import com.bawei.shopdemo.bean.IndexBean;

/**
 * Created by Bonnenu1t丶 on 2017/6/21.
 *
 * 定义model接口  自定义IndexFragmentModelListener 的接口连接
 * 使之与IndexFragmentPresenter 产生关联
 */

public interface IndexFragmentModel {

    public void getData(int pos,IndexFragmentModelListener listener);


    public interface IndexFragmentModelListener{

        //通过传递bean对象
        public void onSuccess(IndexBean indexBean);

        //定义失败code 来表示哪步失败
        public void onFailed(int code);
    }
}
