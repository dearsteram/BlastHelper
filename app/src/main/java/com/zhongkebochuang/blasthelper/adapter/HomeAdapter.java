package com.zhongkebochuang.blasthelper.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.zhongkebochuang.blasthelper.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ${xingdx} on 2017/6/15.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder>{
    private LayoutInflater mInflater;
    private List<Integer> mDatas = new ArrayList<Integer>(Arrays.asList(R.mipmap.wenku_lutianbaopo, R.mipmap.wenku_tezhong,
            R.mipmap.wenku_chaichu, R.mipmap.wenku_juejin,
            R.mipmap.wenku_yantitexing, R.mipmap.wenku_shigongshebei,
            R.mipmap.wenku_baopoanquan, R.mipmap.wenku_baopoqicai));

    public  HomeAdapter(Context context){
        mInflater=LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        final View view=mInflater.inflate(R.layout.wenku_item,parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onRecyclerViewItemClickListener!=null){
                    onRecyclerViewItemClickListener.onItemClick(view,(int)view.getTag());
                }
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position)
    {
        holder.tv.setImageResource(mDatas.get(position));
        holder.itemView.setTag(position);
    }


    @Override
    public int getItemCount()
    {
        return mDatas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {

        ImageView tv;

        public MyViewHolder(View view)
        {
            super(view);
            tv = (ImageView) view.findViewById(R.id.ItemImage);
        }
    }

    private OnRecyclerViewItemClickListener onRecyclerViewItemClickListener;
    public OnRecyclerViewItemClickListener getOnRecyclerViewItemClickListener() {
        return onRecyclerViewItemClickListener;
    }
    public void setOnRecyclerViewItemClickListener(OnRecyclerViewItemClickListener onRecyclerViewItemClickListener) {
        this.onRecyclerViewItemClickListener = onRecyclerViewItemClickListener;
    }
/**
 * 类似ListView的 onItemClickListener接口
 */
public interface OnRecyclerViewItemClickListener{
    /**
     * Item View发生点击回调的方法
     * @param view   点击的View
     * @param position  具体Item View的索引
     */
    void onItemClick(View view,int position);
}
}