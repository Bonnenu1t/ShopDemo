package com.bawei.shopdemo.view;

import com.bawei.shopdemo.bean.IndexBean;

/**
 * Created by Bonnenu1t丶 on 2017/6/21.
 */

public interface IndexFragmentView {

    public void onSuccess(IndexBean indexBean);

    public void onFailed(int code);
}
