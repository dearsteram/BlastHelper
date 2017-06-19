package com.zhongkebochuang.blasthelper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

import com.zhongkebochuang.blasthelper.adapter.LunTanadapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaoPoQuanActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageButton tv_fatie;
    private ListView listview;
    private ImageView newThreadBackBtn;
    String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bao_po_quan);
        listview = (ListView) findViewById(R.id.listview);
        /**
         * 接受到发帖界面传来的表一数据
         * */
        Intent intent = new Intent(this, FaTieActivity.class);
        result = this.getIntent().getStringExtra("messagetiele");
//        Toast.makeText(getActivity(), result, Toast.LENGTH_SHORT).show();

        //获取将要绑定的数据设置到data中
        List<Map<String, Object>> list = getData();
        listview.setAdapter(new LunTanadapter(list, this));
        initview();
    }
    private void initview(){
        tv_fatie = (ImageButton) findViewById(R.id.tv_fatie);
        newThreadBackBtn = (ImageView) findViewById(R.id.newThreadBackBtn);
        tv_fatie.setOnClickListener(this);
        newThreadBackBtn.setOnClickListener(this);
    }
    private List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map;
        for (int i = 0; i < 10; i++) {
            map = new HashMap<String, Object>();
            map.put("forumDisplayTitle", result);
            map.put("postUserName", "一个合格的小编");
            map.put("viewsCnt", "10086");
            map.put("replyLabel", "1008611");
            list.add(map);
            listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(BaoPoQuanActivity.this, TiZiActivity.class);
                    startActivity(intent);
                }
            });
        }
        return list;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_fatie:
                Intent intent = new Intent(BaoPoQuanActivity.this,FaTieActivity.class);
                startActivity(intent);
                break;
            case R.id.newThreadBackBtn:
                Intent intent1 = new Intent(BaoPoQuanActivity.this,MainActivity.class);
                startActivity(intent1);
                BaoPoQuanActivity.this.finish();
        }

    }
}
