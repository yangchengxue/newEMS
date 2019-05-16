package com.example.ycx36.newems.view.sonfragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;


import com.example.ycx36.newems.R;
import com.example.ycx36.newems.util.getCarAllBean;
import com.example.ycx36.newems.util.interface_retrofit;
import com.example.ycx36.newems.util.loginBean;
import com.example.ycx36.newems.util.requestGetCarAllBean;
import com.example.ycx36.newems.view.activity.activity_Register;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static android.content.Context.MODE_PRIVATE;

public class fragment_passwordSignIn extends Fragment {

    @BindView(R.id.at_userPhone)
    AutoCompleteTextView at_userPhone;
    @BindView(R.id.at_userPassword)
    EditText at_userPassword;
    private View view;
    public View onCreateView(LayoutInflater inflater , ViewGroup container , Bundle savedInstanceState){
        if (view == null) {
            view = inflater.inflate(R.layout.layout_f_passwd, container, false);
            ButterKnife.bind(this,view);
        }
        return view;
    }

    @OnClick({R.id.bt_sign,R.id.register,R.id.changePassword})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_sign:
                login();
                break;
            case R.id.register:
                Intent intent = new Intent(getActivity(),activity_Register.class);
                startActivity(intent);
                break;
            case R.id.changePassword:
                break;
        }
    }

    public void login(){
        if (at_userPhone.getText().toString().equals("admin") && at_userPassword.getText().toString().equals("admin")){
            requestGetCarAllBean requestGetCarAll = new requestGetCarAllBean();
            requestGetCarAll.setRequest(new requestGetCarAllBean.RequestBean());
            requestGetCarAll.getRequest().setCommon(new requestGetCarAllBean.RequestBean.CommonBean());
            requestGetCarAll.getRequest().getCommon().setAction("getCarAll");      //设置第一参数 "action":"getCarAll"
            requestGetCarAll.getRequest().getCommon().setReqtime("20190326171001");    //设置第二参数 "reqtime":"20190325180230"

            requestGetCarAll.getRequest().setContent(new requestGetCarAllBean.RequestBean.ContentBean());
            requestGetCarAll.getRequest().getContent().setId(null);      //设置id
            requestGetCarAll.getRequest().getContent().setName("admin");   //设置用户名
            requestGetCarAll.getRequest().getContent().setPassword("admin");  //设置用户密码
            requestGetCarAll.getRequest().getContent().setPhone("");    //设置手机号

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://120.79.62.86:10003/")
                    .addConverterFactory(GsonConverterFactory.create())//设置 Json 转换器
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//RxJava 适配器
                    .build();
            interface_retrofit rxjavaService = retrofit.create(interface_retrofit.class);
            rxjavaService.getCarAll(requestGetCarAll)
                    .subscribeOn(Schedulers.io())//IO线程加载数据
                    .observeOn(AndroidSchedulers.mainThread())//主线程显示数据
                    .subscribe(new Subscriber<getCarAllBean>() {
                        @Override
                        public void onCompleted() {
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.d("错误          ",e.getMessage());
                        }

                        @Override
                        public void onNext(getCarAllBean data) {
                            if (data.getResponse().getContent() != null) {
                                //存储信息
                                saveUserData("UID","uid",data.getResponse().getContent().getUid());
                                saveUserData("UPDATETIME","updatetime",data.getResponse().getContent().getUpdatetime());
                                saveUserData("MOTORSPEED","motorspeed",data.getResponse().getContent().getMotorspeed());
                                saveUserData("MOTOTTORQUE","motortorque",data.getResponse().getContent().getMotortorque());
                                saveUserData("TOTALCURRENT","totalcurrent",data.getResponse().getContent().getTotalcurrent());
                                saveUserData("TOTALVOLTAGE","totalvoltage",data.getResponse().getContent().getTotalvoltage());
                                Toast.makeText(getActivity(),"登录成功",Toast.LENGTH_SHORT).show();
                                Objects.requireNonNull(getActivity()).finish();
                            }else {
                                Toast.makeText(getActivity(),"内容为空",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }else{
            Toast.makeText(getActivity(),"目前只能用测试用户(admin)登录",Toast.LENGTH_SHORT).show();
        }

    }

    public void saveUserData(String NAME,String name,String data){
        SharedPreferences.Editor editor = getActivity().getSharedPreferences(NAME,MODE_PRIVATE).edit();
        editor.putString(name,data);
        editor.apply();
    }

}
