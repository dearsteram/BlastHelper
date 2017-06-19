package com.zhongkebochuang.blasthelper.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhongkebochuang.blasthelper.R;

/**
 * Created by ${xingdx} on 2017/5/26.
 */

public class WoDeFragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.wode_fragment,container,false);
        return view;
    }
}
