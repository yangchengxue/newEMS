package com.example.ycx36.newems.view.activity;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.example.ycx36.newems.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class activity_Register_Vcode extends AppCompatActivity {

    @BindView(R.id.Vcode) AutoCompleteTextView Vcode;
    String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__register__vcode);
        ButterKnife.bind(this);
        Bundle bundle = this.getIntent().getExtras();    //bundle取出之前存在intent的数据
        phone = bundle.getString("phone");   //根据键名读取数据
    }

    @OnClick({R.id.tv_header_back,R.id.bt_next})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_header_back:   //返回
                finish();
                break;
            case R.id.bt_next:
                break;
        }
    }
}
