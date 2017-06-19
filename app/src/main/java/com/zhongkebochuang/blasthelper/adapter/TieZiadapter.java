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
 * Created by ${xingdx} on 2017/6/1.
 */

public class TieZiadapter extends BaseAdapter {
    private LayoutInflater mInflater = null;
    private Context context;
    private List<Map<String, Object>> data;

    public TieZiadapter(Context context, List<Map<String, Object>> data) {
        this.context = context;
        this.data = data;
    }

    /**
     * 组件集合
     *
     */
    public final class Zujian{
        public TextView showthreadUsername;
        public TextView showThreadFloorNum;
        public TextView showthreadPosttime;
        public TextView showthreadMsg;
    }

    @Override
    public int getCount() {
        return data != null ? data.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Zujian zujian=null;
        if(convertView==null){
            zujian=new Zujian();
            //获得组件，实例化组件
            convertView=LayoutInflater.from(context).inflate(R.layout.tiezi_item, parent, false);
            zujian.showthreadUsername=(TextView)convertView.findViewById(R.id.showthreadUsername);
            zujian.showThreadFloorNum=(TextView)convertView.findViewById(R.id.showThreadFloorNum);
            zujian.showthreadPosttime=(TextView)convertView.findViewById(R.id.showthreadPosttime);
            zujian.showthreadMsg=(TextView)convertView.findViewById(R.id.showthreadMsg);
            convertView.setTag(zujian);
        }else{
            zujian=(Zujian)convertView.getTag();
        }
        //绑定数据
        zujian.showthreadUsername.setText((String)data.get(position).get("showthreadUsername"));
        zujian.showThreadFloorNum.setText((String)data.get(position).get("showThreadFloorNum"));
        zujian.showthreadPosttime.setText((String)data.get(position).get("showthreadPosttime"));
        zujian.showthreadMsg.setText((String)data.get(position).get("showthreadMsg"));
        return convertView;
    }
}
