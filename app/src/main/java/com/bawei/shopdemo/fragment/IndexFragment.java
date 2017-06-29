package com.bawei.shopdemo.fragment;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bawei.shopdemo.R;
import com.bawei.shopdemo.adapters.IndexFragmentAdapter;
import com.bawei.shopdemo.base.BaseMvpFragment;
import com.bawei.shopdemo.bean.IndexBean;
import com.bawei.shopdemo.presenter.IndexFragmentPresenter;
import com.bawei.shopdemo.view.IndexFragmentView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class IndexFragment extends BaseMvpFragment<IndexFragmentView, IndexFragmentPresenter> implements IndexFragmentView {

    private static final String ARG_PARAM1 = "param1";
    @BindView(R.id.recycler_id)
    RecyclerView recyclerId;
    Unbinder unbinder;
    private int mParam1;

    //private List<IndexBean.DataBean> list = new ArrayList<IndexBean.DataBean>();
    private IndexFragmentAdapter adapter;
    private LinearLayoutManager layoutManager;


    @Override
    public IndexFragmentPresenter initPresenter() {
        return new IndexFragmentPresenter();
    }

    public IndexFragment() {

    }


    public static IndexFragment newInstance(int param1) {
        IndexFragment fragment = new IndexFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getInt(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_index, container, false);


        unbinder = ButterKnife.bind(this, view);
        initView(view);
        return view;
    }

    private void initView(View view) {
        //recycler_id = (RecyclerView) view.findViewById(R.id.recycler_id);

        adapter = new IndexFragmentAdapter(getActivity());

        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        recyclerId.setLayoutManager(layoutManager);
        recyclerId.setAdapter(adapter);

        presenter.getData(mParam1);

    }

    @Override
    public void onSuccess(IndexBean indexBean) {

        //移除空的数据
        indexBean.getData().remove(2);
        indexBean.getData().remove(3);

        adapter.setData(indexBean.getData());
    }

    @Override
    public void onFailed(int code) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
