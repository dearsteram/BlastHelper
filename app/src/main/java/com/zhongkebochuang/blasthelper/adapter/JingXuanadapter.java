package com.zhongkebochuang.blasthelper.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhongkebochuang.blasthelper.R;

import java.util.List;
import java.util.Map;

/**
 * Created by ${xingdx} on 2017/6/13.
 */

public class JingXuanadapter extends BaseAdapter{
    private LayoutInflater mInflater = null;
    private List<Map<String, Object>> data;
    private Context context;

    public JingXuanadapter(List<Map<String, Object>> data, Context context) {
        this.mInflater=LayoutInflater.from(context);
        this.data = data;
        this.context = context;
    }

    /**
     * 组件集合，对应fatie_item.xml中的控件
     * @author Administrator
     */
    public final class Zujian{
        public com.zhongkebochuang.blasthelper.uitils.CircleImageView user_img;
        public TextView user_name;
        public TextView uesr_hua;
        public ImageView ig_img;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Zujian zujian=null;
        if(convertView==null){
            zujian=new Zujian();
            //获得组件，实例化组件
            convertView=mInflater.inflate(R.layout.jingxuan_item, null);
            zujian.user_img=(com.zhongkebochuang.blasthelper.uitils.CircleImageView) convertView.findViewById(R.id.user_img);
            zujian.user_name=(TextView)convertView.findViewById(R.id.user_name);
            zujian.uesr_hua=(TextView)convertView.findViewById(R.id.uesr_hua);
            zujian.ig_img=(ImageView) convertView.findViewById(R.id.ig_img);
            convertView.setTag(zujian);
        }else{
            zujian=(Zujian)convertView.getTag();
        }
        //绑定数据
        zujian.user_img.setImageResource((Integer)data.get(position).get("user_img"));
        zujian.user_name.setText((String)data.get(position).get("user_name"));
        zujian.uesr_hua.setText((String)data.get(position).get("uesr_hua"));
        zujian.ig_img.setImageResource((Integer) data.get(position).get("ig_img"));
        return convertView;
    }
}


