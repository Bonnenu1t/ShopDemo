package com.bawei.shopdemo.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bawei.shopdemo.R;
import com.bawei.shopdemo.bean.ShopBean;
import com.bawei.shopdemo.fragment.ThirdFragment;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Bonnenu1t丶 on 2017/6/22.
 */

public class ThirdFragmentAdapter extends RecyclerView.Adapter<ThirdFragmentAdapter.ThirdViewHolder> {


    private Context context;
    private List<ShopBean.OrderDataBean.CartlistBean> list;

    public ThirdFragmentAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<ShopBean.OrderDataBean.CartlistBean> list) {
        if (this.list == null) {
            this.list = new ArrayList<ShopBean.OrderDataBean.CartlistBean>();
        }
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public ThirdViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.third_fragment_item, parent, false);
        ThirdViewHolder holder = new ThirdViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ThirdViewHolder holder, final int position) {

        if (position > 0) {

            if (list.get(position).getShopId() == list.get(position - 1).getShopId()){
                holder.llShopcartHeader.setVisibility(View.GONE);
            }else {
                holder.llShopcartHeader.setVisibility(View.VISIBLE);
            }
        }else {
            holder.llShopcartHeader.setVisibility(View.VISIBLE);
        }
        holder.tvItemShopcartClothColor.setText("颜色：" + list.get(position).getColor());
        holder.tvItemShopcartClothSize.setText("尺寸：" + list.get(position).getSize());
        holder.tvItemShopcartClothname.setText(list.get(position).getProductName());
        holder.tvItemShopcartShopname.setText(list.get(position).getShopName());
        holder.tvItemShopcartClothPrice.setText("¥" + list.get(position).getPrice());
        holder.etItemShopcartClothNum.setText(list.get(position).getCount() + "");

        Glide.with(context).load(list.get(position).getDefaultPic()).into(holder.ivItemShopcartClothPic);


        //标记 商品是否被选中
        if(list.get(position).isSelect()){
            //商品选中的状态
            holder.tvItemShopcartClothselect.setImageDrawable(context.getResources().getDrawable(R.drawable.cart1_checkbox_check));
        }else {
            //商品未选中的状态
            holder.tvItemShopcartClothselect.setImageDrawable(context.getResources().getDrawable(R.drawable.cart1_checkbox_normal));
        }

        // 标记 商户是否被选中
        if (list.get(position).isShopSelect()){
            //商户选中
            holder.ivItemShopcartShopselect.setImageDrawable(context.getResources().getDrawable(R.drawable.cart1_checkbox_check));
        }else {
            //商户未选中
            holder.ivItemShopcartShopselect.setImageDrawable(context.getResources().getDrawable(R.drawable.cart1_checkbox_normal));
        }


        //删除事件 回调
        holder.ivItemShopcartClothDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(onDeleteClickListener != null){
                    onDeleteClickListener.onDeleteClick(v,position,list.get(position).getId());
                }
                list.remove(position);
                //如果删除的是第一条数据（或者是 数据带有商户名称的数据） 更新数据源， 标记 那条数据 显示商户名称
                ThirdFragment.setFirstState(list);
                notifyDataSetChanged();
            }
        });

        //  - 商品数量事件
        holder.ivItemShopcartClothMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(list.get(position).getCount() > 1){

                    int count = list.get(position).getCount() - 1 ;
                    list.get(position).setCount(count);
                    notifyDataSetChanged();
                    if(onEditListener != null){
                        onEditListener.onEditListener(position,list.get(position).getId(),count);
                    }
                }

            }
        });


        // + 商品数量事件
        holder.ivItemShopcartClothAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int count = list.get(position).getCount()+ 1 ;
                list.get(position).setCount(count);
                notifyDataSetChanged();

                if(onEditListener != null){
                    onEditListener.onEditListener(position,list.get(position).getId(),count);
                }

            }
        });



        //商品 选中和未选中 事件点击
        holder.tvItemShopcartClothselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //标记 当前 item 的选中状态
                list.get(position).setSelect(!list.get(position).isSelect());

                for(int i=0;i<list.size();i++){
                    for(int j=0;j<list.size();j++){
                        //如果是同一家商铺的商品，并且其中一个商品是未选中，那么商铺的全选勾选取消
                        if(list.get(j).getShopId() == list.get(i).getShopId() && !list.get(j).isSelect()){
                            list.get(i).setShopSelect(false);
                            break;
                        } else {
                            //如果是同一家商铺的商品，并且所有商品是选中，那么商铺的选中全选勾选
                            list.get(i).setShopSelect(true);
                        }
                    }
                }
                notifyDataSetChanged();


            }
        });



        // 店铺 选中 与 未选中
        holder.ivItemShopcartShopselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(list.get(position).getIsFirst() == 1){

                    list.get(position).setShopSelect(!list.get(position).isShopSelect());

                    for(int i=0;i<list.size();i++){

                        if(list.get(i).getShopId() == list.get(position).getShopId()){
                            list.get(i).setSelect(list.get(position).isShopSelect());
                        }
                    }
                    notifyDataSetChanged();

                }

            }
        });


        if(onRefershListener != null){
            boolean isSelect = false;
            //遍历查看isSelect是否全选
            for(int i=0;i<list.size();i++){
                if(!list.get(i).isSelect()){
                    isSelect = false;
                    // 只要有一个商品是 未选中的状态 ，全选按钮就是未选中
                    break;
                }else {
                    isSelect = true;
                }
            }
            onRefershListener.onRefersh(isSelect,list);

        }

    }

    // 点击事件

    public OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View view,int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.onItemClickListener = listener ;
    }

    //定义删除的点击事件
    public OnDeleteClickListener onDeleteClickListener;
    public interface OnDeleteClickListener {
        void onDeleteClick(View view,int position,int cartid);
    }

    public void setOnDeleteClickListener(OnDeleteClickListener deleteClickListener){
        this.onDeleteClickListener = deleteClickListener;
    }


    public OnEditListener onEditListener;
    //添加 减少
    public interface OnEditListener {
        void onEditListener(int position,int cartid,int count);
    }

    public void setOnEditListener(OnEditListener onEditListener){
        this.onEditListener = onEditListener;
    }



    // 商品 选中状态发生变化
    public OnRefershListener onRefershListener;

    public interface OnRefershListener{
        //isSelect true 表示商品全部选中 false 未全部选中
        void onRefersh(boolean isSelect,List<ShopBean.OrderDataBean.CartlistBean> list);
    }

    public void setOnRefershListener(OnRefershListener listener){
        this.onRefershListener = listener ;
    }


    //通过三元运算符计算item的数量
    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }


    //自定义的ViewHolder
    class ThirdViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.view)
        View view;
        @BindView(R.id.iv_item_shopcart_shopselect)
        ImageView ivItemShopcartShopselect;
        @BindView(R.id.tv_item_shopcart_shopname)
        TextView tvItemShopcartShopname;
        @BindView(R.id.ll_shopcart_header)
        LinearLayout llShopcartHeader;
        @BindView(R.id.tv_item_shopcart_clothname)
        TextView tvItemShopcartClothname;
        @BindView(R.id.tv_item_shopcart_clothselect)
        ImageView tvItemShopcartClothselect;
        @BindView(R.id.iv_item_shopcart_cloth_pic)
        ImageView ivItemShopcartClothPic;
        @BindView(R.id.tv_item_shopcart_cloth_price)
        TextView tvItemShopcartClothPrice;
        @BindView(R.id.tv_item_shopcart_cloth_color)
        TextView tvItemShopcartClothColor;
        @BindView(R.id.tv_item_shopcart_cloth_size)
        TextView tvItemShopcartClothSize;
        @BindView(R.id.iv_item_shopcart_cloth_minus)
        ImageView ivItemShopcartClothMinus;
        @BindView(R.id.et_item_shopcart_cloth_num)
        TextView etItemShopcartClothNum;
        @BindView(R.id.iv_item_shopcart_cloth_add)
        ImageView ivItemShopcartClothAdd;
        @BindView(R.id.iv_item_shopcart_cloth_delete)
        ImageView ivItemShopcartClothDelete;
        public ThirdViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
