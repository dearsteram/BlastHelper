package com.zhongkebochuang.blasthelper;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.zhongkebochuang.blasthelper.bean.TieZibean;

public class ZhuCeActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btn,savecord;
    private int inputLine,message;
    final TieZibean tiezi = new TieZibean();
    private TimeCount time;
    private ImageView showThreadBackBtn;
    private EditText cord,phone;
    private String sphone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuce);
        //按钮倒计时
        time = new TimeCount(60000, 1000);

        initview();
    }
    private void initview(){
        btn = (Button) findViewById(R.id.button);
        showThreadBackBtn = (ImageView) findViewById(R.id.showThreadBackBtn);
        cord = (EditText) findViewById(R.id.cord);
        savecord =(Button) findViewById(R.id.savecord);
        phone = (EditText) findViewById(R.id.phone);
        showThreadBackBtn.setOnClickListener(this);
        btn.setOnClickListener(this);
        savecord.setOnClickListener(this);

    }
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle data = msg.getData();
            String val = data.getString("value");
            Log.i("mylog", "请求结果为-->" + val);
            // TODO
            // UI界面的更新等相关操作
        }
    };


    Runnable networkTask = new Runnable() {

        @Override
        public void run() {
            // TODO

            try{
                inputLine = tiezi.sendSMSGet(sphone,
                        "您的注册验证码为:"+message+"【京工博创】", "");
            }catch (Exception e){

            }
            Message msg = new Message();
            Bundle data = new Bundle();
            data.putString("value", String.valueOf(inputLine));
            msg.setData(data);
            handler.sendMessage(msg);
        }
    };


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button:
                message = (int) ((Math.random() * 9 + 1) * 100000);
               sphone = phone.getText().toString();
                if( TextUtils.isEmpty(phone.getText())){
                    Toast.makeText(this,"手机号码不能为空",Toast.LENGTH_SHORT).show();
                  return;
                }else{
                    if(phone.getText().toString().length()==11){
                        new Thread(networkTask).start();
                        time.start();
                    }else{
                        Toast.makeText(this,"请输入正确的手机号码",Toast.LENGTH_SHORT).show();
                        return;
                    }
                }

                break;
            case R.id.showThreadBackBtn:
                this.finish();
                break;
            case R.id.savecord:
                //短信验证码
                int i1 = Integer.parseInt(cord.getText().toString());
                if( i1 == message){
                    Toast.makeText(this,"验证成功",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(ZhuCeActivity.this,MainActivity.class);
                    startActivity(i);
                }else{
                    Toast.makeText(this,"验证码错误，请重新输入",Toast.LENGTH_SHORT).show();
            }


        }
    }

    class TimeCount extends CountDownTimer {


        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            btn.setBackgroundColor(Color.parseColor("#B6B6D8"));
            btn.setClickable(false);
            btn.setText("("+millisUntilFinished / 1000 +") 秒后可重新发送");
        }

        @Override
        public void onFinish() {
            btn.setText("重新获取验证码");
            btn.setClickable(true);
            btn.setBackgroundColor(Color.parseColor("#4EB84A"));

        }
    }


}
