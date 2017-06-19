package com.zhongkebochuang.blasthelper.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhongkebochuang.blasthelper.R;

import java.util.List;
import java.util.Map;

/**
 * Created by ${xingdx} on 2017/5/31.
 */

public class LunTanadapter extends BaseAdapter{
    private LayoutInflater mInflater = null;
    private List<Map<String, Object>> data;
    private Context context;

    public LunTanadapter(List<Map<String, Object>> data, Context context) {
        this.mInflater=LayoutInflater.from(context);
        this.data = data;
        this.context = context;
    }

    /**
     * 组件集合，对应fatie_item.xml中的控件
     * @author Administrator
     */
    public final class Zujian{
        public TextView forumDisplayTitle;
        public TextView postUserName;
        public TextView viewsCnt;
        public TextView replyCnt;
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
            convertView=mInflater.inflate(R.layout.fatie_item, null);
            zujian.forumDisplayTitle=(TextView)convertView.findViewById(R.id.forumDisplayTitle);
            zujian.postUserName=(TextView)convertView.findViewById(R.id.postUserName);
            zujian.viewsCnt=(TextView)convertView.findViewById(R.id.viewsCnt);
            zujian.replyCnt=(TextView)convertView.findViewById(R.id.replyCnt);
            convertView.setTag(zujian);
        }else{
            zujian=(Zujian)convertView.getTag();
        }
        //绑定数据
        zujian.forumDisplayTitle.setText((String)data.get(position).get("forumDisplayTitle"));
        zujian.postUserName.setText((String)data.get(position).get("postUserName"));
        zujian.viewsCnt.setText((String)data.get(position).get("viewsCnt"));
        zujian.replyCnt.setText((String)data.get(position).get("replyLabel"));
        return convertView;
    }
}
