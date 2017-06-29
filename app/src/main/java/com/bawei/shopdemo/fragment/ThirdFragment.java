package com.bawei.shopdemo.fragment;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bawei.shopdemo.R;
import com.bawei.shopdemo.adapters.ThirdFragmentAdapter;
import com.bawei.shopdemo.base.BaseMvpFragment;
import com.bawei.shopdemo.bean.ShopBean;
import com.bawei.shopdemo.presenter.ThirdFragmentPresenter;
import com.bawei.shopdemo.utils.StringUtils;
import com.bawei.shopdemo.view.ThirdFragmentView;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class ThirdFragment extends BaseMvpFragment<ThirdFragmentView,ThirdFragmentPresenter> {


    @BindView(R.id.third_recycle)
    RecyclerView thirdRecycle;
    @BindView(R.id.third_text)
    TextView thirdText;
    @BindView(R.id.third_totalprice)
    TextView thirdTotalprice;
    @BindView(R.id.third_totalnum)
    TextView thirdTotalnum;
    @BindView(R.id.third_submit)
    TextView thirdSubmit;
    @BindView(R.id.third_linear)
    LinearLayout thirdLinear;
    Unbinder unbinder;

    //存放购物车中的所有商品
    private List<ShopBean.OrderDataBean.CartlistBean> shopList = new ArrayList<ShopBean.OrderDataBean.CartlistBean>();

    @Override
    public ThirdFragmentPresenter initPresenter() {
        return new ThirdFragmentPresenter();
    }

    public ThirdFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_third, container, false);
        unbinder = ButterKnife.bind(this, view);

        showData();
        return view;
    }

    private void showData() {
        //线性布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        //创建RecyclerView得适配器
        ThirdFragmentAdapter adapter = new ThirdFragmentAdapter(getActivity());
        thirdRecycle.setAdapter(adapter);
        //设置布局管理器
        thirdRecycle.setLayoutManager(layoutManager);

        try {
            InputStream inputStream = getActivity().getAssets().open("shop.json");
            String string = StringUtils.convertStreamToString(inputStream);
            Gson gson = new Gson();
            ShopBean shopBean = gson.fromJson(string, ShopBean.class);

            for (int i = 0; i < shopBean.getOrderData().size(); i++) {
                //length 得到每一个商品类型中的所有种类的长度
                int length = shopBean.getOrderData().get(i).getCartlist().size();
                for (int g = 0; g < length; g++) {
                    shopList.add(shopBean.getOrderData().get(i).getCartlist().get(g));
                }
            }
            setFirstState(shopList);

            adapter.setData(shopList);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //删除数据回调
        adapter.setOnDeleteClickListener(new ThirdFragmentAdapter.OnDeleteClickListener() {
            @Override
            public void onDeleteClick(View view, int position, int cartid) {

            }
        });


        //
        adapter.setOnRefershListener(new ThirdFragmentAdapter.OnRefershListener() {
            @Override
            public void onRefersh(boolean isSelect,List<ShopBean.OrderDataBean.CartlistBean> list) {

                //标记底部 全选按钮
                if(isSelect){
                    Drawable left = getResources().getDrawable(R.drawable.cart1_checkbox_check);
                    thirdText.setCompoundDrawablesWithIntrinsicBounds(left,null,null,null);
                }else {
                    Drawable left = getResources().getDrawable(R.drawable.cart1_checkbox_normal);
                    thirdText.setCompoundDrawablesWithIntrinsicBounds(left,null,null,null);
                }

                //总价
                float mTotlaPrice = 0f;
                int mTotalNum = 0;
                for(int i=0;i<list.size();i++){
                    if(list.get(i).isSelect()){
                        mTotlaPrice += list.get(i).getPrice() * list.get(i).getCount() ;
                        mTotalNum += list.get(i).getCount();
                    }
                }
                System.out.println("mTotlaPrice = " + mTotlaPrice);

                thirdTotalprice.setText("总价 : " + mTotlaPrice  );

                thirdTotalnum.setText("共" + mTotalNum + "件商品");
            }
        });
    }


    //通过定义的isFirst 来判断商户的名称是否隐藏 或 显示 （1 为显示   2 为隐藏）
    public static void setFirstState(List<ShopBean.OrderDataBean.CartlistBean> list){
        if (list.size()>0){
            //当为第一条数据的时候显示商户名称
            list.get(0).setIsFirst(1);
            for (int i = 1; i < list.size(); i++) {
                    //当有多条数据时 对商户的id进行判断是否为同一商户
                if (list.get(i).getShopId() == list.get(i-1).getShopId()) {
                    //为同一商户时  商户名称进行隐藏
                    list.get(i).setIsFirst(2);
                }else {
                    //不为同一商户 商户名称显示
                    list.get(i).setIsFirst(1);
                }
            }
        }
    }




    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
