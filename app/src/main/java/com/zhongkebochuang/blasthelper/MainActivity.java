package com.zhongkebochuang.blasthelper;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhongkebochuang.blasthelper.fragment.ShouYeFragment;
import com.zhongkebochuang.blasthelper.fragment.WenKuFragment;
import com.zhongkebochuang.blasthelper.fragment.WoDeFragment;
import com.zhongkebochuang.blasthelper.uitils.SlidingMenu;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FrameLayout fragmentContainer;
    private LinearLayout tabMenu;
    private TextView txtWenku;
    private TextView txtLuntan;
    private TextView txtGeren,text_wo;
    private ImageView sousuoBtn;
    //用于展示文库的fragment
    private ShouYeFragment wenku;
    //用于展示论坛的fragment
    private WenKuFragment luntan;
    //用于展示个人信息的fragment
    private WoDeFragment wode;
    /**
     * 用于对Fragment进行管理
     */
    private FragmentManager fragmentManager;
    private SlidingMenu mMenu;
    private SwipeRefreshLayout demo_swiperefreshlayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initview();
        int id = getIntent().getIntExtra("id", 0);
        if ( id == 1 ) {
            txtLuntan.performClick();
        }
        //模拟一次点击事件
        txtWenku.performClick();
//        mMenu = (SlidingMenu) findViewById(R.id.id_menu);
    }

//    public void toggleMenu(View view) {
//        mMenu.toggle();
//    }

    //重置所有文本的选中状态
    public void selected() {
        txtWenku.setSelected(false);
        txtLuntan.setSelected(false);
        txtGeren.setSelected(false);

    }

    private void initview() {
        demo_swiperefreshlayout = (SwipeRefreshLayout) findViewById(R.id.demo_swiperefreshlayout);
        demo_swiperefreshlayout.setEnabled(false);
        fragmentContainer = (FrameLayout) findViewById(R.id.fragment_container);
        tabMenu = (LinearLayout) findViewById(R.id.tab_menu);
        txtWenku = (TextView) findViewById(R.id.txt_wenku);
        txtLuntan = (TextView) findViewById(R.id.txt_luntan);
        txtGeren = (TextView) findViewById(R.id.txt_geren);
        sousuoBtn = (ImageView) findViewById(R.id.sousuo_btn);
        text_wo = (TextView) findViewById(R.id.text_wo);
        txtWenku.setOnClickListener(this);
        tabMenu.setOnClickListener(this);
        txtLuntan.setOnClickListener(this);
        txtGeren.setOnClickListener(this);
        sousuoBtn.setOnClickListener(this);

    }

    //隐藏所有Fragment
    public void hideAllFragment(FragmentTransaction transaction) {
        if ( wenku != null ) {
            transaction.hide(wenku);
        }
        if ( luntan != null ) {
            transaction.hide(luntan);
        }
        if ( wode != null ) {
            transaction.hide(wode);
        }

    }

    @Override
    public void onClick(View v) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        hideAllFragment(transaction);
        switch (v.getId()) {
            case R.id.txt_wenku:
                text_wo.setVisibility(View.GONE);
                sousuoBtn.setVisibility(View.VISIBLE);
                selected();
                txtWenku.setSelected(true);
                demo_swiperefreshlayout.setEnabled(true);
                if ( wenku == null ) {
                    wenku = new ShouYeFragment();
                    transaction.add(R.id.fragment_container, wenku);
                } else {
                    transaction.show(wenku);
                }

                break;
            case R.id.txt_luntan:
                text_wo.setVisibility(View.GONE);
                sousuoBtn.setVisibility(View.VISIBLE);
                selected();
                demo_swiperefreshlayout.setEnabled(false);
                txtWenku.setSelected(true);
                if ( luntan == null ) {
                    luntan = new WenKuFragment();
                    transaction.add(R.id.fragment_container, luntan);
                } else {
                    transaction.show(luntan);
                }

                break;
            case R.id.txt_geren:
                text_wo.setVisibility(View.VISIBLE);
                sousuoBtn.setVisibility(View.GONE);
                demo_swiperefreshlayout.setEnabled(false);
                selected();
                txtWenku.setSelected(true);
                if ( wode == null ) {
                    wode = new WoDeFragment();
                    transaction.add(R.id.fragment_container, wode);
                } else {
                    transaction.show(wode);
                }
                break;
            case R.id.sousuo_btn:
                Intent intent1 = new Intent(MainActivity.this, SouSuoActivity.class);
                startActivity(intent1);

                break;
        }
        transaction.commit();
    }
}

