package com.bawei.shopdemo.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.bawei.shopdemo.R;
import com.bawei.shopdemo.base.BaseMvpFragment;
import com.bawei.shopdemo.presenter.LaMaFragmentPresenter;
import com.bawei.shopdemo.view.LaMaFragmentView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class LaMaFragment extends BaseMvpFragment<LaMaFragmentView,LaMaFragmentPresenter> {


    @BindView(R.id.webView)
    WebView webView;
    Unbinder unbinder;
    private WebView webView1;

    @Override
    public LaMaFragmentPresenter initPresenter() {
        return new LaMaFragmentPresenter();
    }

    public LaMaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_la_ma, container, false);
        unbinder = ButterKnife.bind(this, view);
        //webView1 = (WebView) view.findViewById(R.id.webView);


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        webView.loadUrl("http://c.m.suning.com/group_index.html");

        //webView1.loadUrl("http://c.m.suning.com/group_index.html");
        webView.getSettings().setDefaultTextEncodingName("GBK");
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);//设置js可以直接打开窗口，如window.open()，默认为false
        webView.getSettings().setJavaScriptEnabled(true);//是否允许执行js，默认为false。设置true时，会提醒可能造成XSS漏洞
        webView.getSettings().setSupportZoom(true);//是否可以缩放，默认true
        webView.getSettings().setBuiltInZoomControls(true);//是否显示缩放按钮，默认false
        webView.getSettings().setUseWideViewPort(true);//设置此属性，可任意比例缩放。大视图模式
        webView.getSettings().setLoadWithOverviewMode(true);//和setUseWideViewPort(true)一起解决网页自适应问题
        webView.getSettings().setAppCacheEnabled(true);//是否使用缓存
        webView.getSettings().setDomStorageEnabled(true);//DOM Storage
        // webView.getSettings().setUserAgentString("User-Agent:Android");//设置用户代理，一般不用

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
