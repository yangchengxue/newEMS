package com.example.ycx36.newems.view.activity;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.ycx36.newems.R;
import com.example.ycx36.newems.util.changeUserInfoBean;
import com.example.ycx36.newems.util.interface_retrofit;
import com.example.ycx36.newems.util.userInfoBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class activity_changeInfo extends AppCompatActivity {

    @BindView(R.id.tv_userName) EditText tv_userName;
    @BindView(R.id.tv_email) EditText tv_email;
    @BindView(R.id.tv_mobilephone) EditText tv_mobilephone;

    String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_info);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.lin_header_back2,R.id.tv_header_right})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.lin_header_back2:
                finish();
                break;
            case R.id.tv_header_right:
                break;
        }
    }


}
