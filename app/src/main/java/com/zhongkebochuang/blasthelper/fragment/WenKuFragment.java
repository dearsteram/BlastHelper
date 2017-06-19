package com.zhongkebochuang.blasthelper.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.zhongkebochuang.blasthelper.R;
import com.zhongkebochuang.blasthelper.adapter.HomeAdapter;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by ${xingdx} on 2017/5/26.
 */

public class WenKuFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private HomeAdapter mAdapter;
    private LayoutInflater mInflater;
    ArrayList<HashMap<String, Object>> lstImageItem;
    public static final int HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL;
    public static final int VERTICAL_LIST = LinearLayoutManager.VERTICAL;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.wenku_fragment, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.gridview);
        SwipeRefreshLayout demo_swiperefreshlayout = (SwipeRefreshLayout) getActivity().findViewById(R.id.demo_swiperefreshlayout);
        demo_swiperefreshlayout.setEnabled(false);
//设置布局管理器
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
//设置adapter
        mAdapter = new HomeAdapter(getActivity());
        mRecyclerView.setAdapter(mAdapter);
//设置Item增加、移除动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
//添加分割线
        mRecyclerView.addItemDecoration(new DividerItemDecoration(
                getActivity(), WenKuFragment.HORIZONTAL_LIST));
        mAdapter.setOnRecyclerViewItemClickListener(new HomeAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getActivity(),"您点击的Item的索引为:"+position,Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }



}


