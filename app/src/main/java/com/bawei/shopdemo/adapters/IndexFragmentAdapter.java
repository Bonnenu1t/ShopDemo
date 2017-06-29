package com.bawei.shopdemo.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bawei.shopdemo.R;
import com.bawei.shopdemo.bean.IndexBean;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Bonnenu1t丶 on 2017/6/21.
 */

public class IndexFragmentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    public static final String BASE_IMAGE = "http://image3.suning.cn";

    public final int singleType = 0;
    public final int horizontalType = 1;
    public final int tuangouType = 2;
    public final int defaultType = 3;

    Context context;

    public List<IndexBean.DataBean> list;


    public IndexFragmentAdapter(Context context) {
        this.context = context;
    }

    //自定义一个setData 做list的集合的判断
    public void setData(List<IndexBean.DataBean> data) {
        if (list == null) {
            this.list = new ArrayList<IndexBean.DataBean>();
        }
        this.list.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        RecyclerView.ViewHolder viewHolder = null;
        switch (viewType) {
            case singleType://单张图片的布局
                view = LayoutInflater.from(context).inflate(R.layout.single_item, parent,false);
                viewHolder = new SinglePicViewholder(view);
                break;
            case horizontalType://水平滑动的布局
                view = LayoutInflater.from(context).inflate(R.layout.horizontal_item, parent,false);
                viewHolder = new HorizontalViewHolder(view);
                break;
            case tuangouType:
                view = LayoutInflater.from(context).inflate(R.layout.taungou_item,parent,false);
                viewHolder = new TuanGouViewholder(view);
                break;
            case defaultType:
                view = LayoutInflater.from(context).inflate(R.layout.index_default_item,parent,false);
                viewHolder = new DefaultViewHolder(view);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //对list是否为空进行一个判断
        if (list == null || list.size() == 0) {
            return;
        }
            //对holder进行判断
        if (holder instanceof SinglePicViewholder) {
            //单张图片
            SinglePicViewholder viewholder = (SinglePicViewholder) holder;
            String pic = list.get(position).getTag().get(0).getPicUrl();
            Glide.with(context).load(BASE_IMAGE + pic).into(viewholder.singleImage);
        }else if (holder instanceof HorizontalViewHolder){
            //水平滑动
            HorizontalViewHolder holder1 = (HorizontalViewHolder) holder;
            String pic = list.get(position).getTag().get(0).getPicUrl();
            Glide.with(context).load(BASE_IMAGE + pic).into(holder1.imageView);

            int size = list.get(position).getTag().size();

            for (int i = 1; i < size; i++) {
                //遍历图片相应的路径
                String horizontal_pic = list.get(position).getTag().get(i).getPicUrl();

                //代码中自定义布局
                LinearLayout layout = new LinearLayout(context);
                //设置LinearLayout的方向
                layout.setOrientation(LinearLayout.VERTICAL);
                //设置边距
                layout.setPadding(10,10,10,10);

                //自定义imageView
                ImageView imageView = new ImageView(context);
                //设置imageView的布局参数
                imageView.setLayoutParams(new LinearLayout.LayoutParams(150,150));

                Glide.with(context).load(BASE_IMAGE + horizontal_pic).into(imageView);

                //自定义textView
                TextView textView = new TextView(context);
                textView.setTextSize(25f);
                textView.setTextColor(Color.BLUE);

                //自定义随机数
                Random random=new Random();
                int num = (int) (Math.random()*50+70);//产生70-120的随机数
                textView.setText(String.valueOf(num));
                textView.setGravity(Gravity.CENTER);

                //把自定义的控件添加到当前自定义的布局当中
                layout.addView(imageView);
                layout.addView(textView);

                holder1.linearLayout.addView(layout);
                }
            }else if (holder instanceof TuanGouViewholder){

                TuanGouViewholder viewholder = (TuanGouViewholder) holder;

                String item_pic = list.get(position).getTag().get(0).getPicUrl();
                Glide.with(context).load(BASE_IMAGE+item_pic).into(viewholder.tuangouTop);

                String item_pic1 = list.get(position+1).getTag().get(0).getPicUrl();
                Glide.with(context).load(BASE_IMAGE+item_pic1).into(viewholder.tuangouBigimage);

                viewholder.tuangouTitle.setText(list.get(position+1).getTag().get(0).getElementName());
                viewholder.tuangouDes.setText(list.get(position+1).getTag().get(0).getElementDesc());
            }else if (holder instanceof DefaultViewHolder){

                DefaultViewHolder viewHolder = (DefaultViewHolder) holder;
                viewHolder.textView.setVisibility(View.GONE);
        }

        }


    //定义item的类型
    @Override
    public int getItemViewType(int position) {

        int type = 0;
        switch (position) {
            case 0:
                type = singleType;
                break;
            case 1:
                type = horizontalType;
                break;
            case 2:
                type = tuangouType;
                break;
            case 3:
                type = defaultType;
                break;

        }
        return type;
    }

    //定义intem的数量
    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }


    //单张图片的viewHolder
    class SinglePicViewholder extends RecyclerView.ViewHolder {
        @BindView(R.id.single_image)
        ImageView singleImage;
        public SinglePicViewholder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    //水平滚动的item
    class HorizontalViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.horizontal_image)
        ImageView imageView;
        @BindView(R.id.horizontal_linear)
        LinearLayout linearLayout;
        public HorizontalViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }


    class  DefaultViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.indexfragment_textview)
        TextView textView;
        public DefaultViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    //团购
    class TuanGouViewholder extends RecyclerView.ViewHolder{
        @BindView(R.id.tuangou_top)
        ImageView tuangouTop;
        @BindView(R.id.tuangou_bigimage)
        ImageView tuangouBigimage;
        @BindView(R.id.tuangou_title)
        TextView tuangouTitle;
        @BindView(R.id.tuangou_des)
        TextView tuangouDes;
        @BindView(R.id.tuangou_price)
        TextView tuangouPrice;
        @BindView(R.id.tuangou_buttom)
        LinearLayout linearLayoutButtom;
        public TuanGouViewholder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
