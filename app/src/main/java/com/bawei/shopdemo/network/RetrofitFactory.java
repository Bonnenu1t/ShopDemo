package com.bawei.shopdemo.network;

import com.bawei.shopdemo.network.cookie.CookiesManager;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Bonnenu1t丶 on 2017/6/20.
 */

public class RetrofitFactory {


    public static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .cookieJar(new CookiesManager())
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20,TimeUnit.SECONDS)
            .writeTimeout(20,TimeUnit.SECONDS)
            .addInterceptor(new LoggingInterceptor())
            .build();

    public static ApiService apiService = new Retrofit.Builder()
            .baseUrl("http://qbh.2dyt.com")
            .addConverterFactory(ScalarsConverterFactory.create())
            .client(okHttpClient)
            //把 以前的 call 转化成 Observable
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(ApiService.class);


    //自定义get 直接定义Observer方法
    public static void get(String url, Observer<String> observer){
                apiService.get(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public static void get(String url, Map<String,String> map, Observer<String> observer){
                 apiService.get(url,map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public static void post(String url,Map<String,String> map,Observer<String> observer){
                 apiService.post(url,map)
                .subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);

    }
}
