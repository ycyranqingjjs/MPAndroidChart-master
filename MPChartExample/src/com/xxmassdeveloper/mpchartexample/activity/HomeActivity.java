package com.xxmassdeveloper.mpchartexample.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.xxmassdeveloper.mpchartexample.R;

public class HomeActivity extends Activity implements View.OnClickListener {
    
    private RelativeLayout jc01;
    private RelativeLayout jc02;
    private RelativeLayout jc031;
    private RelativeLayout jc032;
    private RelativeLayout jc041;
    private RelativeLayout jc042;
    private RelativeLayout jc051;
    private RelativeLayout jc052;
    private RelativeLayout jc061;
    private RelativeLayout jc062;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
    }

    private void initView() {
        jc01 = (RelativeLayout) findViewById(R.id.jc01);
        jc01.setOnClickListener(this);
        jc02 = (RelativeLayout) findViewById(R.id.jc02);
        jc02.setOnClickListener(this);
        jc031 = (RelativeLayout) findViewById(R.id.jc031);
        jc031.setOnClickListener(this);
        jc032 = (RelativeLayout) findViewById(R.id.jc032);
        jc032.setOnClickListener(this);
        jc041 = (RelativeLayout) findViewById(R.id.jc041);
        jc041.setOnClickListener(this);
        jc042 = (RelativeLayout) findViewById(R.id.jc042);
        jc042.setOnClickListener(this);
        jc051 = (RelativeLayout) findViewById(R.id.jc051);
        jc051.setOnClickListener(this);
        jc052 = (RelativeLayout) findViewById(R.id.jc052);
        jc052.setOnClickListener(this);
        jc061 = (RelativeLayout) findViewById(R.id.jc061);
        jc061.setOnClickListener(this);
        jc062 = (RelativeLayout) findViewById(R.id.jc062);
        jc062.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(HomeActivity.this,CaseActivity.class));
//        switch (v.getId()){

//            case R.id.jc01:
////                Toast.makeText(HomeActivity.this, "点击了", Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(HomeActivity.this,CaseActivity.class));
//                break;
//        }
    }
}
