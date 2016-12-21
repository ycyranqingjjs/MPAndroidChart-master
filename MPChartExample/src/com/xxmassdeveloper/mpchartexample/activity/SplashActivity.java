package com.xxmassdeveloper.mpchartexample.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.xxmassdeveloper.mpchartexample.R;

import io.realm.Case;

public class SplashActivity extends Activity implements View.OnClickListener {

    private Button bt_login;
    private EditText et_dress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initView();
    }

    private void initView() {
        bt_login = (Button) findViewById(R.id.bt_login);
        et_dress = (EditText) findViewById(R.id.et_dress);
        bt_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_login:
                Intent intent = new Intent(SplashActivity.this, CaseActivity.class);
                intent.putExtra("dress",et_dress.getText().toString());
                startActivity(intent);
                finish();
                break;
            default:
                break;
        }
    }
}
