package com.zhongkebochuang.blasthelper.uitils;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.Map;

/**
 * Created by ${xingdx} on 2017/5/27.
 */

public class MyApplication extends Application {
    private static MyApplication instance;
    public static RequestQueue queue;
    // 用于存放按钮倒计时时间
    public static Map<String, Long> map;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        queue = Volley.newRequestQueue(getApplicationContext());
    }

    public static MyApplication getInstance(){
        // 因为我们程序运行后，Application是首先初始化的，如果在这里不用判断instance是否为空
        return instance;
    }
    public static RequestQueue getHttpQueue(){
        return queue;
    }
}

