package com.zhongkebochuang.blasthelper;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.zhongkebochuang.blasthelper.adapter.TieZiadapter;
import com.zhongkebochuang.blasthelper.widget.XListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.zhongkebochuang.blasthelper.R.id.showthreadListview;

public class TiZiActivity extends AppCompatActivity implements XListView.IXListViewListener {


    XListView mlist;
    ImageView img;
    Handler mHandler;
    List<Map<String, Object>> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.avtivity_tiezi);
        initview();
        List<Map<String, Object>> list = getData();
        mlist.setAdapter(new TieZiadapter(TiZiActivity.this, list));
        mlist.setPullLoadEnable(true);
        mlist.setPullRefreshEnable(true);//下拉刷新
        mlist.setXListViewListener(this);//给xListView设置监听  ******
        mHandler = new Handler();
    }


    private void initview() {
        mlist = (XListView) findViewById(showthreadListview);
        img = (ImageView) findViewById(R.id.showThreadBackBtn);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TiZiActivity.this.finish();
            }
        });
    }


    private List<Map<String, Object>> getData() {
        list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map;
        for (int i = 1; i < 10; i++) {
            map = new HashMap<String, Object>();
            map.put("showthreadUsername", "男子离家1年回家后发现妻子秘密，果断离婚。。。。。。。。");
            map.put("showThreadFloorNum", i + "#");
            map.put("showthreadPosttime", "2018-2-18");
            map.put("showthreadMsg", "看故事+V 嘿嘿嘿！！！");
            list.add(map);
//            mlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                    Intent intent = new Intent(TiZiActivity.this, TiZiActivity.class);
//                    startActivity(intent);
//                }
//            });
        }
        return list;
    }

    @Override
    public void onRefresh() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                list.clear();
                getData();
                mlist.setAdapter(new TieZiadapter(TiZiActivity.this, list));
                onLoad();
            }
        }, 2000);


    }

    @Override
    public void onLoadMore() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                list.clear();
                mlist.setAdapter(new TieZiadapter(TiZiActivity.this, list));
                onLoad();
            }
        }, 2000);

    }

    private void onLoad() {
        mlist.stopRefresh();
        mlist.stopLoadMore();

    }
}
