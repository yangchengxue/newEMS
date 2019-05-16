package com.example.ycx36.newems.view.activity;

import android.content.SharedPreferences;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import com.example.ycx36.newems.R;
import com.example.ycx36.newems.util.interface_retrofit;
import com.example.ycx36.newems.util.regesterUserBean;
import com.example.ycx36.newems.util.registerBean;
import com.example.ycx36.newems.util.requestRegisterUserBean;

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

public class activity_Register extends AppCompatActivity {

    @BindView(R.id.et_userName) EditText et_userName;
    @BindView(R.id.et_userPassword) EditText et_userPassword;
    @BindView(R.id.et_userPhone) EditText et_userPhone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__register);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.tv_header_back,R.id.bt_Register})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_header_back:   //返回
                finish();
                break;
            case R.id.bt_Register:
                RegistrationUser(et_userName,et_userPhone,et_userPassword);
                break;
        }
    }

    public void RegistrationUser(EditText et_userName, EditText et_userPhone,EditText et_userPassword) {
        requestRegisterUserBean registerUser = new requestRegisterUserBean();
        registerUser.setRequest(new requestRegisterUserBean.RequestBean());
        registerUser.getRequest().setCommon(new requestRegisterUserBean.RequestBean.CommonBean());
        registerUser.getRequest().getCommon().setAction("registerUser");
        registerUser.getRequest().getCommon().setReqtime("20190326171001");

        registerUser.getRequest().setContent(new requestRegisterUserBean.RequestBean.ContentBean());
        registerUser.getRequest().getContent().setId(null);
        registerUser.getRequest().getContent().setName(et_userName.getText().toString());
        registerUser.getRequest().getContent().setPassword(et_userPassword.getText().toString());
        registerUser.getRequest().getContent().setPhone(et_userPhone.getText().toString());

        Retrofit retrofit2 = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())  //Gson解析器
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl("http://120.79.62.86:10003/")  //数据接口的主地址
                .build();
        interface_retrofit rinterface = retrofit2.create(interface_retrofit.class);
        rinterface.regesterUser(registerUser)  //请求参数
                .subscribeOn(Schedulers.io())  //观察者切换新线程,subscribe只能调用一次。
                .doOnNext(new Action1<regesterUserBean>() {  //请求结束后调用 doOnNext(),并获data数据
                    @Override
                    public void call(regesterUserBean datas) {

                    }
                })
                .observeOn(AndroidSchedulers.mainThread())   //被观察者切换到主线程
                .subscribe(new Action1<regesterUserBean>() {    //观察者监听到datas数据,主线程中执行
                    @Override
                    public void call(regesterUserBean datas) {
                        if (datas.getResponse().getContent() == 1){
                            Toast.makeText(activity_Register.this,"注册成功",Toast.LENGTH_SHORT).show();
                            finish();
                        }
                        if (datas.getResponse().getContent() == 2){
                            Toast.makeText(activity_Register.this,"注册失败：手机号已注册",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
