package com.zhongkebochuang.blasthelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ${xingdx} on 2017/5/27.
 */

public class SouSuoActivity extends Activity {
    @Bind(R.id.etSearch)
    EditText etSearch;
    @Bind(R.id.ivDeleteText)
    ImageView ivDeleteText;
    @Bind(R.id.btnSearch)
    Button btnSearch;
    @Bind(R.id.btnfinish)
    Button btnfinish;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ssousuo_main);
        ButterKnife.bind(this);


        etSearch.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub

            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub

            }

            public void afterTextChanged(Editable s) {
                if (s.length() == 0) {
                    ivDeleteText.setVisibility(View.GONE);
                    btnfinish.setVisibility(View.VISIBLE);
                    btnSearch.setVisibility(View.GONE);
                } else {
                    ivDeleteText.setVisibility(View.VISIBLE);
                    btnSearch.setVisibility(View.VISIBLE);
                    btnfinish.setVisibility(View.GONE);
                }
            }
        });

    }

    @OnClick({R.id.btnSearch, R.id.ivDeleteText,R.id.btnfinish})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivDeleteText:
                etSearch.setText("");
                break;
            case R.id.btnSearch:
               /**
                * 搜索功能
                * */
                Toast.makeText(this,"你什么也没搜到",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnfinish:
                Intent intent = new Intent(SouSuoActivity.this,MainActivity.class);
                intent.putExtra("idf",1);
                startActivity(intent);
                SouSuoActivity.this.finish();
                break;
        }
    }
}
