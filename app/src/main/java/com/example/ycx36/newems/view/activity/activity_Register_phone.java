package com.example.ycx36.newems.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import com.example.ycx36.newems.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class activity_Register_phone extends AppCompatActivity {

    @BindView(R.id.phNumber) EditText phNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__register_phone);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_header_back,R.id.getVcode})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_header_back:   //返回
                finish();
                break;
            case R.id.getVcode:
                Intent intent = new Intent(activity_Register_phone.this,activity_Register_Vcode.class);
                Bundle bundle = new Bundle();      //创建一个budle对象
                bundle.putString("phone", phNumber.getText().toString());  //写入数据
                intent.putExtras(bundle);             //用putExtras方法将写入的数据存入intent中
                startActivity(intent);
                break;
        }
    }
}
