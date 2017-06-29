package com.bawei.shopdemo.network;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by Bonnenu1t丶 on 2017/6/20.
 */

public abstract class BaseObserver implements Observer<String>{

    @Override
    public void onSubscribe(@NonNull Disposable d) {

    }

    @Override
    public void onNext(@NonNull String s) {

        onSuccess(s);
    }

    @Override
    public void onError(@NonNull Throwable e) {

        onFailed(0);
    }

    @Override
    public void onComplete() {

    }

    //定义一个成功的抽象方法
    public abstract void onSuccess(String result);
    //定义一个失败的抽象方法
    public abstract void onFailed(int code);
}
