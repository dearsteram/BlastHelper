package com.zhongkebochuang.blasthelper.bean;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by ${xingdx} on 2017/5/31.
 */

public class TieZibean {


    public static int sendSMSGet(String Mobile, String Content, String send_time)
            throws MalformedURLException, UnsupportedEncodingException {
        URL url = null;
        String CorpID = "TCLK04939";// 账户名
        String Pwd = "blast@2858";// 密码
        String send_content = URLEncoder.encode(
                Content.replaceAll("<br/>", " "), "GBK");// 发送内容
        url = new URL("https://inolink.com/ws/BatchSend2.aspx?CorpID="
                + CorpID + "&Pwd=" + Pwd + "&Mobile=" + Mobile + "&Content="
                + send_content + "&Cell=&SendTime=" + send_time);
        BufferedReader in = null;
        int inputLine = 0;
        try {
            System.out.println("开始发送短信手机号码为 ：" + Mobile);
            in = new BufferedReader(new InputStreamReader(url.openStream()));
            inputLine = new Integer(in.readLine()).intValue();
        } catch (Exception e) {
            System.out.println("网络异常,发送短信失败！");
            inputLine = -2;
            Log.e("TAG",e.toString());
        }
        System.out.println("结束发送短信返回值：  " + inputLine);
        return inputLine;
    }


}
