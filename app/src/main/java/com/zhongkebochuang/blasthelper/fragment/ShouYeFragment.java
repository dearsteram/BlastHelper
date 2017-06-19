package com.zhongkebochuang.blasthelper.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.zhongkebochuang.blasthelper.BaoPoQuanActivity;
import com.zhongkebochuang.blasthelper.R;
import com.zhongkebochuang.blasthelper.adapter.JingXuanadapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ${xingdx} on 2017/5/26.
 */

public class ShouYeFragment extends Fragment {
    private LinearLayout lay1;
    private ListView listview;
    private SwipeRefreshLayout demo_swiperefreshlayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shouye_fragment, container, false);
        listview = (ListView) view.findViewById(R.id.listview_jingxuan);
        lay1 = (LinearLayout) view.findViewById(R.id.lay1);
        lay1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), BaoPoQuanActivity.class);
                startActivity(intent);
            }
        });

        //获取将要绑定的数据设置到data中
        List<Map<String, Object>> list = getData();
        JingXuanadapter jinxuan = new JingXuanadapter(list, getActivity());
        listview.setAdapter(jinxuan);
        demo_swiperefreshlayout = (SwipeRefreshLayout) getActivity().findViewById(R.id.demo_swiperefreshlayout);
        demo_swiperefreshlayout.setProgressBackgroundColorSchemeResource(android.R.color.white);
        demo_swiperefreshlayout.setColorSchemeResources(android.R.color.holo_blue_light,
                android.R.color.holo_red_light, android.R.color.holo_orange_light,
                android.R.color.holo_green_light);
        demo_swiperefreshlayout.setProgressViewOffset(false, 0, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
                        .getDisplayMetrics()));
        demo_swiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        demo_swiperefreshlayout.setRefreshing(false);
                    }
                }, 5000);
                //  一般会从网络获取数据
//        new Handler().post(new Runnable() {
//            @Override
//            public void run() {
//                获取数据
//                refreshData();
//                swiper.setRefreshing(false);
//            }
//        });

            }
        });
        /**
         * 解决listivew与SwipeRefresh的下拉冲突问题
         * */
        listview.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }

            @Override
            public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem == 0)
                    demo_swiperefreshlayout.setEnabled(true);
                else
                    demo_swiperefreshlayout.setEnabled(false);
            }
        });

        return view;
    }

    private List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map;
        for (int i = 0; i < 10; i++) {
            map = new HashMap<String, Object>();
            map.put("user_img", R.drawable.aaa);
            map.put("user_name", "希尔瓦娜斯");
            map.put("uesr_hua", "希尔瓦娜斯是部落之中最与众不同的人物，和这个神秘的领袖并肩而战的人无不感到恐惧和敬畏。");
            map.put("ig_img", R.drawable.aaa);
            list.add(map);
//            listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                    Intent intent = new Intent(getActivity(), TiZiActivity.class);
//                    startActivity(intent);
//                }
//            });
        }
        return list;
    }


}
