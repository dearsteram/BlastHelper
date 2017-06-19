package com.zhongkebochuang.blasthelper;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

public class NavigationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_navigation);
        Handler handler = new Handler();
        handler.postDelayed(new splashhandler(), 2000); // 延迟2秒，再运行splashhandler的run()
    }
    class splashhandler implements Runnable

    {
        public void run()

        {
            startActivity(new Intent(getApplication(),  LoginActivity.class)); // 显示第2屏
            NavigationActivity.this.finish();   // 结束第1屏
        }
    }

}
