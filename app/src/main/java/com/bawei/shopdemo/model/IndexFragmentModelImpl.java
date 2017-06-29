package com.bawei.shopdemo.model;

import com.bawei.shopdemo.bean.IndexBean;
import com.bawei.shopdemo.network.BaseObserver;
import com.bawei.shopdemo.network.RetrofitFactory;
import com.google.gson.Gson;

/**
 * Created by Bonnenu1t丶 on 2017/6/21.
 * 定义接口链接 并解析数据
 */

public class IndexFragmentModelImpl implements IndexFragmentModel{

    private String [] url = {"http://lib.suning.com/app/redbaby/80000_8.2.0-155.json",
            "http://lib.suning.com/app/redbaby/babydiapers-64.json",
            "http://lib.suning.com/app/redbaby/babymilk-41.json",
            "http://lib.suning.com/app/redbaby/BabyBottles-56.json",
            "http://lib.suning.com/app/redbaby/babytoys-50.json",
            "http://lib.suning.com/app/redbaby/babyclothes-49.json",
            "http://lib.suning.com/app/redbaby/babybooks-39.json"} ;

    //pos 为定义的url 中 String数组 的接口地址链接
    @Override
    public void getData(int pos, final IndexFragmentModelListener listener) {

        RetrofitFactory.get(url[pos], new BaseObserver() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                IndexBean indexBean = gson.fromJson(result, IndexBean.class);

                listener.onSuccess(indexBean);
            }

            @Override
            public void onFailed(int code) {
                listener.onFailed(code);
            }
        });
    }
}
