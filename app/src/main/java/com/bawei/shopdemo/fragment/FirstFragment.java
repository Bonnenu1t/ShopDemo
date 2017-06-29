package com.bawei.shopdemo.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bawei.shopdemo.R;
import com.bawei.shopdemo.activitys.PinDaoActivity;
import com.bawei.shopdemo.adapters.FristFragmentAdapter;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends Fragment {


    @BindView(R.id.first_er_wei_ma)
    ImageView firstErWeiMa;
    @BindView(R.id.first_sou_suo)
    EditText firstSouSuo;
    @BindView(R.id.first_de_lu)
    ImageView firstDeLu;
    Unbinder unbinder;
    private TabLayout frist_tab;
    private ViewPager frist_viewpager;

    public FirstFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_first, container, false);

        initView(view);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    private void initView(View view) {
        frist_tab = (TabLayout) view.findViewById(R.id.frist_tab);
        frist_viewpager = (ViewPager) view.findViewById(R.id.frist_viewpager);

        FristFragmentAdapter adapter = new FristFragmentAdapter(getChildFragmentManager());

        frist_viewpager.setAdapter(adapter);

        frist_tab.setupWithViewPager(frist_viewpager);
        frist_tab.setTabMode(TabLayout.MODE_SCROLLABLE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /**
         * 处理二维码扫描结果
         */
        if (requestCode == REQUEST_CODE) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    Toast.makeText(getActivity(), "解析结果:" + result, Toast.LENGTH_LONG).show();
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(getActivity(), "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public static final int REQUEST_CODE = 111;
    //二维码扫描的点击事件
    @OnClick(R.id.first_er_wei_ma)
    public void onFirstErWeiMaClicked() {
        Intent intent = new Intent(getActivity(), CaptureActivity.class);
        startActivityForResult(intent, REQUEST_CODE);

    }

    //搜索的点击事件
    @OnClick(R.id.first_sou_suo)
    public void onFirstSouSuoClicked() {
        startActivity(new Intent(getActivity(), PinDaoActivity.class));
    }

    //跳转到登录界面的点击事件
    @OnClick(R.id.first_de_lu)
    public void onFirstDeLuClicked() {

    }
}
