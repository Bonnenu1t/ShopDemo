package com.bawei.shopdemo.base;

/**
 * Created by Bonnenu1t丶 on 2017/6/19.
 *
 * 定义一个抽象类   并定义两个方法
 */

public abstract class BasePresenter<T> {

    //定义一个泛型T
    public T view;

    //当前View 的构造方法
    public void attach(T view){
        this.view = view;
    }

    //当调用detach 时把当前View 滞空
    public void detach(){
        this.view = null;
    }

}
