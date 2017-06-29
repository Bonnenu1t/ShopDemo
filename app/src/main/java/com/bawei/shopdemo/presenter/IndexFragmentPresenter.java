package com.bawei.shopdemo.presenter;

import com.bawei.shopdemo.base.BasePresenter;
import com.bawei.shopdemo.bean.IndexBean;
import com.bawei.shopdemo.model.IndexFragmentModel;
import com.bawei.shopdemo.model.IndexFragmentModelImpl;
import com.bawei.shopdemo.view.IndexFragmentView;

/**
 * Created by Bonnenu1t丶 on 2017/6/21.
 */

public class IndexFragmentPresenter extends BasePresenter<IndexFragmentView>{

    private IndexFragmentModelImpl indexFragmentModelImpl;

    public IndexFragmentPresenter() {
        indexFragmentModelImpl = new IndexFragmentModelImpl();
    }


    //获取数据

    public void getData(int pos){

        indexFragmentModelImpl.getData(pos, new IndexFragmentModel.IndexFragmentModelListener() {
            @Override
            public void onSuccess(IndexBean indexBean) {
                view.onSuccess(indexBean);
            }

            @Override
            public void onFailed(int code) {

                view.onFailed(code);
            }
        });

    }

}
